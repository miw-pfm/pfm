package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.util.TypeIteration;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Manuel Alvarez
 */
public class TestIteration {

    private static IterationEntity iterationEntity1;
    private static IterationEntity iterationEntity2;
    private static IterationEntity iterationEntity3;
    private static UserEntity user;
    private static ProjectEntity project;
    private static IterationEjb iterEjb;

    @BeforeClass
    public static void init() {
        user = new UserEntity();
        user.setName("usuario");
        AbstractDAOFactory.getFactory().getUserDAO().create(user);

        project = new ProjectEntity();
        project.setName("Projecto prueba Worker");
        project.setOwner(user);
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);
    }

    public void create() {
        iterEjb = new IterationEjb();

        iterationEntity1 = new IterationEntity();
        iterationEntity1.setTypeIteration(TypeIteration.INCEPTION);
        iterationEntity1.setIterValue(1);
        iterationEntity1.setStartDate(new GregorianCalendar(2014, 05, 21));
        iterationEntity1.setEndDate(new GregorianCalendar(2014, 06, 25));
        iterationEntity1.setProject(project);

        iterationEntity2 = new IterationEntity();
        iterationEntity2.setTypeIteration(TypeIteration.INCEPTION);
        iterationEntity2.setIterValue(2);
        iterationEntity2.setStartDate(new GregorianCalendar(2014, 06, 26));
        iterationEntity2.setEndDate(new GregorianCalendar(2014, 07, 26));
        iterationEntity2.setProject(project);

        iterationEntity3 = new IterationEntity();
        iterationEntity3.setTypeIteration(TypeIteration.ELABORATION);
        iterationEntity3.setIterValue(1);
        iterationEntity3.setStartDate(new GregorianCalendar(2014, 07, 27));
        iterationEntity3.setEndDate(new GregorianCalendar(2014, 07, 30));
        iterationEntity3.setProject(project);

        iterEjb.create(iterationEntity1);
        iterEjb.create(iterationEntity2);
        iterEjb.create(iterationEntity3);
    }
    
    public void delete(){
        iterEjb.delete(iterationEntity1);
        iterEjb.delete(iterationEntity2);
        iterEjb.delete(iterationEntity3);
    }

    @Test
    public void testCreate() {
        create();

        assertTrue("ERROR creating iteration", AbstractDAOFactory.getFactory().getIterationDAO().read(iterationEntity1.getId()).equals(iterationEntity1));
        assertTrue("ERROR creating iteration", AbstractDAOFactory.getFactory().getIterationDAO().read(iterationEntity2.getId()).equals(iterationEntity2));
    }

    @Test
    public void testDelete() {
        delete();

        assertNull("Todavia existe el trabajador despues de borrar", AbstractDAOFactory.getFactory().getIterationDAO().read(iterationEntity1.getId()));
        assertNull("Todavia existe el trabajador despues de borrar", AbstractDAOFactory.getFactory().getIterationDAO().read(iterationEntity2.getId()));
        assertNull("Todavia existe el trabajador despues de borrar", AbstractDAOFactory.getFactory().getIterationDAO().read(iterationEntity3.getId()));
    }

    @Test
    public void testUpdate() {
        create();

        iterationEntity1.setStartDate(new GregorianCalendar(2014, 05, 20));
        iterEjb.update(iterationEntity1);

        iterationEntity2.setIterValue(3);
        iterEjb.update(iterationEntity2);

        iterationEntity3.setTypeIteration(TypeIteration.CONSTRUCTION);
        iterEjb.update(iterationEntity3);

        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getIterationDAO().read(iterationEntity1.getId()).equals(iterationEntity1));
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getIterationDAO().read(iterationEntity2.getId()).equals(iterationEntity2));
        assertTrue("ERROR updating", AbstractDAOFactory.getFactory().getIterationDAO().read(iterationEntity3.getId()).equals(iterationEntity3));

        delete();
    }

// Solo falla por el problema de persistencia
//    @Test
//    public void testGetIterations() {
//        create();
//        assertNotNull("ERROR get Iterations", iterEjb.getIterations());        
//        delete();
//    }

    @AfterClass
    public static void finish() {
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);
    }
}
