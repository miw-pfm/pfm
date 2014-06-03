/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.UserEntity;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fred Peña
 */
public class TestLogin {

    private UserEntity userEntity1;
    private UserEntity userEntity2;
    private UserEntity userEntity3;

    @Before
    public void init() {           
        
        userEntity1 = new UserEntity();
        userEntity2 = new UserEntity();
        userEntity3 = new UserEntity();
        
        userEntity1.setUsername("fred1");
        userEntity1.setPassword("123456789");
        userEntity1.setEmail("fred@gmail.com");
        userEntity1.setName("Fred");
        userEntity1.setSecondSurname("Pena");
        userEntity1.setSurname("Ant");

        userEntity2.setUsername("fred2");
        userEntity2.setPassword("123456789");
        userEntity2.setEmail("fred@gmail.com");
        userEntity2.setName("Fred");
        userEntity2.setSecondSurname("Pena");
        userEntity2.setSurname("Ant");

        userEntity3.setUsername("fred3");
        userEntity3.setPassword("123456789");
        userEntity3.setEmail("fred@gmail.com");
        userEntity3.setName("Fred");
        userEntity3.setSecondSurname("Pena");
        userEntity3.setSurname("Ant");

        AbstractDAOFactory.getFactory().getUserDAO().create(userEntity1);
        AbstractDAOFactory.getFactory().getUserDAO().create(userEntity2);
        AbstractDAOFactory.getFactory().getUserDAO().create(userEntity3);
    }

    @Test
    public void testLoginCorrect() {
        final LoginEjb loginEjb = new LoginEjb();
        String userName = "fred1";
        String password = "123456789";
        assertNotNull(loginEjb.findUser(userName, password));

        userName = "fred2";
        password = "123456789";
        assertNotNull(loginEjb.findUser(userName, password));

        userName = "fred3";
        password = "123456789";
        assertNotNull(loginEjb.findUser(userName, password));

        userName = "fred1";
        password = "1234567890";
        assertNull(loginEjb.findUser(userName, password));

        userName = "fred2";
        password = "1234567890";
        assertNull(loginEjb.findUser(userName, password));

        userName = "fred3";
        password = "1234567890";
        assertNull(loginEjb.findUser(userName, password));

    }

    @Test
    public void testLoginIncorrect() {
        final LoginEjb loginEjb = new LoginEjb();
        String userName = "fred1";
        String password = "1234567890";
        assertNull(loginEjb.findUser(userName, password));

        userName = "fred2";
        password = "1234567890";
        assertNull(loginEjb.findUser(userName, password));

        userName = "fred3";
        password = "1234567890";
        assertNull(loginEjb.findUser(userName, password));

        userName = "fred5";
        password = "123456789";
        assertNull(loginEjb.findUser(userName, password));

        userName = "fred5";
        password = "123456789";
        assertNull(loginEjb.findUser(userName, password));
    }

    @After
    public void finish() {
        AbstractDAOFactory.getFactory().getUserDAO().delete(userEntity1);
        AbstractDAOFactory.getFactory().getUserDAO().delete(userEntity2);
        AbstractDAOFactory.getFactory().getUserDAO().delete(userEntity3);
    }
}
