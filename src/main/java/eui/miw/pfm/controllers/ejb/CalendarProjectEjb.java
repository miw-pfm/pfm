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

    public void update(final CalendarEntity calendarEntity) {
        AbstractDAOFactory.getFactory().getCalendarDAO().update(calendarEntity);
    }

    public void delete(final CalendarEntity calendarEntity) {
        AbstractDAOFactory.getFactory().getCalendarDAO().delete(calendarEntity);
    }

    public void create(final CalendarEntity calendarEntity) {
        AbstractDAOFactory.getFactory().getCalendarDAO().create(calendarEntity);
    }

    public List<CalendarEntity> obtainHolidays(final ProjectEntity project) {
        final String psql = "SELECT ca FROM CalendarEntity ca WHERE ca.project = ?1";//NOPMD
        return AbstractDAOFactory.getFactory().getCalendarDAO().find(psql, project);
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
