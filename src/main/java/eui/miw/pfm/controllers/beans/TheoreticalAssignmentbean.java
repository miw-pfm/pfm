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
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
    private static final Logger LOGGER = Logger.getLogger(TheoreticalAssignmentbean.class.getName());

    private static final double[] TOTAL = {5.0, 20.0, 65.0, 10.0};

    private transient TheoreticalAssignmentEjb assignmentEjb;

    @PostConstruct
    public void init() {
        assignmentEjb = new TheoreticalAssignmentEjb();
    }

    public List<TheoreticalAssignmentEntity> getTheoreticalAssignments() {
        return assignmentEjb.findAll();
    }

    public double getTheoreticalAssignment(final int index) {
        return ConverterDecimal.roundOneDecimals(TOTAL[index]);
    }

    public double getTotalProjectTheoreticalAssignment() {
        double total = 0.0;
        for (int i = 0; i < 4; i++) {
            total += getTheoreticalAssignment(i);
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
}
