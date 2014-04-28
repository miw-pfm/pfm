/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.dao.interfaces.UseCaseDAO;
import eui.miw.pfm.models.entities.UseCaseEntity;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Roberto Amor
 */
public class TestUseCase {
    
    private transient UseCaseEntity usecase;
    private transient UseCaseEjb usecaseEjb;
    
    @Before
    public void before() {
        ProjectDAO projectDAO = AbstractDAOFactory.getFactory().getProjectDAO();
        
        usecaseEjb = new UseCaseEjb();
        usecase = new UseCaseEntity();
        usecase.setName("UseCase 1");
        usecase.setDescription("UseCase de ejemplo");
        usecase.setProject(projectDAO.read(1));
    }
    
    @Test
    public void updateTest(){
        UseCaseDAO useCaseDAO = AbstractDAOFactory.getFactory().getUseCaseDAO();
        useCaseDAO.create(usecase); // se guarda en la base de datos
        usecase.setDescription("UseCase de ejemplo modificado");
        usecase.setName("UseCase 1 Modificado");
        usecaseEjb.update(usecase);
        UseCaseEntity usecase2 = useCaseDAO.read(usecase.getId()); // se obtiene de la BD
        useCaseDAO.delete(usecase);
        assertTrue("Modified", usecase2.getDescription().equals("UseCase de ejemplo modificado") && usecase2.getName().equals("UseCase 1 Modificado"));
    }
}
