package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ActivityDAO;
import eui.miw.pfm.models.dao.interfaces.SubActivityDAO;
import eui.miw.pfm.models.dao.interfaces.CalendarDAO;
import eui.miw.pfm.models.dao.interfaces.CalendarTemplateDAO;
import eui.miw.pfm.models.dao.interfaces.IterationDAO;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.RiskDAO;
import eui.miw.pfm.models.dao.interfaces.TaskDAO;
import eui.miw.pfm.models.dao.interfaces.UseCaseDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.dao.interfaces.WorkUnitDAO;
import eui.miw.pfm.models.dao.interfaces.WorkerDAO;
import eui.miw.pfm.models.dao.interfaces.WorkUnitDAO;
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

    @Override
    public UserDAO getUserDAO() {
        return new JPAUserDAO();
    }

    @Override
    public UseCaseDAO getUseCaseDAO() {
        return new JPAUseCaseDAO();
    }

    @Override
    public CalendarDAO getCalendarDAO() {
        return new JPACalendarDAO();
    }

    @Override
    public CalendarTemplateDAO getCalendarTemplateDAO() {
        return new JPACalendarTemplateDAO();
    }

    @Override
    public WorkerDAO getWorkerDAO() {
        return new JPAWorkerDAO();
    }

    @Override
    public TaskDAO getTaskDAO() {
        return new JPATaskDAO();
    }

    @Override
    public RiskDAO getRiskDAO() {
        return new JPARiskDAO();
    }


    @Override
    public ActivityDAO getActivityDAO() {
        return new JPAActivityDAO();
    }
    
    @Override
    public SubActivityDAO getSubActivityDAO() {
        return new JPASubActivityDAO();
    }

    @Override
    public IterationDAO getIterationDAO() {
        return new JPAIterationDAO();
    }

    @Override
    public WorkUnitDAO getWorkUnitDAO() {
       return new JPAWorkUnitDAO();
    }
}
