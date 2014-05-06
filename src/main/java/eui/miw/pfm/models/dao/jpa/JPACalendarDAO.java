package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.CalendarDAO;
import eui.miw.pfm.models.entities.CalendarEntity;

/**
 *
 * @author Jean Mubaied
 */

public class JPACalendarDAO extends JPATransactionGenericDAO<CalendarEntity, Integer> implements CalendarDAO{

    public JPACalendarDAO(){
       super(CalendarEntity.class);
    }
}

