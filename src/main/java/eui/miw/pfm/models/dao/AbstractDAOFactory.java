package eui.miw.pfm.models.dao;

import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.jpa.JPADAOFactory;
import org.apache.log4j.Logger;

public abstract class AbstractDAOFactory {

    public static AbstractDAOFactory factory = null;

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
}
