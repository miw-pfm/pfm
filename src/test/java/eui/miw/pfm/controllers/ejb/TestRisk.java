package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.RiskEntity;
import eui.miw.pfm.models.entities.UserEntity;
import org.junit.AfterClass;
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

        riskEntity1 = new RiskEntity();
        riskEntity1.setName("Pepe1");
        riskEntity1.setProject(project);
        project.addRisk(riskEntity1);

        riskEntity2 = new RiskEntity();
        riskEntity2.setName("Pepe2");
        riskEntity2.setProject(project);
        project.addRisk(riskEntity2);

        riskEntity3 = new RiskEntity();
        riskEntity3.setName("Pepe3");
        riskEntity3.setProject(project);
        project.addRisk(riskEntity3);

        AbstractDAOFactory.getFactory().getRiskDAO().create(riskEntity1);
        AbstractDAOFactory.getFactory().getRiskDAO().create(riskEntity2);
        AbstractDAOFactory.getFactory().getRiskDAO().create(riskEntity3);

    }

    @Test
    public void testUpdate() {
        final RiskEjb riskEjb = new RiskEjb();

        riskEntity2.setName("pepe5");
        riskEntity2.setProject(project);
        riskEntity3.setName("pepe6");
        riskEntity3.setProject(project);

        riskEjb.update(riskEntity2);
        riskEjb.update(riskEntity3);
       
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity2.getId()).equals(riskEntity2));
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity3.getId()).equals(riskEntity3));

    }

    @Test
    public void testCreate() {
        final RiskEjb riskEjb = new RiskEjb();
        final RiskEntity riskEntity4 = new RiskEntity();
        final RiskEntity riskEntity5 = new RiskEntity();
        final RiskEntity riskEntity6 = new RiskEntity();

        riskEntity4.setName("Pepe7");
        riskEntity4.setProject(project);
        project.addRisk(riskEntity4);

        riskEntity5.setName("Pepe8");
        riskEntity5.setProject(project);
        project.addRisk(riskEntity5);

        riskEntity6.setName("Pepe9");
        riskEntity6.setProject(project);
        project.addRisk(riskEntity6);

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

        assertNotNull("No existe el trabajador antes de borrar", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity1.getId()));

        final RiskEjb riskEjb = new RiskEjb();
        riskEjb.delete(riskEntity1);
        assertNull("Todavia existe el trabajador despues de borrar", AbstractDAOFactory.getFactory().getRiskDAO().read(riskEntity1.getId()));
    }

    @AfterClass
    public static void finish() {
        AbstractDAOFactory.getFactory().getRiskDAO().delete(riskEntity1);
        AbstractDAOFactory.getFactory().getRiskDAO().delete(riskEntity2);
        AbstractDAOFactory.getFactory().getRiskDAO().delete(riskEntity3);

        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);
    }

}
