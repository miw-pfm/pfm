/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.CalendarEntity;
import eui.miw.pfm.models.entities.CalendarTemplateEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Jean Mubaied
 * @author Manuel Rodríguez
 *
 */
public class CalendarProjectEjb {

    public void update(final CalendarEntity calendar) {
        AbstractDAOFactory.getFactory().getCalendarDAO().update(calendar);
    }

    public void delete(final CalendarEntity calendar) {
        AbstractDAOFactory.getFactory().getCalendarDAO().delete(calendar);
    }

    public void create(final CalendarEntity calendar) {
        AbstractDAOFactory.getFactory().getCalendarDAO().create(calendar);
    }

    public List<CalendarEntity> obtainHolidays(final ProjectEntity project) {
        final String psql = "SELECT ca FROM CalendarEntity ca WHERE ca.project = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getCalendarDAO().find(psql, new Object[]{project});
    }

    //Manuel Rodríguez
    public CalendarEntity obtainHoliday(final ProjectEntity project, final Calendar holiday) {
        return AbstractDAOFactory.getFactory().getCalendarDAO().findHoliday(project, holiday);
    }

    //Manuel Rodríguez
    public List<CalendarTemplateEntity> obtainHolidays() {
        return AbstractDAOFactory.getFactory().getCalendarTemplateDAO().findAll();
    }

}
