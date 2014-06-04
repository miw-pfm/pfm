/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author William
 */
public class TestDiscipline{ 
    
    @Test
    public void testObtainAllDisciplinesDAO() {
        assertNotNull("listado de diciplinas", new DisciplineEjb().findAll());
    }
    
}
