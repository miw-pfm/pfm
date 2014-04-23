/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import java.util.Date;

/**
 *
 * @author William
 */
public class ConfProjectBean {
    
    
    public boolean validDates(Date start,Date end){
        
        boolean validDates = false;
        Date actual = new Date(System.currentTimeMillis() - (60 * 60 * 1000));
        if((start.after(actual) || start.equals(actual)) && (end.after(start))){
          validDates = true;
        }
        
        return validDates;
    }
    
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ConfProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jose Angel
 */
@ManagedBean
@SessionScoped
public class ConfProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProjectEntity project;

    public ConfProjectBean() {
        super();
        project = new ProjectEntity();        
    }

    public void edit(final int projId) {
        ConfProjectEjb ejb;
        ejb = new ConfProjectEjb();
        setProject(ejb.getProject(projId));
    }

    public ProjectEntity getProject() {
        return project;
    }
        
    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    //TODO: Replace static text with bundle variable
    public String update() {
        ConfProjectEjb ejb;
        String result;

        if (validFields()) {
            ejb = new ConfProjectEjb();
            ejb.update(project);
            result = "correct";
        } else {
            result = "error";
        }

        return result;
    }

    //TODO
    private boolean validFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
