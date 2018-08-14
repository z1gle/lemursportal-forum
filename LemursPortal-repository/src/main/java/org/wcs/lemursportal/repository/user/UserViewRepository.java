package org.wcs.lemursportal.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.user.UserView;

/**
 * @author Zacharie <r.haritiana.z@gmail.com>
 *
 */
@Transactional
@Repository
public interface UserViewRepository extends JpaRepository<UserView, Integer>{	
    public UserView findByIdUser(Integer idUser);
}
