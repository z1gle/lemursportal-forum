package org.wcs.lemursportal.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.dto.user.SocialProvider;
import org.wcs.lemursportal.dto.user.SocialUser;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.repository.user.UserRepository;
import org.wcs.lemursportal.service.authentication.AuthenticationService;


/**
 * 
 * @author z
 *
 */
@Service("localUserDetailService")
public class LocalUserDetailService implements AuthenticationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalUserDetailService.class); 

    private UserRepository userRepository;
    
    @Autowired
    public LocalUserDetailService(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userId) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByEmail(userId);
        if (user == null) {
            return null;
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = buildSimpleGrantedAuthorities(user);
        
        SocialUser principal = SocialUser.getBuilder()
                .firstName(user.getNom())
                .id(user.getId())
                .lastName(user.getPrenom())
                .password(user.getPassword())
                .role(user.getRoles())
                .socialProvider(user.getProvider()==null?SocialProvider.NONE:SocialProvider.valueOf(user.getProvider()))
                .username(user.getEmail())
                .build();
        
        return principal;
    }

    private List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final UserInfo user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (user.getRoles() != null) {
            for (UserType role : user.getRoles()) {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getLibelle()));
            }
        }
        return simpleGrantedAuthorities;
    }

    @Autowired
	private AuthenticationManager authenticationManager;
    
    @Override
    public void autoLogin(String email, String password, HttpServletRequest request) {
		UserDetails userDetails = this.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        request.getSession();//créer une session si ce n'est pas déjà fait
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            LOGGER.debug(String.format("Auto login '%s' successfully!", email));
        } else{
        	LOGGER.debug(String.format("Auto login '%s' failed!", email));
        }
		
	}

}
