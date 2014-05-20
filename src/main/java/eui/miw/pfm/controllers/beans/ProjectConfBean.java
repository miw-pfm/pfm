package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ProjectEjb;
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
public class ProjectConfBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProjectEntity project;//NOPMD
    private final ProjectEjb projectEjb;//NOPMD
    private static final Logger LOG = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD
    private static final List<Integer> chosenList = new ArrayList<>();
    private boolean weeksError = false;

    public ProjectConfBean() {
        super();
        project = new ProjectEntity();
        projectEjb = new ProjectEjb();
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

    public boolean isWeeksError() {
        return weeksError;
    }

    public void setWeeksError(boolean weeksError) {
        this.weeksError = weeksError;
    }

    public String haveWeeksError() {
        String result = "";
        if(this.weeksError){
            result = "Error (weeks < weeks per iteration)";
        }
        return result;
    }
    public String update() {
        String result;

        if (validDates(project.getStartDate(), project.getEndDate()) && this.validWeeksPerIter()) {
            projectEjb.update(project);
            result = "openProject";
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

    public boolean validWeeksPerIter(){
        boolean result;
        this.weeksError = false;
        final double mils = (project.getEndDate().getTime() - project.getStartDate().getTime() + 1) / 7 * 5;
        final double weeks = ((mils / (1000 * 60 * 60 * 24))) / 5;
        if(weeks<project.getWeekNumIteration()){
            LOG.warning("Not valid time for project (weeks < weeks per iteration)");
            this.weeksError = true;
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    
    public void stimateIter() {

        final double mils = (project.getEndDate().getTime() - project.getStartDate().getTime() + 1) / 7 * 5;
        final double weeks = ((mils / (1000 * 60 * 60 * 24))) / 5;
        this.weeksError = false;
        if(!this.validWeeksPerIter()){
            return;
        }
        
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
