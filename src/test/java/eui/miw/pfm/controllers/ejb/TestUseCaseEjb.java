/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.UseCaseEntity;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author bk0823
 */
public class TestUseCaseEjb {

    private UseCaseEjb useCaseEjb;
    private UseCaseEntity useCaseEntity;

    @Before
    public void setUp() {
        useCaseEjb = new UseCaseEjb();
        useCaseEntity = new UseCaseEntity();
    }

    @Test
    public void testDelete() {
        useCaseEntity.setName("TestUseCase");
        useCaseEntity.setDescription("Test Descripcion UseCase");
        useCaseEntity.setProject(AbstractDAOFactory.getFactory().getProjectDAO().read(1));
        AbstractDAOFactory.getFactory().getUseCaseDAO().create(useCaseEntity);
        assertNotNull("",AbstractDAOFactory.getFactory().getUseCaseDAO().read(useCaseEntity.getId()));
        useCaseEjb.delete(useCaseEntity);
        assertNull("This project still exists after deleted", AbstractDAOFactory.getFactory().getUseCaseDAO().read(useCaseEntity.getId()));
    }
}
