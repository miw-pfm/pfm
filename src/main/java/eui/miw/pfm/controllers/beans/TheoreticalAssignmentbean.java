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

    private static final double[] TOTAL = {5.0, 20.0, 65.0, 10.0};

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
        return ConverterDecimal.roundOneDecimals(TOTAL[index]);
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
                total += (assignment.getInceptionValue() * TOTAL[0]);
                total += (assignment.getElaborationValue() * TOTAL[1]);
                total += (assignment.getContrutionValue() * TOTAL[2]);
                total += (assignment.getTransitionValue() * TOTAL[3]);
                break;
            }
        }
        return ConverterDecimal.roundOneDecimals(total / 100);
    }

    public double getTotalAssignmentWorkingDays(final int index) {
        resourcesPlanBean.calculator();
        return ConverterDecimal.roundOneDecimals((TOTAL[index] / 100) * resourcesPlanBean.getWorking());
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
        final TheoreticalAssignmentEntity theoretical = activity.getTheoreticalAssignment().get(0);

        switch (index) {
            case 0:
                return iterationValue(this.iterationBean.getListInception().size(), theoretical.getInceptionValue(), index);
            case 1:
                return iterationValue(this.iterationBean.getListElaboration().size(), theoretical.getElaborationValue(), index);
            case 2:
                return iterationValue(this.iterationBean.getListConstruction().size(), theoretical.getContrutionValue(), index);
            case 3:
                return iterationValue(this.iterationBean.getListTransition().size(), theoretical.getTransitionValue(), index);
            default:
                return 0.0;
        }
    }
}
