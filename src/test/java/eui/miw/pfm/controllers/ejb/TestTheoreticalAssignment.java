/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.TheoreticalAssignmentEntity;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Fred Peña
 */
public class TestTheoreticalAssignment {

    private static TheoreticalAssignmentEntity assignment1;
    private static TheoreticalAssignmentEntity assignment2;

    private static ActivityEntity activity;

    @BeforeClass
    public static void before() {

        activity = new ActivityEntity();
        activity.setCode("Act1");
        activity.setName("Activity 1");
        AbstractDAOFactory.getFactory().getActivityDAO().create(activity);

    }

    private void create() {
        assignment1 = new TheoreticalAssignmentEntity();
        assignment1.setActivity(activity);
        assignment1.setContrutionValue(10.3);
        assignment1.setElaborationValue(9);
        assignment1.setInceptionValue(2.3);
        assignment1.setTransitionValue(5.6);

        assignment2 = new TheoreticalAssignmentEntity();
        assignment2.setActivity(activity);
        assignment2.setContrutionValue(1.3);
        assignment2.setElaborationValue(9.3);
        assignment2.setInceptionValue(2);
        assignment2.setTransitionValue(5);

        AbstractDAOFactory.getFactory().getTheoreticalAssignmentDAO().create(assignment1);
        AbstractDAOFactory.getFactory().getTheoreticalAssignmentDAO().create(assignment2);

    }

    @Test
    public void testFindAll() {

        final TheoreticalAssignmentEjb assignmentEjb = new TheoreticalAssignmentEjb();
        create();
        assertNotNull("Not Null", assignmentEjb.findAll());
    }

    @AfterClass
    public static void after() {
        AbstractDAOFactory.getFactory().getTheoreticalAssignmentDAO().delete(assignment1);
        AbstractDAOFactory.getFactory().getTheoreticalAssignmentDAO().delete(assignment2);

        AbstractDAOFactory.getFactory().getActivityDAO().delete(activity);
    }
}
