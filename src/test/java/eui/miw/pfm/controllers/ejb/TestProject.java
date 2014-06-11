/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jose Mª Villar
 */
public class TestProject {

    private transient UserEntity user;
    private transient ProjectEntity project;
    private transient ProjectEjb projectEjb;
    
    @Before
    public void init() {
        user = new UserEntity();
        user.setName("usuario");
        AbstractDAOFactory.getFactory().getUserDAO().create(user); 
        
        project = new ProjectEntity();
        project.setName("Nuevo Projecto Prueba");
        project.setOwner(user);
        
        projectEjb = new ProjectEjb();        
    }
    
    @Test
    public void testCreate() {        
        projectEjb.createProject(project);
        assertEquals("Los proyectos NO son iguales",AbstractDAOFactory.getFactory().getProjectDAO().read(project.getId()), project);
    }    
    
    @Test
    public void testUpdate() {        
        projectEjb.createProject(project);
        project.setName("Nuevo Projecto Prueba Modificado");
        project.setDescription("Es una descripción del proyecto");
        project.setStartDate(new Date());
        project.setEndDate(new Date(new GregorianCalendar(2014, 12, 31).getTimeInMillis()));
        projectEjb.update(project);
        assertEquals("Los proyectos NO son iguales",AbstractDAOFactory.getFactory().getProjectDAO().read(project.getId()), project);
    }    
    
    @After
    public void finish() {
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);        
    }
}
