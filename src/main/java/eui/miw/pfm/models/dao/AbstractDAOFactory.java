package eui.miw.pfm.models.dao;

import eui.miw.pfm.models.dao.interfaces.ActivityDAO;
import eui.miw.pfm.models.dao.interfaces.CalendarDAO;
import eui.miw.pfm.models.dao.interfaces.CalendarTemplateDAO;
import eui.miw.pfm.models.dao.interfaces.IterationDAO;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.RiskDAO;
import eui.miw.pfm.models.dao.interfaces.TaskDAO;
import eui.miw.pfm.models.dao.interfaces.UseCaseDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.dao.interfaces.WorkerDAO;
import eui.miw.pfm.models.dao.jpa.JPADAOFactory;
import org.apache.log4j.Logger;

public abstract class AbstractDAOFactory {

    private static AbstractDAOFactory factory = null;

    public static void setFactory(final AbstractDAOFactory factory) {
        AbstractDAOFactory.factory = factory;
    }

    public static AbstractDAOFactory getFactory() {
        if (AbstractDAOFactory.factory == null) {
            AbstractDAOFactory.factory = new JPADAOFactory();
            Logger.getLogger(AbstractDAOFactory.class).info(
                    "DAOFactory: " + AbstractDAOFactory.factory.getClass().getSimpleName());
        }
        return AbstractDAOFactory.factory;
    }

    public abstract ProjectDAO getProjectDAO();

    public abstract UserDAO getUserDAO();

    public abstract UseCaseDAO getUseCaseDAO();

    public abstract CalendarDAO getCalendarDAO();

    public abstract CalendarTemplateDAO getCalendarTemplateDAO();

    public abstract WorkerDAO getWorkerDAO();

    public abstract TaskDAO getTaskDAO();

    public abstract RiskDAO getRiskDAO();
    
    public abstract ActivityDAO getActivityDAO();

    public abstract IterationDAO getIterationDAO();
}
