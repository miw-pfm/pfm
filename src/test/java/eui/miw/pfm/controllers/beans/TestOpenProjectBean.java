/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.util.moks.ContextMocker;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author William
 */
public class TestOpenProjectBean {

    private transient OpenProjectBean openProjectBean;
    private transient UserDAO userDAO;
    private transient ProjectDAO projectDAO;

    @Before
    public void init() {
        
         FacesContext context = ContextMocker.mockFacesContext();
        try {
            Map<String, Object> session = new HashMap<String, Object>();
            ExternalContext ext = mock(ExternalContext.class);
            when(ext.getSessionMap()).thenReturn(session);
            when(context.getExternalContext()).thenReturn(ext);
            this.openProjectBean = new OpenProjectBean();
            userDAO = AbstractDAOFactory.getFactory().getUserDAO();
            projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();

        } finally {
            context.release();
        }
        
    }

    @Test
    public void testOpenProject() {//NOPMD
        //UserDAO userDAO ;
        //userDAO = AbstractDAOFactory.getFactory().getUserDAO();

        UserEntity user;
        user = new UserEntity();
        userDAO.create(user);

        //List<ProjectEntity> projects ;
        //projects = new ArrayList<ProjectEntity>();
        //ProjectDAO projectDAO ;
        //projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        ProjectEntity project;
        project = new ProjectEntity();
        project.setChosenNumIteration(2);
        project.setDescription("'");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setName("ProjectTest");
        project.setWeekNumIteration(2);
        project.setOwner(user);
        projectDAO.create(project);
        //projectDAO.commit();

        //ProjectEntity projectOpened = new ProjectEntity();

        //projects = projectDAO.find("SELECT p FROM ProjectEntity p WHERE p.owner = ?1",user)  /* projectDAO.read(1); (ArrayList<ProjectEntity>) projectDAO.find(attributes, values)*/;
        //System.out.println("Count :  "+projects.size());
        //assertTrue("Invalid Open Project",projects.size()> 0);
        this.testOpenProject(user, project);
        projectDAO.delete(project);
        userDAO.delete(user);
    }

    public void testOpenProject(UserEntity user, ProjectEntity project) {

        ProjectEntity projects;
        //projects = projectDAO.find("SELECT p FROM ProjectEntity p WHERE p.owner = ?1",user)  /* projectDAO.read(1); (ArrayList<ProjectEntity>) projectDAO.find(attributes, values)*/;
        projects = this.openProjectBean.openProject(project.getId());
        assertTrue("Invalid Open Project", projects != null);

    }
    
//    public void cleanDB() {
//
//        List<UserEntity> users;
//        users = userDAO.findAll();
//        List<ProjectEntity> projects;
//        projects = projectDAO.findAll();
//
//    }

}
