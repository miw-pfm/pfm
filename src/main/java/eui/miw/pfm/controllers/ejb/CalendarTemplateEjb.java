/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.CalendarTemplateDAO;
import eui.miw.pfm.models.entities.CalendarTemplateEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.List;

/**
 *
 * @author Manuel Rodriguez
 */
public class CalendarTemplateEjb {
    public List<CalendarTemplateEntity> obtainHolidays() {        
        CalendarTemplateDAO calendarTemplateDAO = AbstractDAOFactory.getFactory().getCalendarTemplateDAO();        
        return calendarTemplateDAO.findAll();
    }
}
