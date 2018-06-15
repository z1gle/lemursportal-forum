package org.wcs.lemursportal.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wcs.lemursportal.dto.user.SocialProvider;
import org.wcs.lemursportal.dto.user.SocialUser;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;

/**
 *
 * @author z
 *
 */
public class SecurityUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    public static void authenticateUser(UserInfo user) {

//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = buildSimpleGrantedAuthorities(user);
//        SocialUser userDetails = new SocialUser(user.getLogin(), user.getLogin(), user.getPassword(), true, true, true, true, simpleGrantedAuthorities);
        SocialUser userDetails = SocialUser.getBuilder()
                .firstName(user.getNom())
                .id(user.getId())
                .lastName(user.getPrenom())
                .password(user.getPassword())
                .role(user.getRoles())
                .socialProvider(SocialProvider.valueOf(user.getProvider()))
                .username(user.getEmail())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("User: {} has been logged in.", user);
    }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final UserInfo user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (user.getRoles() != null) {
            for (UserType role : user.getRoles()) {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getLibelle()));
            }
        }
        return simpleGrantedAuthorities;
    }

    public static SocialProvider toSocialProvider(String providerId) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.toString().equalsIgnoreCase(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.NONE;
    }

//    public static void logInUser(UserInfo user) {
//        LOGGER.info("Logging in user: {}", user);
//
//        ExampleUserDetails userDetails = ExampleUserDetails.getBuilder()
//                .firstName(user.getFirstName())
//                .id(user.getId())
//                .lastName(user.getLastName())
//                .password(user.getPassword())
//                .role(user.getRole())
//                .socialSignInProvider(user.getSignInProvider())
//                .username(user.getEmail())
//                .build();
//        LOGGER.debug("Logging in principal: {}", userDetails);
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        LOGGER.info("User: {} has been logged in.", userDetails);
//    }
    public static String encrypte(String id) {
        Random rand = new Random();
        String step1 = RandomStringUtils.randomAlphanumeric(rand.nextInt(20));
        String step2 = RandomStringUtils.randomAlphanumeric(rand.nextInt(20));
//        String step3 = step1 + "!_!" + id + "!_!" + "321addceeecs!jjdislu" + "!_!" + step2;
//        return enc(step3);
        return step1 + "!_!" + enc(id) + "!_!" + "321addc" + RandomStringUtils.randomAlphanumeric(rand.nextInt(20)) + "cs!jjdislu" + "!_!" + step2;
    }

    private static String enc(String string) {
        String seed = "lemursPortalMakeOneSessionForAll";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);
        return encryptor.encrypt(string);
    }

    public static Integer decrypte(String code) {
//        String decode = dec(code);
        String decode = code;
        String[] liste = decode.split("!_!");
        String id = liste[1];
        return new Integer(dec(id));
    }

    private static String dec(String string) {
        String seed = "lemursPortalMakeOneSessionForAll";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);
        return encryptor.decrypt(string);
    }
}
