package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Clemencio Morales
 */
@SessionScoped
@Named
public class ProjectBean extends Bean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ProjectBean.class.getName());

    private static final long serialVersionUID = 1L;
    private ProjectEntity projectEntity;

    public ProjectBean() {
        super();
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(final ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public String getName() {
        return this.projectEntity.getName();
    }

}
