package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ConfProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Jose Angel
 */
@RequestScoped
@Named
public class ConfProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProjectEntity project;//NOPMD
    private final ConfProjectEjb confProjectEjb;//NOPMD
    private static final Logger LOG = Logger.getLogger(ConfProjectBean.class.getName());//NOPMD
    private static final List<Integer> chosenList = new ArrayList<>();

    public ConfProjectBean() {
        super();
        project = new ProjectEntity();
        confProjectEjb = new ConfProjectEjb();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOG.warning("No session exist");
        }
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public String update() {
        String result;

        if (validDates(project.getStartDate(), project.getEndDate())) {
            confProjectEjb.update(project);
            result = "list_project";
        } else {
            LOG.warning("Not valid dates");
            final FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verify the chosen dates",""));
            result = "confProject";
        }

        return result;
    }

    public boolean validDates(final Date start, final Date end) {

        boolean validDates = false;
        Date actual = new Date(System.currentTimeMillis() - (60 * 60 * 1000));
        if ((start.after(actual) || start.equals(actual)) && (end.after(start))) {
            validDates = true;
        }

        return validDates;
    }

    public void stimateIter() {

        final double mils = (project.getEndDate().getTime() - project.getStartDate().getTime() + 1) / 7 * 5;
        final double weeks = ((mils / (1000 * 60 * 60 * 24))) / 5;
        double sugIte;
        sugIte = weeks / project.getWeekNumIteration();

        if (!Double.isInfinite(sugIte)) {
            float estimate;
            estimate = (float) ((float) Math.round(sugIte * 100.0) / 100.0);
            project.setEstimatedNumIteration(estimate);

            final int roundEst = Math.round(estimate);
            chosenList.clear();

            if (roundEst > 1) {
                chosenList.add((roundEst - 1));
            }

            chosenList.add(roundEst);
            chosenList.add((roundEst + 1));
        }
    }

    public List<Integer> getChosenList() {
        return chosenList;
    }

}
