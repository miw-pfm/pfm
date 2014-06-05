/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.TargetEntity;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author Jean Mubaied
 */
@ViewScoped
@Named
public class TargetBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TargetEntity target;
    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    public TargetBean() {
        super();
        this.target = new TargetEntity();

        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public TargetEntity getTarget() {
        return target;
    }

    public void setTarget(final TargetEntity target) {
        this.target = target;
    }
   
}
