/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Roberto Amor
 * @author Fred Pena
 */
@Named
@ViewScoped
public class ResourcesPlanBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());
    private int workers;
    private int weeks;
    private int working;
    private int hours;
    private static final int BUSINESS_DAYS = 5;
    private static final int HOURS_WORKED_DAY = 8;//hours worked per day

    @ManagedProperty(value = "#{calendarProjectBean}")
    private final transient CalendarProjectBean calendarProjectBean = new CalendarProjectBean(); //NOPMD

    @PostConstruct
    public void inti() {
        this.workers = this.project.getWorkersInPlan();
        calculator();
    }

    public ResourcesPlanBean() {
        super();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(final int workers) {
        this.workers = workers;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(final int weeks) {
        this.weeks = weeks;
    }

    public int getWorking() {
        return working;
    }

    public void setWorking(final int working) {
        this.working = working;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(final int hours) {
        this.hours = hours;
    }

    public void saveWorkers() {
        if (validateWorkers()) {
            LOGGER.log(Level.INFO, this.workers + ".");
            this.project.setWorkersInPlan(this.workers);
            new ProjectEjb().update(this.project);
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Workers Saved", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Number Workers not valid", ""));
        }
    }

    public boolean validateWorkers() {
        return this.workers >= 0;
    }

    public void calculator() {
        double week;
        double work;
        double hour;

        week = ((double) calendarProjectBean.getWorkingDays()) / BUSINESS_DAYS;
        work = this.workers * week * BUSINESS_DAYS;
        hour = work * HOURS_WORKED_DAY;

        this.weeks = (int) Math.round((float) week);
        this.hours = (int) hour;
        this.working = (int) work;
    }
}
