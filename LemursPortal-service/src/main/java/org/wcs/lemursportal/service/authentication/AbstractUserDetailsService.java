package org.wcs.lemursportal.service.authentication;



import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.wcs.lemursportal.data.authentication.IUserInfo;
import org.wcs.lemursportal.data.user.UserInfo;
import org.wcs.lemursportal.data.user.UserType;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 * @param <U>
 */
public abstract class AbstractUserDetailsService<U extends IUserInfo> implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)	throws UsernameNotFoundException {
		UserInfo account = this.findUserByLogin(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}

	public void signin(UserInfo account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private User createUser(UserInfo userInfo) {
		return new User(userInfo.getLogin(), userInfo.getPassword(), userInfo.getEnabled(), true, true, true, getUserAuthorities(userInfo));
	}
	
	private Authentication authenticate(UserInfo userInfo) {
		return new UsernamePasswordAuthenticationToken(createUser(userInfo), null,  getUserAuthorities(userInfo));		
	}
	
	/**
	 * Récupération information sur l'utilisateur à partir de son login
	 * @param username
	 * @return
	 */
	protected abstract UserInfo findUserByLogin(String login);
	

	private Collection<GrantedAuthority> getUserAuthorities(UserInfo userInfo) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(UserType role: userInfo.getRoles()){
			String rolePrefixed = role.getLibelle();
			if(!rolePrefixed.toUpperCase().startsWith("ROLE_")){
				rolePrefixed = "ROLE_" + role.getLibelle().toUpperCase();
			}
			grantedAuthorities.add(new SimpleGrantedAuthority(rolePrefixed));
		}
		return grantedAuthorities;
	}
}
