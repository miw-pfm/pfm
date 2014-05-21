package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.CalendarDAO;
import eui.miw.pfm.models.entities.CalendarEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.Calendar;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Jean Mubaied
 * @author Manuel Rodríguez
 */
public class JPACalendarDAO extends JPATransactionGenericDAO<CalendarEntity, Integer> implements CalendarDAO {

    public JPACalendarDAO() {
        super(CalendarEntity.class);
    }

    //Manuel Rodríguez
    @Override
    public CalendarEntity findHoliday(final ProjectEntity project, final Calendar holiday) {
        final Query query = entityManager.createQuery("SELECT ca FROM CalendarEntity ca WHERE ca.project = ?1 AND ca.holiday = ?2");
        query.setParameter(1, project);
        query.setParameter(2, holiday);

        final List results = query.getResultList();
        if (results.isEmpty()) {
            return null;//NOPMD
        } else if (results.size() == 1) {
            return (CalendarEntity) results.get(0);
        }
        throw new NonUniqueResultException();
    }

}
