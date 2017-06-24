/**
 * 
 */
package org.wcs.lemursportal.service.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;

import freemarker.template.Configuration;

/**
 * @author mikajy.hery
 *
 */
@Service
public class MailServiceImpl implements MailService {

	@Resource(name = "mailSender")
	private JavaMailSender mailSender;

	private String mailFrom = "no-reply@lemursportal-wcs.org";

	@Autowired
	Configuration freemarkerConfiguration;

	@Override
	@Async
	public void sendMail(Thematique thematique, List<UserInfo> destinataires) {
		MimeMessagePreparator preparator = getMessagePreparator(thematique);
		sendMail(preparator);
	}

	@Override
	@Async
	public void sendMail(Post question, UserInfo owner, List<UserInfo> thematiqueManager) {
		MimeMessagePreparator preparator = getMessagePreparator(question);
		sendMail(preparator);

	}

	private void sendMail(MimeMessagePreparator mimeMessagePreparator) {
		try {
			mailSender.send(mimeMessagePreparator);
			System.out.println("Message has been sent.............................");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getMessagePreparator(final Thematique thematique) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject("Teste notification mail de Lemurs Portals");
				helper.setFrom(mailFrom);
				helper.setTo("mikajy401@gmail.com");

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("thematique", thematique);

				String text = geFreeMarkerQuestionTemplateContent(model);// Use
																	// Freemarker
																	// or
																	// Velocity
				System.out.println("Template content : " + text);

				// use the true flag to indicate you need a multipart message
				helper.setText(text, true);

				// Additionally, let's add a resource as an attachment as well.
				// helper.addAttachment("cutie.png", new
				// ClassPathResource("linux-icon.png"));

			}
		};
		return preparator;
	}

	private MimeMessagePreparator getMessagePreparator(final Post question) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject("Teste notification mail de Lemurs Portals");
				helper.setFrom(mailFrom);
				helper.setTo("mikajy401@gmail.com");

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("question", question);

				String text = geFreeMarkerThematiqueTemplateContent(model);// Use
																	// Freemarker
																	// or
																	// Velocity
				System.out.println("Template content : " + text);

				// use the true flag to indicate you need a multipart message
				helper.setText(text, true);

				// Additionally, let's add a resource as an attachment as well.
				// helper.addAttachment("cutie.png", new
				// ClassPathResource("linux-icon.png"));

			}
		};
		return preparator;
	}

	public String geFreeMarkerThematiqueTemplateContent(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate("fm_questionMailTemplate.txt"), model));
			return content.toString();
		} catch (Exception e) {
			System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
		}
		return "";
	}
	


	public String geFreeMarkerQuestionTemplateContent(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate("fm_thematiqueMailTemplate.txt"), model));
			return content.toString();
		} catch (Exception e) {
			System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
		}
		return "";
	}

}
