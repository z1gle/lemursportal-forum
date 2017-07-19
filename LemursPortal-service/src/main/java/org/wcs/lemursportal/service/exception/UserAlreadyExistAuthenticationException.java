package org.wcs.lemursportal.service.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * @author z
 *
 */
public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

}
