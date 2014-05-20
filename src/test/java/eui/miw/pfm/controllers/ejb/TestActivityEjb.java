/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jean Mubaied
 */
public class TestActivityEjb {
    
    private ActivityEjb activityEjb;
    
    @Before
    public void init() {
        this.activityEjb = new ActivityEjb();
    }
    
    @Test
    public void testObtainActivities() {
        System.out.println(activityEjb.obtainActivities());
        assertNotNull("listado de actividades",activityEjb.obtainActivities());
    }
}
