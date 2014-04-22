/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.entities;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author ro
 */
public class UserEntityTest {
    
    private UserEntity user;
    
    public UserEntityTest() {
    }
    
    @Before
    public void init() {
       this.user = new UserEntity();
    }
    

    @Test
    public void testCreateEntity(){
        this.user.setName("Pepe");
        this.user.setPassword("1234");
        this.user.setUsername("pepe23");
        this.user.setEmail("pepe@pepe.com");
        this.user.setSurename("lopez");
        this.user.setSecondSurename("guti");
        
        UserDAO userDAO;
        userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        userDAO.create(this.user);
        
        assertTrue("fin test",true); // NOPMD
        
    }
    /**
     * Test of getId method, of class UserEntity.
     */
//    @Test
//    public void testGetId() {
//        System.out.println("getId");
//        UserEntity instance = new UserEntity();
//        Long expResult = null;
//        Long result = instance.getId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setId method, of class UserEntity.
//     */
//    @Test
//    public void testSetId() {
//        System.out.println("setId");
//        Long id = null;
//        UserEntity instance = new UserEntity();
//        instance.setId(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hashCode method, of class UserEntity.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        UserEntity instance = new UserEntity();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class UserEntity.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object object = null;
//        UserEntity instance = new UserEntity();
//        boolean expResult = false;
//        boolean result = instance.equals(object);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class UserEntity.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        UserEntity instance = new UserEntity();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
