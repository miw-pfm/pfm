package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.RiskEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.RiskEntity;
import eui.miw.pfm.util.ExceptionCatch;
import eui.miw.pfm.util.LazyRiskDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Fred Peña
 */
@SessionScoped
@Named
public class RiskBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RiskBean.class.getName());
    private transient LazyDataModel<RiskEntity> lazyModel;
    private RiskEntity selectedRisk;
    private RiskEntity riskEntity;
    private static List<Integer> chosenList;
    private transient ProjectEntity project;
    
    public RiskBean() {
        super();
        riskEntity = new RiskEntity();
        chosenList = new ArrayList<>();
        chosenList.add(1);
        chosenList.add(2);
        chosenList.add(3);
        chosenList.add(4);
        chosenList.add(5);
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        this.lazyModel = new LazyRiskDataModel(new RiskEjb().findRisks(this.project));
    }

    public RiskEntity getRiskEntity() {
        return riskEntity;
    }

    public void setRiskEntity(final RiskEntity riskEntity) {
        this.riskEntity = riskEntity;
    }

    public RiskEntity getSelectedRisk() {
        return selectedRisk;
    }

    public void setSelectedRisk(final RiskEntity selectedRisk) {
        this.selectedRisk = selectedRisk;
    }

    public LazyDataModel<RiskEntity> getLazyModel() {
        return lazyModel;
    }

    public String update() {
        assert this.riskEntity != null;
        final RiskEjb riskEjb = new RiskEjb();
        this.riskEntity.setProject(this.project);

        riskEjb.update(this.riskEntity);
        FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Risk Updated", ""));
        return "listRisk";
    }

    public String create() {
        if (this.project == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No project selected", ""));
            return null;//NOPMD
        }
        final RiskEjb riskEjb = new RiskEjb();

        this.riskEntity.setProject(this.project);
        this.project.addRisk(this.riskEntity);

        riskEjb.create(this.riskEntity);
        
        if (ExceptionCatch.getInstance().isException()) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Create Risk", ""));
            ExceptionCatch.getInstance().setException(false);
            this.project.removeRisk(this.riskEntity);
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Risk Created", ""));
            this.reload();                   
        }

        return "listRisk";
    }

    public String edit(final RiskEntity risk) {
        this.riskEntity = risk;
        return "editRisk";
    }

    public String delete(final RiskEntity risk) {
        assert risk != null;
        assert this.project != null;

        this.riskEntity = risk;
        LOGGER.info(this.riskEntity.toString());

        this.riskEntity.setProject(this.project);
        this.project.removeRisk(this.riskEntity);

        new RiskEjb().delete(this.riskEntity);
        FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Risk Deleted", ""));

        reload();

        return "listRisk";
    }

    private void reload() {
        this.lazyModel = new LazyRiskDataModel(new RiskEjb().findRisks(this.project));
    }
    
    public List<Integer> getChosenList() {
        return chosenList;
    }
}
