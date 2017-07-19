package org.wcs.lemursportal.dto.user;

import java.util.Date;

/**
 * 
 * @author z
 *
 */
public class UserRegistrationForm {

    private String userId;
    private String nom;
    private String prenom;
    private String phoneno;
    private String email;
    private String password;
    private SocialProvider socialProvider;
    private String imageUrl;
    private Date dateNaissance;

    public UserRegistrationForm() {
    }

    public UserRegistrationForm(final String userId, final String nom, final String prenom, final String phoneno, final String email, final String password, final SocialProvider socialProvider, final String imageUrl, final Date dateNaissance) {
        this.userId = userId;
        this.nom = nom;
        this.prenom = prenom;
        this.phoneno = phoneno;
        this.email = email;
        this.password = password;
        this.socialProvider = socialProvider;
        this.imageUrl = imageUrl;
        this.dateNaissance = dateNaissance;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(final String phoneno) {
        this.phoneno = phoneno;
    }

    public SocialProvider getSocialProvider() {
        return socialProvider;
    }

    public void setSocialProvider(final SocialProvider socialProvider) {
        this.socialProvider = socialProvider;
    }
    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public static class Builder {
        private String userId;
        private String nom;
        private String prenom;
        private String phoneno;
        private String email;
        private String password;
        private SocialProvider socialProvider;
        private String imageUrl;
        private Date dateNaissance;

        public Builder addUserId(final String userId) {
            this.userId = userId;
            return this;
        }

        public Builder addNom(final String nom) {
            this.nom = nom;
            return this;
        }

        public Builder addPrenom(final String prenom) {
            this.prenom = prenom;
            return this;
        }

        public Builder addEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder addPassword(final String password) {
            this.password = password;
            return this;
        }

        public Builder addPhoneNo(final String phoneno) {
            this.phoneno = phoneno;
            return this;
        }

        public Builder addSocialProvider(final SocialProvider socialProvider) {
            this.socialProvider = socialProvider;
            return this;
        }
        
        public Builder addImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder addDateNaissance(final Date dateNaissance) {
        	this.dateNaissance = dateNaissance;
        	return this;
        }
    	
        public UserRegistrationForm build() {
            return new UserRegistrationForm(userId, nom, prenom, phoneno, email, password, socialProvider, imageUrl, dateNaissance);
        }
    }
}
