package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ProgressDetailEjb;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

/**
 *
 * @author Clemencio Morales
 */
@RequestScoped
@Named
public class ProgressResumeBean extends Bean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger.getLogger(ProgressResumeBean.class.getName());//NOPMD
    
    private ProgressDetailEntity progressDetailEntity;
    private ProgressDetailEjb progressDetailEjb;
    
    @ManagedProperty(value = "#{progressDetailBean}")
    private final transient ProgressDetailBean progressDetailBean = new ProgressDetailBean();
    
    private int total;
    
    public ProgressResumeBean(){
        super();
        progressDetailEntity = new ProgressDetailEntity();
        progressDetailEjb = new ProgressDetailEjb();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ProgressDetailEntity getProgressDetailEntity() {
        return progressDetailEntity;
    }

    public void setProgressDetailEntity(ProgressDetailEntity progressDetailEntity) {
        this.progressDetailEntity = progressDetailEntity;
    }

    public ProgressDetailEjb getProgressDetailEjb() {
        return progressDetailEjb;
    }

    public void setProgressDetailEjb(ProgressDetailEjb progressDetailEjb) {
        this.progressDetailEjb = progressDetailEjb;
    }
    
    public void obtainPercentsOfPhasePerIteration(){
    //por cada it y disc, find all (Por cada iteración y disciplina, sumar el porcentaje de los CDU.)
    //Coger ProgressDetailEntity e ir sumando los percent de cada cdu de cada discipline_id e iteration_id
        List<ProgressDetailEntity> progressDetails = this.getProgressDetailEjb().getDetails();
        
        for (ProgressDetailEntity progressDetailEntity: progressDetails) {
            this.setTotal(this.getTotal()+this.getProgressDetailEntity().getPercent());
        }
    }
}
