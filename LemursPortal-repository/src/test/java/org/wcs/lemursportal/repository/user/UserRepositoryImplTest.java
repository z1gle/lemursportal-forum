/**
 * 
 */
package org.wcs.lemursportal.repository.user;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.repository.config.RepositoryTestConfig;

/**
 * @author mikajy.hery
 *
 */

@ActiveProfiles("tests")
@ContextConfiguration(classes=RepositoryTestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserRepositoryImplTest {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	private UserType admin, expert, moderateur, simpleuser;
	
	private Random random = new Random();
	
	ShaPasswordEncoder encoder = new ShaPasswordEncoder();
	UserType[] roles;
	@PostConstruct
	@Transactional
	public void initDatabase(){
		
		//Ajouter les roles
		//ADMIN
		admin = new UserType();
		admin.setId(random.nextInt());
		admin.setLibelle("ADMIN");
		admin.setDescription("Administrateur du site");
		userTypeRepository.insert(admin);
		//EXPERT
		expert = new UserType();
		expert.setId(random.nextInt());
		expert.setLibelle("EXPERT");
		expert.setDescription("Les experts !!");
		userTypeRepository.insert(expert);
		
		//MODERATEUR
		moderateur = new UserType();
		moderateur.setId(random.nextInt());
		moderateur.setLibelle("MODERATEUR");
		moderateur.setDescription("MODERATEUR");
		userTypeRepository.insert(moderateur);
		
		//SIMPLE UTILISATEUR
		simpleuser = new UserType();
		simpleuser.setId(random.nextInt());
		simpleuser.setLibelle("USER");
		simpleuser.setDescription("Simple Utilisateur");
		userTypeRepository.insert(simpleuser);
		
		roles = new UserType[]{admin, simpleuser, moderateur, expert};
		
	}
	
	@Test
	public void testCreateUserWithRole(){
		int nbUser = random.nextInt(100);
		int nbEnabledUser = 0;
		for(int i=0;i< nbUser; i++){
			UserInfo user = new UserInfo();
			user.setId(i);
			user.setLogin("monlogin " + i);
			user.setDateNaissance(new Date());
			boolean enabled = random.nextBoolean();
			user.setEnabled(enabled);
			user.setNom("nom " + i);
			user.setPassword(encoder.encodePassword("password " + i, "s-a-l-t"));
			user.setPrenom("Prenom " + i);
			int roleIndex = random.nextInt(4);
			user.setRoles(new HashSet<>(Arrays.asList(roles[roleIndex])));
			userRepository.insert(user);
			if(enabled){
				nbEnabledUser++;
			}
		}
		List<UserInfo> users = userRepository.findAll();
		org.junit.Assert.assertEquals(nbEnabledUser, users.size());
	}
	
	@Test
	public void testCreateUserWithSameLoginError(){
		String login = "monlogin";
		for(int i=0;i< 2; i++){
			UserInfo user = new UserInfo();
			user.setId(random.nextInt());
			user.setLogin(login);
			user.setDateNaissance(new Date());
			user.setEnabled(true);
			user.setNom("nom " + random.nextInt());
			user.setPassword(encoder.encodePassword("password " + random.nextInt(), "s-a-l-t"));
			user.setPrenom("Prenom " + random.nextInt());
			int roleIndex = random.nextInt(4);
			user.setRoles(new HashSet<>(Arrays.asList(roles[roleIndex])));
			userRepository.insert(user);
		}
		org.junit.Assert.assertEquals(2, userRepository.findAll().size());
	}
}
