/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.models.entities.WorkUnitEntity;
import eui.miw.pfm.util.TypeIteration;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jose Mª Villar
 */
public class TestWorkUnit {

    private UserEntity user;
    private ProjectEntity project;
    private SubActivityEntity subActivity;
    private IterationEntity iteration;

    private final WorkUnitEjb workUnitEjb = new WorkUnitEjb();
    private final List<WorkUnitEntity> listWorkUnit = new ArrayList<>();

    @Before
    public void before() {
        this.user = new UserEntity();
        this.project = new ProjectEntity();

        this.user.setName("usuario");
        AbstractDAOFactory.getFactory().getUserDAO().create(user);

        this.project.setName("Projecto prueba CU");
        this.project.setOwner(user);
        AbstractDAOFactory.getFactory().getProjectDAO().create(project);

        this.iteration = new IterationEntity();
        this.iteration.setTypeIteration(TypeIteration.INCEPTION);
        this.iteration.setIterValue(1);
        this.iteration.setProject(project);
        AbstractDAOFactory.getFactory().getIterationDAO().create(iteration);

        this.subActivity = AbstractDAOFactory.getFactory().getSubActivityDAO().read(1);
    }
   
    @Test
    public void storeHoursBySubActivityIteration() {
        final int numHours = 1;
        final int numWorkUnits = numHours * 4;

        
        for (int wu = 0; wu < this.workUnitEjb.calculatingWorkUnits(numHours); wu++) {
            this.listWorkUnit.add(new WorkUnitEntity(iteration, subActivity));
        }

        for (WorkUnitEntity workUnitEntity : this.listWorkUnit) {
            this.workUnitEjb.create(workUnitEntity);
        }

        System.out.println("numWorkUnits: "+this.workUnitEjb.getNumTotalWorkUnits(subActivity, iteration));
        
        assertSame("numero de unidades alamcenadas por subactividad e iteracion", this.workUnitEjb.getNumTotalWorkUnits(subActivity, iteration), numWorkUnits);
    }

    @After
    public void after() {
        for (WorkUnitEntity workUnitEntity : listWorkUnit) {
            this.workUnitEjb.delete(workUnitEntity);
        }

        AbstractDAOFactory.getFactory().getIterationDAO().delete(iteration);
        AbstractDAOFactory.getFactory().getProjectDAO().delete(project);
        AbstractDAOFactory.getFactory().getUserDAO().delete(user);
    }

}
