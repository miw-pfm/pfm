package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPADAOFactory extends AbstractDAOFactory {

    private static final String PERSISTENCE_UNIT = "jpa";

    private transient EntityManager entityManager;

    public JPADAOFactory() {
        super();
        this.entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
    }

    public EntityManager getEntityManager() {
        if (!entityManager.isOpen()) {
            this.entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT)
                    .createEntityManager();
        }
        return entityManager;
    }

    @Override
    public ProjectDAO getProjectDAO() {
  	return new JPAProjectDAO();
    }

}
