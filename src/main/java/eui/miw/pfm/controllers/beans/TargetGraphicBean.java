/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.TargetEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.TargetEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author William
 */
@Named
@ViewScoped
public class TargetGraphicBean extends Bean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(TargetGraphicBean.class.getName());//NOPMD
    private final transient CartesianChartModel model; 
    private final transient List<TargetEntity> targets;
    
    private final transient TargetBean targetBean = new TargetBean();

    public TargetGraphicBean() {
        super();
        ProjectEntity project = new ProjectEntity();
         try {
            project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        
        model = new CartesianChartModel();
        targets = new TargetEjb().obtainProjectTargets(project);
        prepareGraphic();
    }

    public CartesianChartModel getModel() {
        return model;
    }
    
    private void prepareGraphic(){
        ChartSeries discipline ;
        for(TargetEntity target:targets){
            discipline = new ChartSeries();
            discipline.setLabel(target.getDiscipline().getName());
            discipline.set("Inception",(int)((targetBean.obtainPercentOfPhase(0)/target.getInception())*100));
            discipline.set("Elaboration",(int)((targetBean.obtainPercentOfPhase(1)/target.getElaboration())*100));
            discipline.set("Construction",(int)((targetBean.obtainPercentOfPhase(2)/target.getConstruction())*100));
            discipline.set("Transition",(int)((targetBean.obtainPercentOfPhase(3)/target.getTransition())*100));
            model.addSeries(discipline);
        }
    }
    
}
