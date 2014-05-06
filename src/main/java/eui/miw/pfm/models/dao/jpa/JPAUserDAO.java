package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public class JPAUserDAO extends JPATransactionGenericDAO<UserEntity, Integer> implements UserDAO {

    public JPAUserDAO() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity findUser(String nameUser, String password) {
        Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = ?1 AND u.password = ?2");
        query.setParameter(1, nameUser);
        query.setParameter(2, password);

        List results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return (UserEntity) results.get(0);
        }
        throw new NonUniqueResultException();
    }
}
