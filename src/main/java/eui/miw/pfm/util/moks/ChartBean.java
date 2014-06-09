/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util.moks;

import eui.miw.pfm.controllers.beans.Bean;
import eui.miw.pfm.controllers.beans.ProjectConfBean;
import eui.miw.pfm.controllers.ejb.DisciplineEjb;
import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.controllers.ejb.ProgressDetailEjb;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Jean Mubaied
 * @author Jose Mª Villar
 */
@Named(value = "chartBean")
@ViewScoped
public class ChartBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private CartesianChartModel categoryModel;
    private CartesianChartModel categoryModelPAbs;

    
    private transient final ChartSeries pIdentification = new ChartSeries();
    private transient final ChartSeries pSpecification = new ChartSeries();
    private transient final ChartSeries pDesign = new ChartSeries();
    private transient final ChartSeries pImplementation = new ChartSeries();
    private transient final ChartSeries pTest = new ChartSeries();

    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    public ChartBean() {
        super();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        
        createCategoryModelProgressAbstract();
        createCategoryModel();
    }

    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();

        final ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");

        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        final ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 300);

        final ChartSeries prueba = new ChartSeries();
        prueba.setLabel("prueba");
        prueba.set("2004", 10);
        prueba.set("2005", 50);
        prueba.set("2006", 200);
        prueba.set("2007", 0);
        prueba.set("2008", 500);

        categoryModel.addSeries(boys);
        categoryModel.addSeries(girls);
        categoryModel.addSeries(prueba);

    }

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(final CartesianChartModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public CartesianChartModel getCategoryModelPAbs() {
        return categoryModelPAbs;
    }

    public void setCategoryModelPAbs(final CartesianChartModel categoryModelPAbs) {
        this.categoryModelPAbs = categoryModelPAbs;
    }
    
    private void createCategoryModelProgressAbstract() {
        final List<IterationEntity> iterations = new IterationEjb().getAllIterations(project);
        final List<DisciplineEntity> discipline = new DisciplineEjb().findAll(); //NOPMD
        final ProgressDetailEjb progressDetailEjb = new ProgressDetailEjb(); //NOPMD

        this.categoryModelPAbs = new CartesianChartModel();
                
        this.pIdentification.setLabel("Identification");
        this.pSpecification.setLabel("Specification");
        this.pDesign.setLabel("Design");
        this.pImplementation.setLabel("Implementatio");
        this.pTest.setLabel("Test");

        for (IterationEntity iterationEntity : iterations) {
            for (DisciplineEntity disciplineEntity : discipline) {
                switch (disciplineEntity.getName()) {
                    case "Specification":
                        this.pSpecification.set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(iterationEntity, disciplineEntity));
                        break;
                    case "Design":
                        this.pDesign.set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(iterationEntity, disciplineEntity));
                        break;
                    case "Implementation":
                        this.pImplementation.set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(iterationEntity, disciplineEntity));
                        break;
                    case "Test":
                        this.pTest.set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(iterationEntity, disciplineEntity));
                        break;
                    default: break;
                }
            }
        }
        
        categoryModelPAbs.addSeries(pSpecification);
        categoryModelPAbs.addSeries(pDesign);
        categoryModelPAbs.addSeries(pImplementation);
        categoryModelPAbs.addSeries(pTest);        
    }
}
