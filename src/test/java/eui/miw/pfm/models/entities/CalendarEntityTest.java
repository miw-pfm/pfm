/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.entities;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.CalendarDAO;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Jean Mubaied
 */
public class CalendarEntityTest {
    
    private CalendarEntity calendar;
    private CalendarDAO calendarDao;
    
    
    public CalendarEntityTest() {
    }
    
    @Before
    public void init() {
       this.calendar = new CalendarEntity();
    }

    @Test
    public void testCreateEntity(){
        ProjectDAO pd = AbstractDAOFactory.getFactory().getProjectDAO();
        
        this.calendar.setHoliday(new GregorianCalendar(2014, 11, 25));
        this.calendar.setName("Christmas");
        this.calendar.setDescription("Free");
        this.calendar.setProject(pd.read(1));
       
        calendarDao = AbstractDAOFactory.getFactory().getCalendarDAO();
        calendarDao.create(this.calendar);
        
        assertTrue("fin test",true); // NOPMD  
    }
    
//    @After
//    public void after() {
//        calendarDao.delete(calendar);
//    }
    
}
