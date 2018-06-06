/**
 *
 */
package org.wcs.lemursportal.web.form;

/**
 * @author
 *
 */
public class ChangePasswordForm {
    private Integer id;
    private String newPassword;
    private String password;
    private String passwordConfirm;

    public ChangePasswordForm() {
    }

//    public ChangePasswordForm(String password, String newPassword, String passwordConfirm) {
//        this.newPassword = newPassword;
//        super.setPassword(password);
//        super.setPasswordConfirm(passwordConfirm);
//    }

    public ChangePasswordForm(String newPassword, String password, String passwordConfirm) {
        this.newPassword = newPassword;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public ChangePasswordForm(Integer id, String newPassword, String password, String passwordConfirm) {
        this.id = id;
        this.newPassword = newPassword;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }        
        
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
