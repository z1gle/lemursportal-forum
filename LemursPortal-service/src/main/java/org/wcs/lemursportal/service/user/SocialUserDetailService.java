package org.wcs.lemursportal.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.wcs.lemursportal.dto.user.SocialUser;


/**
 * 
 * @author z
 *
 */
@Service("socialUserDetailService")
public class SocialUserDetailService implements SocialUserDetailsService {

//    @Autowired
//    @Qualifier(value = "localUserDetailService")
    private UserDetailsService userDetailService;
    
    public SocialUserDetailService(UserDetailsService userDetailsService) {
        this.userDetailService = userDetailsService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(final String userId) throws UsernameNotFoundException, DataAccessException {
//    	LocalUser user = (LocalUser) userDetailService.loadUserByUsername(userId);
    	UserDetails user = userDetailService.loadUserByUsername(userId);
        if (user == null) {
            throw new SocialAuthenticationException("No local user mapped with social user " + userId);
        }
        
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return (SocialUserDetails) user;
    }
}
