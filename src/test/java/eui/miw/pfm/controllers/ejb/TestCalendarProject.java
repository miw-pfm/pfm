/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.CalendarEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jean Mubaied
 */
public class TestCalendarProject {

    private static CalendarEntity calendar1;
    private static CalendarEntity calendar2;
    private static CalendarEntity calendar3;
    private static UserEntity user;
    private static ProjectEntity project;

    @BeforeClass
    public static void before() {

        user = new UserEntity();
        user.setName("usuario");
        user.setEmail("pepe@gmail.com");
        user.setPassword("123456789");
        user.setSecondSurname("Pepe");
        user.setSurname("Pepe");
        user.setUsername("pepe");
        AbstractDAOFactory.getFactory().getUserDAO().create(user);

        project = new ProjectEntity();
        project.setName("Projecto prueba Calendar");
        project.setChosenNumIteration(1);
        project.setDescription("Desc");
        project.setEndDate(new Date());
        project.setEstimatedNumIteration(1);
        project.setStartDate(new Date());
        project.setStringEndDate("123456");
        project.setWeekNumIteration(2);
        project.setOwner(user);
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);

    }

    private void create() {
        final CalendarProjectEjb cProjectEjb = new CalendarProjectEjb();

        calendar1 = new CalendarEntity();
        calendar1.setHoliday(new GregorianCalendar(2014, 10, 25));
        calendar1.setName("Christmas1");
        calendar1.setDescription("Free1");
        calendar1.setProject(project);

        calendar2 = new CalendarEntity();
        calendar2.setHoliday(new GregorianCalendar(2014, 11, 25));
        calendar2.setName("Christmas2");
        calendar2.setDescription("Free2");
        calendar2.setProject(project);

        calendar3 = new CalendarEntity();
        calendar3.setHoliday(new GregorianCalendar(2014, 12, 25));
        calendar3.setName("Christmas3");
        calendar3.setDescription("Free3");
        calendar3.setProject(project);

        cProjectEjb.create(calendar1);
        cProjectEjb.create(calendar2);
        cProjectEjb.create(calendar3);
    }

    @Test
    public void testCreate() {
        create();

        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar1.getId()).equals(calendar1));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar2.getId()).equals(calendar2));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar3.getId()).equals(calendar3));
    }

    @Test
    public void testDelete() {
        assertNotNull("No existe el Calendar 1 antes de borrar", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar1.getId()));
        assertNotNull("No existe el Calendar 2 antes de borrar", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar2.getId()));
        assertNotNull("No existe el Calendar 3 antes de borrar", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar3.getId()));

        final CalendarProjectEjb cProjectEjb = new CalendarProjectEjb();
        cProjectEjb.delete(calendar1);
        cProjectEjb.delete(calendar2);
        cProjectEjb.delete(calendar3);

        assertNull("Existe el Calendar 1 antes de borrar", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar1.getId()));
        assertNull("Existe el Calendar 2 antes de borrar", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar2.getId()));
        assertNull("Existe el Calendar 3 antes de borrar", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar3.getId()));
    }

    @Test
    public void testUpdate() {
        create();

        final CalendarProjectEjb cProjectEjb = new CalendarProjectEjb();

        calendar1.setHoliday(new GregorianCalendar(2014, 3, 25));
        calendar1.setName("Christmas10");
        calendar1.setDescription("Free10");
        calendar1.setProject(project);

        calendar2.setHoliday(new GregorianCalendar(2014, 2, 25));
        calendar2.setName("Christmas20");
        calendar2.setDescription("Free20");
        calendar2.setProject(project);

        calendar3.setHoliday(new GregorianCalendar(2014, 1, 25));
        calendar3.setName("Christmas30");
        calendar3.setDescription("Free30");
        calendar3.setProject(project);

        cProjectEjb.update(calendar1);
        cProjectEjb.update(calendar2);
        cProjectEjb.update(calendar3);

        assertTrue("ERROR updating name 1", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar1.getId()).getName().equals(calendar1.getName()));
        assertTrue("ERROR updating name 2", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar2.getId()).getName().equals(calendar2.getName()));
        assertTrue("ERROR updating name 3", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar3.getId()).getName().equals(calendar3.getName()));

        assertTrue("ERROR updating holiday 1", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar1.getId()).getHoliday().equals(calendar1.getHoliday()));
        assertTrue("ERROR updating holiday 1", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar2.getId()).getHoliday().equals(calendar2.getHoliday()));
        assertTrue("ERROR updating holiday 1", AbstractDAOFactory.getFactory().getCalendarDAO().read(calendar3.getId()).getHoliday().equals(calendar3.getHoliday()));

        cProjectEjb.delete(calendar1);
        cProjectEjb.delete(calendar2);
        cProjectEjb.delete(calendar3);
    }

    @Test
    public void testFind() {
        create();

        final CalendarProjectEjb cProjectEjb = new CalendarProjectEjb();

        assertTrue("ERROR find 1", cProjectEjb.obtainHolidays(project).contains(calendar1));
        assertTrue("ERROR find 2", cProjectEjb.obtainHolidays(project).contains(calendar2));
        assertTrue("ERROR find 3", cProjectEjb.obtainHolidays(project).contains(calendar3));

        cProjectEjb.delete(calendar1);
        cProjectEjb.delete(calendar2);
        cProjectEjb.delete(calendar3);

    }

    @AfterClass
    public static void after() {
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);
    }
}
