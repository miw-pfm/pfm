package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.RiskEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.RiskEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Fred Peña
 */
@Named
@RequestScoped
public class RiskBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RiskBean.class.getName());
    private RiskEntity riskEntity;
    private static List<Integer> chosenList;
    private transient ProjectEntity project;

    public RiskBean() {
        super();
        riskEntity = new RiskEntity();
        chosenList = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            chosenList.add(i);
        }

        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public RiskEntity getRiskEntity() {
        return riskEntity;
    }

    public void setRiskEntity(final RiskEntity riskEntity) {
        this.riskEntity = riskEntity;
    }

    public String update() {
        this.riskEntity.setProject(project);

        LOGGER.info(this.riskEntity.toString());

        if (new RiskEjb().update(this.riskEntity)) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Risk Updated", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Risks name is already exists", ""));
            return null;
        }
        return "/riskplan/riskList";
    }

    public String create() {
        if (this.project == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No project selected", ""));
            return null;//NOPMD
        }

        this.riskEntity.setProject(this.project);

        if (new RiskEjb().create(this.riskEntity)) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Risk Created", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Risks name is already exists", ""));
            return null;
        }
        return "/riskplan/riskList";
    }

    public String editRisk() {
        if (this.riskEntity == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Risk Selected", ""));
        } else {
            return "/riskplan/riskEdit";
        }
        return null;
    }

    public String delete() {
        if (this.riskEntity == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Risk Selected", ""));
        } else {
            LOGGER.info(this.riskEntity.toString());

            this.riskEntity.setProject(this.project);

            new RiskEjb().delete(this.riskEntity);
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Risk Deleted", ""));
        }
        return null;
    }

    public List<RiskEntity> getRisks() {
        return new RiskEjb().findRisks(this.project);
    }

    public List<Integer> getChosenList() {
        return chosenList;
    }
}
