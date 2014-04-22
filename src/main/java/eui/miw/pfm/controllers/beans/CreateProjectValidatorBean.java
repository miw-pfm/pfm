/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.CreateProjectValidatorEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
//import eui.miw.pfm.util.mocks.MockProjectNamesList;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author César Martínez
 */
@RequestScoped
@Named
public class CreateProjectValidatorBean extends Bean {

    /**
     * Creates a new instance of CreateProjectValidatorBean
     */
    public CreateProjectValidatorBean() {//NOPMD
    }
//function to validate that the suggested name doesn't exist already. 

    public boolean nameValidator(final String suggestedName) {
        CreateProjectValidatorEjb cPVEjb;
        cPVEjb = new CreateProjectValidatorEjb();
        List<ProjectEntity> projects;
        projects = cPVEjb.nameValidator();
        boolean nameExist; //name validation flag
        nameExist = false;//NOPMD
        for (ProjectEntity project : projects) {
            if (project.getName().equals(suggestedName)) {
                nameExist = true;//NOPMD
            }
        }
        return nameExist;
    }
}
