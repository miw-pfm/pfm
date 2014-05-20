package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.RiskEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import eui.miw.pfm.models.entities.UserEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Fred Peña
 */
public class TestRisk {

    private static RiskEntity riskEntity1;
    private static RiskEntity riskEntity2;
    private static RiskEntity riskEntity3;
    private static UseCaseEntity useCaseEntity1;
    private static UseCaseEntity useCaseEntity2;
    private static UseCaseEntity useCaseEntity3;

    private static UserEntity user;
    private static ProjectEntity project;

    @BeforeClass
    public static void init() {
        user = new UserEntity();
        user.setName("usuario");
        AbstractDAOFactory.getFactory().getUserDAO().create(user);

        project = new ProjectEntity();
        project.setName("Projecto prueba Worker");
        project.setOwner(user);
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);

        useCaseEntity1 = new UseCaseEntity();
        useCaseEntity1.setDescription("UC3");
        useCaseEntity1.setName("UC3");
        useCaseEntity1.setProject(project);
        AbstractDAOFactory.getFactory().getUseCaseDAO().create(useCaseEntity1);

        useCaseEntity2 = new UseCaseEntity();
        useCaseEntity2.setDescription("UC1");
        useCaseEntity2.setName("UC1");
        useCaseEntity2.setProject(project);
        AbstractDAOFactory.getFactory().getUseCaseDAO().create(useCaseEntity2);

        useCaseEntity3 = new UseCaseEntity();
        useCaseEntity3.setDescription("UC2");
        useCaseEntity3.setName("UC2");
        useCaseEntity3.setProject(project);
        AbstractDAOFactory.getFactory().getUseCaseDAO().create(useCaseEntity3);

        riskEntity1 = new RiskEntity();
        riskEntity1.setName("Pepe1");
        riskEntity1.setValue(4);
        riskEntity1.setProject(project);
        useCaseEntity1.addRisk(riskEntity1);
        project.addRisk(riskEntity1);

        riskEntity2 = new RiskEntity();
        riskEntity2.setName("Pepe2");
        riskEntity2.setValue(1);
        riskEntity2.setProject(project);
        useCaseEntity1.addRisk(riskEntity2);
        useCaseEntity2.addRisk(riskEntity2);
        project.addRisk(riskEntity2);

        riskEntity3 = new RiskEntity();
        riskEntity3.setName("Pepe3");
        riskEntity3.setValue(3);
        riskEntity3.setProject(project);
        useCaseEntity1.addRisk(riskEntity3);
        useCaseEntity2.addRisk(riskEntity3);
        useCaseEntity3.addRisk(riskEntity3);
        project.addRisk(riskEntity3);

        AbstractDAOFactory.getFactory().getRiskDAO().create(riskEntity1);
        AbstractDAOFactory.getFactory().getRiskDAO().create(riskEntity2);
        AbstractDAOFactory.getFactory().getRiskDAO().create(riskEntity3);

    }

  //  @Test
    public void testUpdate() {
        final RiskEjb riskEjb = new RiskEjb();

        riskEntity2.setName("pepe5");
        riskEntity2.setValue(1);
        riskEntity2.setProject(project);
        List<UseCaseEntity> lUsecase1 = new ArrayList<>();
        lUsecase1.add(useCaseEntity3);
        riskEntity2.setUsecases(lUsecase1);

        riskEntity3.setName("pepe6");
        riskEntity3.setValue(5);
        riskEntity3.setProject(project);
        List<UseCaseEntity> lUsecase2 = new ArrayList<>();
        lUsecase2.add(useCaseEntity1);
        riskEntity3.setUsecases(lUsecase2);

        riskEjb.update(riskEntity2);
        riskEjb.update(riskEntity3);


        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity2.getId()).getName().equals(riskEntity2.getName()));
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity3.getId()).getName().equals(riskEntity3.getName()));
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity2.getId()).getValue() == riskEntity2.getValue());
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity3.getId()).getValue() == riskEntity3.getValue());
    }

    @Test
    public void testCreate() {
        final RiskEjb riskEjb = new RiskEjb();
        final RiskEntity riskEntity4 = new RiskEntity();
        final RiskEntity riskEntity5 = new RiskEntity();
        final RiskEntity riskEntity6 = new RiskEntity();

        riskEntity4.setName("Pepe7");
        riskEntity4.setValue(4);
        riskEntity4.setProject(project);
        List<UseCaseEntity> lUsecase1 = new ArrayList<>();
        lUsecase1.add(useCaseEntity1);

        riskEntity4.setUsecases(lUsecase1);

        riskEntity5.setName("Pepe8");
        riskEntity5.setValue(5);
        riskEntity5.setProject(project);
        List<UseCaseEntity> lUsecase2 = new ArrayList<>();
        lUsecase2.add(useCaseEntity1);
        lUsecase2.add(useCaseEntity2);
        riskEntity5.setUsecases(lUsecase2);

        riskEntity6.setName("Pepe9");
        riskEntity6.setValue(3);
        riskEntity6.setProject(project);
        List<UseCaseEntity> lUsecase3 = new ArrayList<>();
        lUsecase3.add(useCaseEntity1);
        lUsecase3.add(useCaseEntity2);
        lUsecase3.add(useCaseEntity3);
        riskEntity6.setUsecases(lUsecase3);

        riskEjb.create(riskEntity4);
        riskEjb.create(riskEntity5);
        riskEjb.create(riskEntity6);

        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity4.getId()).equals(riskEntity4));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity5.getId()).equals(riskEntity5));
        assertTrue("ERROR creating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity6.getId()).equals(riskEntity6));

        AbstractDAOFactory.getFactory().getRiskDAO().delete(riskEntity4);
        AbstractDAOFactory.getFactory().getRiskDAO().delete(riskEntity5);
        AbstractDAOFactory.getFactory().getRiskDAO().delete(riskEntity6);
    }

    @Test
    public void testDelete() {

        assertNotNull("No existe el Risk 1 antes de borrar", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity1.getId()));
        assertNotNull("No existe el Risk 2 antes de borrar", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity2.getId()));
        assertNotNull("No existe el Risk 3 antes de borrar", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity3.getId()));

        final RiskEjb riskEjb = new RiskEjb();
        riskEjb.delete(riskEntity1);
        riskEjb.delete(riskEntity2);
        riskEjb.delete(riskEntity3);

        assertNull("Todavia existe el Risk 1 despues de borrar", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity1.getId()));
        assertNull("Todavia existe el Risk 2 despues de borrar", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity2.getId()));
        assertNull("Todavia existe el Risk 3 despues de borrar", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity3.getId()));
    }

    @AfterClass
    public static void finish() {
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);

        AbstractDAOFactory.getFactory().getUseCaseDAO().delete(useCaseEntity1);
        AbstractDAOFactory.getFactory().getUseCaseDAO().delete(useCaseEntity2);
        AbstractDAOFactory.getFactory().getUseCaseDAO().delete(useCaseEntity3);
    }

}
