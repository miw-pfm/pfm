package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.UserEntity;

public class JPAUserDAO extends JPATransactionGenericDAO<UserEntity, Integer> implements UserDAO{

    public JPAUserDAO() {
        super(UserEntity.class);
    }
    
}
