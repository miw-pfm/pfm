/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.ControllerDate;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Roberto Amor
 * @author Fred Pena
 */
@Named
@RequestScoped
public class ResourcesPlanBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD
    private int workers;
    private int weeks;
    private int working;
    private int hours;
    private static final int WEEKDAYS = 7;
    private static final int BUSINESS_DAYS = 5;
    private static final int MORE_ONE = 1;
    private static final int HOURS_WORKED_DAY = 8;//hours worked per day

    private final ProjectEjb projectEjb;//NOPMD

    public ResourcesPlanBean() {
        super();
        projectEjb = new ProjectEjb();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        this.workers = this.project.getWorkersInPlan();
        calculator();
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
            LOGGER.log(Level.INFO, this.workers + "");
            this.project.setWorkersInPlan(this.workers);
            projectEjb.update(this.project);
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
        double workingDays;
        double hour;

        workingDays = roundOneDecimals((ControllerDate.getDifferenceDate(this.project.getStartDate(), this.project.getEndDate()) + MORE_ONE) / WEEKDAYS * BUSINESS_DAYS);
        week = workingDays / BUSINESS_DAYS;
        work = this.workers * week * BUSINESS_DAYS;
        hour = work * HOURS_WORKED_DAY;

        this.weeks = (int) Math.round((float) week);
        this.hours = (int) hour;
        this.working = (int) work;
    }

    private double roundOneDecimals(final double value) {
        final DecimalFormat oneDigit = new DecimalFormat("#,###.0");
        try {
            return (double) oneDigit.parse(oneDigit.format(value));//NOPMD
        } catch (ParseException ex) {
            Logger.getLogger(ResourcesPlanBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
