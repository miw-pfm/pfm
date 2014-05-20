package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import java.util.List;

/**
 *
 * @author Manuel Álvarez
 */
public class IterationEjb {

    public void create(final IterationEntity iterationEntity) {
        AbstractDAOFactory.getFactory().getIterationDAO().create(iterationEntity);
    }

    public void delete(final IterationEntity iterationEntity) {
        AbstractDAOFactory.getFactory().getIterationDAO().delete(iterationEntity);
    }

    public void update(final IterationEntity iterationEntity) {
        AbstractDAOFactory.getFactory().getIterationDAO().update(iterationEntity);

    }

    public List<IterationEntity> getIterations() {
        return AbstractDAOFactory.getFactory().getIterationDAO().findAll();
    }
}
