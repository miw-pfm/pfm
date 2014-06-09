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

    private transient final ChartSeries percentIdentification = new ChartSeries();
    private transient final ChartSeries percentSpecification = new ChartSeries();
    private transient final ChartSeries percentDesign = new ChartSeries();
    private transient final ChartSeries percentImplementation = new ChartSeries();
    private transient final ChartSeries percentTest = new ChartSeries();

    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    public ChartBean() {
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
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

    public void createCategoryModelProgressAbstract() {
        final List<IterationEntity> iterations = new IterationEjb().getAllIterations(project);
        final List<DisciplineEntity> discipline = new DisciplineEjb().findAll();
        final ProgressDetailEjb progressDetailEjb = new ProgressDetailEjb();
        
        this.percentIdentification.setLabel("Identification");
        this.percentSpecification.setLabel("Specification");
        this.percentDesign.setLabel("Design");
        this.percentImplementation.setLabel("Implementatio");
        this.percentTest.setLabel("Test");
        

        for (IterationEntity iterationEntity : iterations) {
            for (DisciplineEntity disciplineEntity : discipline) {
                this.percentSpecification.set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(iterationEntity, disciplineEntity));
                this.percentDesign.set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(iterationEntity, disciplineEntity));
                this.percentImplementation.set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(iterationEntity, disciplineEntity));
                this.percentTest.set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(iterationEntity, disciplineEntity));
                
            }
        }
    }
}
