package org.wcs.lemursportal.dto.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.wcs.lemursportal.model.user.UserType;

/**
 * 
 * @author z
 *
 */
public class SocialUser extends org.springframework.social.security.SocialUser {

//    private String userId;
//
//    public SocialUser(final String userId, final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//        this.userId = userId;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
	
	private Integer id;

    private String firstName;

    private String lastName;

    private Set<UserType> role;

    private SocialProvider socialProvider;

    public SocialUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<UserType> getRole() {
        return role;
    }

    public SocialProvider getSocialSignInProvider() {
        return socialProvider;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id: " + id)
                .append(", username: " + getUsername())
                .append(", firstName: " + firstName)
                .append(", lastName: " + lastName)
                .append(", role: " + role)
                .append(", socialProvider: " + socialProvider)
                .toString();
    }

    public static class Builder {

        private Integer id;

        private String username;

        private String firstName;

        private String lastName;

        private String password;

        private Set<UserType> role;

        private SocialProvider socialProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder role(Set<UserType> role) {
            this.role = role;
            
            if(role != null){
    			for(UserType roles: role){
    				String rolePrefixed = roles.getLibelle();
    				if(!rolePrefixed.toUpperCase().startsWith("ROLE_")){
    					rolePrefixed = "ROLE_" + roles.getLibelle().toUpperCase();
    				}
    				 this.authorities.add(new SimpleGrantedAuthority(rolePrefixed));
    			}
    		}

            return this;
        }

        public Builder socialProvider(SocialProvider socialSignInProvider) {
            this.socialProvider = socialSignInProvider;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public SocialUser build() {
        	SocialUser user = new SocialUser(username, password, authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.socialProvider = socialProvider;

            return user;
        }
    }
}
