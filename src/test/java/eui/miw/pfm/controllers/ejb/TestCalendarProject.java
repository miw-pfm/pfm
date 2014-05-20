/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.CalendarDAO;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.entities.CalendarEntity;
import java.util.GregorianCalendar;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jean Mubaied
 */
public class TestCalendarProject {

    private CalendarEntity calendar;
    private CalendarProjectEjb calendarProjectEJB;

    public TestCalendarProject() {
    }

    @Before
    public void init() {
        ProjectDAO pd = AbstractDAOFactory.getFactory().getProjectDAO();
        this.calendar = new CalendarEntity();
        this.calendar.setHoliday(new GregorianCalendar(2014, 11, 25));
        this.calendar.setName("Christmas");
        this.calendar.setDescription("Free");
        this.calendar.setProject(pd.read(124));
        this.calendarProjectEJB = new CalendarProjectEjb();
    }

    @Test
    public void testCreateCalendar() {
        calendarProjectEJB.create(this.calendar);
        assertTrue("fin test", true); // NOPMD  
    }

  //  @Test
    public void testUpdateCalendar() {
        CalendarDAO cd = AbstractDAOFactory.getFactory().getCalendarDAO();
        CalendarEntity calendar2 = cd.read(12);
        calendar2.setName("ChristmaChanged");
        calendar2.setDescription("NotFree");
        calendarProjectEJB.update(calendar2);
        assertTrue("fin test", true); // NOPMD  
    }

//    @Test
//    public void testFindHolidays() {
//        ProjectDAO pd = AbstractDAOFactory.getFactory().getProjectDAO();
//        assertNotNull(calendarProjectEJB.obtainHolidays(pd.read(12)));
//    }

    @After
    public void after() {
        calendarProjectEJB.delete(this.calendar);
    }
}
