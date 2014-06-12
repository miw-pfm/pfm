/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
    private transient UserEntity user2;
    
    private transient ProjectEntity project;
    private transient ProjectEntity project2;
    private transient ProjectEntity project3;
    
    private transient ProjectEjb projectEjb;
    
    @Before
    public void init() {
        user = new UserEntity();
        user.setName("usuario");
        AbstractDAOFactory.getFactory().getUserDAO().create(user); 
        
        user2 = new UserEntity();        
        user2.setName("usuario2");
        AbstractDAOFactory.getFactory().getUserDAO().create(user2);
                
        projectEjb = new ProjectEjb();
        
        project = new ProjectEntity();
        project.setName("Nuevo Projecto Prueba");
        project.setOwner(user);        
        projectEjb.createProject(project);

        project2 = new ProjectEntity();
        project2.setName("Nuevo Projecto Prueba 2");
        project2.setOwner(user);
        projectEjb.createProject(project2);        

        project3 = new ProjectEntity();
        project3.setName("Nuevo Projecto Prueba 3");
        project3.setOwner(user2);
        projectEjb.createProject(project3);        
    }
    
    @Test
    public void testCreate() {        
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
    
    @Test
    public void testOpen() {
        ProjectEntity p = projectEjb.openProject(project.getId());
        assertEquals("Los proyectos NO son iguales", p, project);
    }
    
    @Test
    public void testObtainProjects() {
        final List<ProjectEntity> projectEntitys = new ArrayList<>();
                
        projectEntitys.add(project);
        projectEntitys.add(project2);               
        assertTrue("NO recupera los proyectos del usuario", projectEjb.obtainProjects(user).containsAll(projectEntitys));        
        
        projectEntitys.add(project3);
        assertFalse("Recupera todos los proyectos", projectEjb.obtainProjects(user).containsAll(projectEntitys));
        
    

    }
        
    @After
    public void finish() {
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project2);
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project3);        
        AbstractDAOFactory.getFactory().getUserDAO().delete(user2);        
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);        
    }
}
