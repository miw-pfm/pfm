package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ConfProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.SessionMap;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jose Angel
 */
@RequestScoped
@Named
public class ConfProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProjectEntity project;
    private final SessionMap sessionMap;//NOPMD
    private static final Logger LOG = Logger.getLogger(ConfProjectBean.class.getName());//NOPMD

    public ConfProjectBean() {
        super();
        project = new ProjectEntity();
        this.sessionMap = new SessionMap();
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

    public boolean validDates(final Date start, final Date end) {

        boolean validDates = false;
        Date actual = new Date(System.currentTimeMillis() - (60 * 60 * 1000));
        if ((start.after(actual) || start.equals(actual)) && (end.after(start))) {
            validDates = true;
        }

        return validDates;
    }

    public String getProjectName() {
        return ((ProjectEntity) this.sessionMap.get("project")).getName();
    }

}
