package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.RiskEjb;
import eui.miw.pfm.models.entities.RiskEntity;
import java.io.Serializable;
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
@RequestScoped
@Named
public class RiskBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RiskBean.class.getName());

    private RiskEntity riskEntity;

    public RiskBean() {
        super();
        riskEntity = new RiskEntity();
    }

    public RiskEntity getRiskEntity() {
        return riskEntity;
    }

    public void setRiskEntity(final RiskEntity riskEntity) {
        this.riskEntity = riskEntity;
    }

    public String update() {
        assert this.riskEntity != null;
        final RiskEjb riskEjb = new RiskEjb();

        LOGGER.info("Update: " + this.riskEntity.toString());

        riskEjb.update(this.riskEntity);
        return null;
    }

    public String create() {
        assert this.riskEntity != null;
        LOGGER.info(this.riskEntity.toString());
        final RiskEjb riskEjb = new RiskEjb();
        riskEjb.create(this.riskEntity);
        FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Risk Created", ""));
        return null;
    }

    public String delete(final RiskEntity risk) {
        assert this.riskEntity != null;
        LOGGER.info(this.riskEntity.toString());
        final RiskEjb riskEjb = new RiskEjb();
        riskEjb.delete(risk);
        return "workersList";
    }

    public String editWorker(final RiskEntity risk) {
        this.riskEntity = risk;
        LOGGER.info("Edit: " + this.riskEntity.toString());
        return null;
    }

    public List<RiskEntity> getRisks() {
        final RiskEjb ejb = new RiskEjb();
        return ejb.findAll();
    }

}
