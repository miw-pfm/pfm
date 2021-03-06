/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jean Mubaied
 * @author Jose Mª Villar
 */
public class TestActivities {

    private transient ActivitiesEjb activitiesEjb;
    private transient ActivityEntity activity;

    @Before
    public void init() {
        this.activitiesEjb = new ActivitiesEjb();

        this.activity = new ActivityEntity(9, "Control de Cambios asignable", "C");
        final SubActivityEntity subActivity1 = new SubActivityEntity(37, "Plan de Control de Cambios", "C1", activity);
        final SubActivityEntity subActivity2 = new SubActivityEntity(38, "Gestión de Cambios en Iteración", "C2", activity);

        this.activity.addSubActivity(subActivity1);
        this.activity.addSubActivity(subActivity2);
    }

    @Test
    public void testObtainAllActivities() {
        assertNotNull("listado de actividades", activitiesEjb.obtainAllActivities());

    }

    @Test
    public void testObtainActivity() {
        assertEquals("es la misma actividad", activitiesEjb.obtainActivity(this.activity.getId()), this.activity);
    }

    @Test
    public void testObtainAllSubActivities() {
        assertNotNull("listado de subactividades", activitiesEjb.obtainAllSubActivities());
    }

    @Test
    public void testObtainAllSubActivitiesByActivity() {
        assertTrue("listado de subactividades de una actividad", activitiesEjb.obtainSubActivities(activitiesEjb.obtainActivity(this.activity.getId())).containsAll(this.activity.getSubActivity()));
    }
}
