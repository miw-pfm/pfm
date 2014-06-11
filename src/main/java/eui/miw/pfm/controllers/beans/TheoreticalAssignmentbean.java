/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.TheoreticalAssignmentEjb;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.TheoreticalAssignmentEntity;
import eui.miw.pfm.util.ConverterDecimal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Fred Peña
 */
@ViewScoped
@Named
public class TheoreticalAssignmentbean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final double[] PERCENT = {5.0, 20.0, 65.0, 10.0};

    private transient TheoreticalAssignmentEjb assignmentEjb;

    @ManagedProperty(value = "#{resourcesPlanBean}")
    private final transient ResourcesPlanBean resourcesPlanBean = new ResourcesPlanBean();

    @ManagedProperty(value = "#{iterationBean}")
    private final transient IterationBean iterationBean = new IterationBean();

    @PostConstruct
    public void init() {
        assignmentEjb = new TheoreticalAssignmentEjb();
    }

    public List<TheoreticalAssignmentEntity> getTheoreticalAssignments() {
        return assignmentEjb.findAll();
    }

    public double getTotalTheoreticalAssignmentPercentage(final int index) {
        return ConverterDecimal.roundOneDecimals(PERCENT[index]);
    }

    public double getTotalTheoreticalAssignmentPercentageProject() {
        double total = 0.0;
        for (int i = 0; i < 4; i++) {
            total += getTotalTheoreticalAssignmentPercentage(i);
        }
        return ConverterDecimal.roundOneDecimals(total);
    }

    public double getProjectTheoreticalAssignment(final ActivityEntity activity) {
        double total = 0.0;
        for (TheoreticalAssignmentEntity assignment : getTheoreticalAssignments()) {
            if (assignment.getActivity().equals(activity)) {
                total += (assignment.getInceptionValue() * PERCENT[0]);
                total += (assignment.getElaborationValue() * PERCENT[1]);
                total += (assignment.getContrutionValue() * PERCENT[2]);
                total += (assignment.getTransitionValue() * PERCENT[3]);
                break;
            }
        }
        return ConverterDecimal.roundOneDecimals(total / 100);
    }

    public double getTotalAssignmentWorkingDays(final int index) {
        resourcesPlanBean.calculator();
        return ConverterDecimal.roundOneDecimals((PERCENT[index] / 100) * resourcesPlanBean.getWorking());
    }

    public double getTotalAssignmentWorkingDaysProject() {
        double total = 0.0;
        for (int i = 0; i < 4; i++) {
            total += getTotalAssignmentWorkingDays(i);
        }
        return ConverterDecimal.roundOneDecimals(total);
    }

    public double getWorkingDaysValues(final double value, final int index) {
        return ConverterDecimal.roundOneDecimals((value * getTotalAssignmentWorkingDays(index)) / 100);
    }

    public double getProjecttWorkingDays(final ActivityEntity activity) {
        return ConverterDecimal.roundOneDecimals((getProjectTheoreticalAssignment(activity) * getTotalAssignmentWorkingDaysProject()) / 100);
    }

    private double iterationValue(final int size, final double value, final int index) {
        return ConverterDecimal.roundOneDecimals(getWorkingDaysValues(value, index) / size);
    }

    public double getInceptionIterationValue(final ActivityEntity activity, final int index) {
        final TheoreticalAssignmentEntity theoretical = activity.getTheoreticalAssignment().get(0);//NOPMD
        double iterationValue;//NOPMD
        switch (index) {
            case 0:
                iterationValue = iterationValue(this.iterationBean.getListInception().size(), theoretical.getInceptionValue(), index);
                break;
            case 1:
                iterationValue = iterationValue(this.iterationBean.getListElaboration().size(), theoretical.getElaborationValue(), index);
                break;
            case 2:
                iterationValue = iterationValue(this.iterationBean.getListConstruction().size(), theoretical.getContrutionValue(), index);
                break;
            case 3:
                iterationValue = iterationValue(this.iterationBean.getListTransition().size(), theoretical.getTransitionValue(), index);
                break;
            default:
                iterationValue = 0.0;
        }
        return iterationValue;
    }
}
