package eui.miw.pfm.models.dao.interfaces;

import eui.miw.pfm.models.dao.TransactionGenericDAO;
import eui.miw.pfm.models.entities.CalendarEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.Calendar;

/**
 *
 * @author Jean Mubaied
 * @author Manuel Rodríguez
 */

public interface CalendarDAO extends TransactionGenericDAO<CalendarEntity, Integer>{ 
    CalendarEntity findHoliday(ProjectEntity project, Calendar holiday);
}
