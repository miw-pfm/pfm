/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aw0591
 */
public class TestListCaseUse {
    
    private UserDAO userDAO;
    private ProjectDAO projectDAO;
    private UserEntity user;
    private ProjectEntity project;
    private ListUseCaseEjb listUCEjb;
    
    
    @Before
    public void before() {
        this.user = new UserEntity();
        this.project = new ProjectEntity();
        this.listUCEjb = new ListUseCaseEjb();

        this.user.setName("usuario");
        this.userDAO = AbstractDAOFactory.getFactory().getUserDAO();
        this.userDAO.create(user);

        this.project.setName("Projecto prueba CU");
        this.project.setOwner(user);

        this.projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        this.projectDAO.create(project);        
    }
    
    @Test
    public void obtainUseCases() {
        
        UseCaseEntity uc1 = new UseCaseEntity();
        UseCaseEntity uc2 = new UseCaseEntity();
        UseCaseEntity uc3 = new UseCaseEntity();
        
        List<UseCaseEntity> listUC = new ArrayList<UseCaseEntity>();
        
        uc1.setName("CU1");
        uc1.setDescription("Descripción del caso de uso1");
        uc1.setProject(project);

        uc2.setName("CU2");
        uc2.setDescription("Descripción del caso de uso2");
        uc2.setProject(project);

        uc3.setName("CU3");
        uc3.setDescription("Descripción del caso de uso3");
        uc3.setProject(project);
        
        this.project.addUseCases(uc1);
        this.projectDAO.update(project);
        
        this.project.addUseCases(uc2);
        this.projectDAO.update(project);
        
        listUC.addAll(project.getUseCases());
        
        assertTrue("Recupera uc1 y uc2 correctamente",this.listUCEjb.obtainUseCase(this.project).containsAll(listUC));
        listUC.clear();
        
        this.project.addUseCases(uc3);
        listUC.addAll(project.getUseCases());
        
        assertFalse("Recupera uc1 y uc2 correctamente, uc3 NO está en la BD",this.listUCEjb.obtainUseCase(this.project).containsAll(listUC));
        listUC.clear();
        
        this.projectDAO.update(project);
        listUC.addAll(project.getUseCases());
        
        assertTrue("Recupera uc1, uc2 y uc3 correctamente",this.listUCEjb.obtainUseCase(this.project).containsAll(listUC));
    }

    @After
    public void after() {
       // this.projectDAO.delete(project);
       // this.userDAO.delete(user);
    }
}
