/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListProjectsEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Roberto Amor
 */
@Named
@SessionScoped
public class ListProjectsBean extends Bean {
    private List<ProjectEntity> projects;
    private int pages; // number of pages to display

    /**
     * Creates a new instance of ViewProjectsBean
     */
    public ListProjectsBean() { // NOPMD
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(final List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(final int pages) {
        this.pages = pages;
    }
    
    public void obtainProjects(){
        ListProjectsEjb eaE; 
        eaE = new ListProjectsEjb();
        this.setProjects(eaE.obtainProjects(1));
    }
    
    
}
