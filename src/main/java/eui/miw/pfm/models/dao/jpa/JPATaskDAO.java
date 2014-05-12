package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.TaskDAO;
import eui.miw.pfm.util.moks.entities.TasksEntityMock;

/**
 *
 * @author Fred Peña
 * @author Clemencio Morales
 * @author Pepe Bustamante
 */
public class JPATaskDAO extends JPATransactionGenericDAO<TasksEntityMock, Integer> implements TaskDAO {

    public JPATaskDAO() {
        super(TasksEntityMock.class);
    }
}
