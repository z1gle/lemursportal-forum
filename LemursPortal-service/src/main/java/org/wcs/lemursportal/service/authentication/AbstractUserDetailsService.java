//package org.wcs.lemursportal.service.authentication;
//
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.wcs.lemursportal.model.authentication.IUserInfo;
//import org.wcs.lemursportal.model.authentication.UserRole;
//import org.wcs.lemursportal.model.user.UserInfo;
//import org.wcs.lemursportal.model.user.UserType;
//
///**
// * @author Mikajy <mikajy401@gmail.com>
// *
// * @param <U>
// */
//public abstract class AbstractUserDetailsService<U extends IUserInfo> implements UserDetailsService {
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUserDetailsService.class);
//
//	@Override
//	public UserDetails loadUserByUsername(String username)	throws UsernameNotFoundException {
//		LOGGER.debug("Find user with login :" + username);
//		UserInfo account = this.findUserByLogin(username);
//		if(account == null) {
//			throw new UsernameNotFoundException("user not found");
//		}
//		return createUser(account);
//	}
//
//	public void signin(UserInfo account) {
//		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
//	}
//	
//	private User createUser(UserInfo userInfo) {
//		return new User(userInfo.getLogin(), userInfo.getPassword(), userInfo.getEnabled(), true, true, true, getUserAuthorities(userInfo));
//	}
//	
//	private Authentication authenticate(UserInfo userInfo) {
//		return new UsernamePasswordAuthenticationToken(createUser(userInfo), null,  getUserAuthorities(userInfo));		
//	}
//	
//	/**
//	 * Récupération information sur l'utilisateur à partir de son login
//	 * @param username
//	 * @return
//	 */
//	protected abstract UserInfo findUserByLogin(String login);
//	
//	private Collection<GrantedAuthority> getUserAuthorities(UserInfo userInfo) {
//		StringBuilder sb = new StringBuilder("User Authorities : [");
//		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//		Set<UserType> roles = userInfo.getRoles();
//		if(roles != null){
//			for(UserType role: roles){
//				String rolePrefixed = role.getLibelle();
//				if(!rolePrefixed.toUpperCase().startsWith("ROLE_")){
//					rolePrefixed = "ROLE_" + role.getLibelle().toUpperCase();
//				}
//				sb.append(rolePrefixed).append(",");
//				grantedAuthorities.add(new SimpleGrantedAuthority(rolePrefixed));
//			}
//		}
//		if(grantedAuthorities.isEmpty()){
//			//Quand il n'y a pas de role definit par defaut pour l'utilisateur, 
//			//on lui attribue le role SIMPLE_UTILISATEUR par defaut
//			grantedAuthorities.add(new SimpleGrantedAuthority(UserRole.SIMPLE_USER.getRole()));
//			sb.append(UserRole.SIMPLE_USER.getRole()).append("(default)");
//		}
//		sb.append("]");
//		LOGGER.debug(sb.toString());
//		return grantedAuthorities;
//	}
//	
//	
//}
