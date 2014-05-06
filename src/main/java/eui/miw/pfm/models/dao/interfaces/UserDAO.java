package eui.miw.pfm.models.dao.interfaces;

import eui.miw.pfm.models.dao.TransactionGenericDAO;
import eui.miw.pfm.models.entities.UserEntity;

/**
 *
 * @author Fred Peña
 */
public interface UserDAO extends TransactionGenericDAO<UserEntity, Integer> {

    UserEntity findUser(String nameUser, String password);
}
