/**
 * 
 */
package org.wcs.lemursportal.repository.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.mail.TokenUser;

/**
 * @author zacharie
 *
 */
@Repository
@Transactional
public interface TokenUserRepository extends JpaRepository<TokenUser, Integer>{
    public TokenUser findByToken(String token);
}
