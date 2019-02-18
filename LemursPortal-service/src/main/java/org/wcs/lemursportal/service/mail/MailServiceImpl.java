/**
 *
 */
package org.wcs.lemursportal.service.mail;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.wcs.lemursportal.model.mail.MailQueue;
import org.wcs.lemursportal.model.mail.MailQueueMimeMessagePreparator;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.mail.MailQueueRepository;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * @author mikajy.hery
 *
 */
@Service
@EnableScheduling
public class MailServiceImpl implements MailService {

    @Value("${mail.notification.question.subject:Nouvelle question}")
    private String sujetThematique;

    @Value("${mail.notification.thematique.subject:Nouvelle thématique}")
    private String sujetQuestion;

    @Resource(name = "mailSender")
    private JavaMailSender mailSender;

    @Value("${application.mail.from:no-reply@lemursportal-wcs.org}")
    private String mailFrom;

    @Autowired
    private MailQueueRepository mailQueueRepository;

    @Autowired
    Configuration freemarkerConfiguration;

    @Override
    @Async
    public void sendMail(Thematique thematique, List<UserInfo> destinataires, HashMap<String, String> postDetail) {
        MimeMessagePreparator preparator = getMessagePreparator(thematique, destinataires, postDetail);
        sendMail(preparator);
    }

    @Override
    @Async
    public void sendMail(Post question, UserInfo owner, List<UserInfo> destinataires) {
        MimeMessagePreparator preparator = getMessagePreparator(question, destinataires);
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

    private MimeMessagePreparator getMessagePreparator(final Thematique thematique, List<UserInfo> destinataires,
            final HashMap<String, String> postDetails) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject(sujetThematique);
                helper.setFrom(mailFrom);
//				helper.setTo("zacharie.rakotoarinivo@gmail.com");
                Set<String> tos = new HashSet<>();
                try {
                    for (UserInfo expert : thematique.getManagers()) {
                        tos.add(expert.getEmail());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                helper.setTo(tos.toArray(new String[0]));

                Map<String, Object> model = new HashMap<String, Object>();
//				model.put("thematique", thematique);
                model.put("title", postDetails.get("<h>"));
                model.put("title_post", postDetails.get("title"));
                model.put("text_post", postDetails.get("text"));
                model.put("link", postDetails.get("link"));

                String text = getPublicationMailTemplateContent(model);// Use
                // Freemarker
                // or
                // Velocity
                // use the true flag to indicate you need a multipart message
                helper.setText(text, true);

                // Additionally, let's add a resource as an attachment as well.
                // helper.addAttachment("cutie.png", new
                // ClassPathResource("linux-icon.png"));
            }
        };
        return preparator;
    }

    private MimeMessagePreparator getMessagePreparator(final Post question, final List<UserInfo> destinataires) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject(sujetQuestion);
                Set<String> tos = new HashSet<>();
                for (UserInfo expert : destinataires) {
                    tos.add(expert.getEmail());
                }
                helper.setTo(tos.toArray(new String[0]));
                helper.setCc(question.getOwner().getEmail());

                Map<String, Object> model = new HashMap<String, Object>();
                model.put("question", question);

                String text = geFreeMarkerThematiqueTemplateContent(model);// Use
                // Freemarker
                // or
                // Velocity
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
            return e.getMessage();
        }

    }

    public String geFreeMarkerQuestionTemplateContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(freemarkerConfiguration.getTemplate("fm_thematiqueMailTemplate.txt"), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public void saveMail(Thematique thematique, List<UserInfo> destinataires, String thematiqueUrl) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        String sujet = "[LemursPortal] Nouvelle thematique";
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("thematique", thematique);
        model.put("thematiqueUrl", thematiqueUrl);
        String body = FreeMarkerTemplateUtils
                .processTemplateIntoString(freemarkerConfiguration.getTemplate("fm_thematiqueMailTemplate.txt"), model);
        MailQueue mailQueue = new MailQueue();
        mailQueue.setBody(body);
        mailQueue.setSubject(sujet);
        StringBuilder destinataireSb = new StringBuilder();
        for (UserInfo dest : destinataires) {
            destinataireSb.append(dest.getEmail()).append(";");
        }
        mailQueue.setDestinataires(destinataireSb.toString());
        mailQueue.setCreationDate(new Date());
        mailQueue.setSent(false);
        mailQueueRepository.save(mailQueue);
    }

    @Override
    public void saveMail(Post question, UserInfo owner, List<UserInfo> thematiqueManager, String questionUrl) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        String sujet = "[LemursPortal] Nouvelle question";
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("question", question);
        model.put("questionUrl", questionUrl);
        String body = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("fm_questionMailTemplate.txt"), model);
        MailQueue mailQueue = new MailQueue();
        mailQueue.setBody(body);
        mailQueue.setSubject(sujet);
        StringBuilder destinataireSb = new StringBuilder();
        for (UserInfo dest : thematiqueManager) {
            destinataireSb.append(dest.getEmail()).append(";");
        }
        mailQueue.setDestinataires(destinataireSb.toString());
        mailQueue.setCreationDate(new Date());
        mailQueue.setSent(false);
        mailQueueRepository.save(mailQueue);
    }

//    @Scheduled(fixedDelay = 300000)//60*5*1000 = 5 minutes
//    @Transactional(noRollbackFor = MailException.class)
//    public void sendMails() {
//        List<MailQueue> mailQueues = mailQueueRepository.findAllNotSent();
//        for (MailQueue mailQueue : mailQueues) {
//            MailQueueMimeMessagePreparator preparator = new MailQueueMimeMessagePreparator(mailQueue);
//            try {
//                mailSender.send(preparator);
//                mailQueue.setSent(true);
//                mailQueue.setSentDate(new Date());
//            } catch (MailException e) {
//                mailQueue.setSendingErrors(e.getMessage());
//            }
//            mailQueueRepository.update(mailQueue);
//        }
//    }

    @Override
    public void sendEmail(SimpleMailMessage passwordResetEmail) {
        try {
            mailSender.send(passwordResetEmail);
            System.out.println("Message has been sent.............................");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }

    public String getPublicationMailTemplateContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(freemarkerConfiguration.getTemplate("fm_publicationMailTemplate.txt"), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
            return e.getMessage();
        }
    }

}
