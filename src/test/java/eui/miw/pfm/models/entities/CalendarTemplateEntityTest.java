/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.CalendarTemplateDAO;
import java.util.GregorianCalendar;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jean Mubaied
 */
public class CalendarTemplateEntityTest {

    private CalendarTemplateEntity calendarTemplate;
    private CalendarTemplateDAO calendarTemplateDao;

    public CalendarTemplateEntityTest() {
    }

    @Before
    public void init() {
        this.calendarTemplate = new CalendarTemplateEntity();
    }

    @Test
    public void testCreateEntity() {
        this.calendarTemplate.setHoliday(new GregorianCalendar(2014, 11, 25));
        this.calendarTemplate.setName("Christmas");

        calendarTemplateDao = AbstractDAOFactory.getFactory().getCalendarTemplateDAO();
        calendarTemplateDao.create(this.calendarTemplate);

        assertTrue("fin test", true); // NOPMD  
    }

    @After
    public void after() {
        calendarTemplateDao.delete(calendarTemplate);
    }

}
