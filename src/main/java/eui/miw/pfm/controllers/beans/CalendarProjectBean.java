package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.CalendarProjectEjb;
import eui.miw.pfm.models.entities.CalendarEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.ExceptionCatch;
import eui.miw.pfm.util.SessionMap;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Manuel Álvarez
 * @code added Manuel Rodríguez
 * @code added Jean Mubaied
 * @code added Hector William
 */
@RequestScoped
@Named
public class CalendarProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CalendarEntity calendarEntity;
    private ProjectEntity project;
    private String name = "";
    private String description = "";
    private SimpleDateFormat format = new SimpleDateFormat("d/M/yy");
    private Date date1;
    private static final Logger LOG = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD
    private boolean disableAdd;
    private boolean disableEditRemove;
    private boolean disableNameField;
    private boolean disableDescField;

    public CalendarProjectBean() {
        super();
        project = new ProjectEntity();
        calendarEntity = new CalendarEntity();
        try {
            this.project = ((ProjectEntity) new SessionMap().get("project"));
        } catch (Exception e) {
            LOG.warning("No session exist");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(final Date date1) {
        this.date1 = date1;
    }

    public boolean isDisableEditRemove() {
        return disableEditRemove;
    }

    public String getStartDate() {
        return format.format(this.project.getStartDate());
    }

    public String getEndDate() {
        return format.format(this.project.getEndDate());
    }

    public boolean isDisableNameField() {
        return disableNameField;
    }

    public void setDisableNameField(final boolean disableNameField) {
        this.disableNameField = disableNameField;
    }

    public boolean isDisableDescField() {
        return disableDescField;
    }

    public void setDisableDescField(final boolean disableDescField) {
        this.disableDescField = disableDescField;
    }

    public int getWorkingDays() {
        Calendar startDate;
        startDate = new GregorianCalendar();
        Calendar endDate;
        endDate = new GregorianCalendar();
        startDate.setTime(this.project.getStartDate());
        endDate.setTime(this.project.getEndDate());
        int diffDays;
        diffDays = 0;
        while (startDate.before(endDate) || startDate.equals(endDate)) {
            if (startDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && startDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                diffDays++;
            }
            startDate.add(Calendar.DATE, 1);
        }
        diffDays = diffDays - this.getRealHolidays();
        return diffDays;
    }

    public int getRealHolidays() {
        int count;
        count = 0;
        final CalendarProjectEjb ejb = new CalendarProjectEjb();
        for (CalendarEntity holiday : ejb.obtainHolidays(this.project)) {
            if (this.project.getStartDate().before(holiday.getHoliday().getTime()) && this.project.getEndDate().after(holiday.getHoliday().getTime())) {
                count++;
            }
        }
        return count;
    }

    public String create() {
        this.calendarEntity.setProject(project);
        new CalendarProjectEjb().create(this.calendarEntity);
        return null;
    }

    public String update() {
        this.calendarEntity.setProject(project);
        new CalendarProjectEjb().update(this.calendarEntity);
        return null;
    }

    public String delete() {
        new CalendarProjectEjb().delete(this.calendarEntity);
        return null;
    }

    public Calendar selectedDate() {
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTime(date1);
        return calendar;
    }

    //Manuel Rodríguez
    //Hector William
    public void addDateSelect(final ActionEvent actionEvent) {
        new CalendarProjectEjb().create(readyCalendarEntity());
        final RequestContext context = RequestContext.getCurrentInstance();
        boolean operationResult;
        operationResult = false;
        FacesMessage message;

        if (ExceptionCatch.getInstance().isException()) {
            operationResult = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error Festive Date ",
                    "Error : Please review the filled fields and try again. ");
            ExceptionCatch.getInstance().setException(false);
        } else {
            operationResult = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Successfutlly Added",
                    "The date was correctly added.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("operationResult", operationResult);

    }

    //Manuel Rodríguez
    //Hector William
    public void updateDateSelect(final ActionEvent actionEvent) {
        CalendarProjectEjb calendarEJB;
        calendarEJB = new CalendarProjectEjb();
        CalendarEntity calendar;
        calendar = calendarEJB.obtainHoliday(project, selectedDate());
        calendar.setDescription(description);
        calendar.setName(name);
        calendarEJB.update(calendar);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Update",
                "The date was correctly updated"));
        RequestContext.getCurrentInstance().addCallbackParam("operationResult", true);
    }

    //Manuel Rodríguez
    //Hector William
    public void deleteDateSelect(final ActionEvent actionEvent) {
        CalendarProjectEjb calendarEJB;
        calendarEJB = new CalendarProjectEjb();
        calendarEJB.delete(calendarEJB.obtainHoliday(project, selectedDate()));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Delete",
                "The date was correctly Deleted"));
        RequestContext.getCurrentInstance().addCallbackParam("operationResult", true);
        RequestContext.getCurrentInstance().addCallbackParam("operation", "delete");
    }

    //Manuel Rodríguez
    public CalendarEntity readyCalendarEntity() {
        CalendarEntity calendarentity;
        calendarentity = new CalendarEntity();
        calendarentity.setHoliday(selectedDate());
        calendarentity.setDescription(description);
        calendarentity.setName(name);
        calendarentity.setProject(project);
        return calendarentity;
    }

    //Manuel Rodríguez
    public void fillCalendar() {
        String actualDate;
        String currentCDate;
        List<CalendarEntity> calendarHolyDates;
        calendarHolyDates = new CalendarProjectEjb().obtainHolidays(project);
        for (CalendarEntity calendar : calendarHolyDates) {
            actualDate = format.format(date1);
            currentCDate = format.format(calendar.getHoliday().getTime());
            if (actualDate.equals(currentCDate)) {
                name = calendar.getName();
                description = calendar.getDescription();
                this.disableAdd = true;
                this.disableEditRemove = false;
                this.disableNameField = false;
                this.disableDescField = false;
                return;
            }
        }
        this.disableAdd = false;
        this.disableNameField = false;
        this.disableDescField = false;
        this.disableEditRemove = true;
    }

    public boolean isDisableAdd() {
        return disableAdd;
    }

    //Manuel Rodríguez
    public String[] getHolidays() {
        CalendarProjectEjb calendarPEjb;
        calendarPEjb = new CalendarProjectEjb();
        List<CalendarEntity> holidays ;
        holidays= calendarPEjb.obtainHolidays(project);
        String[] hdArray ;
        hdArray = new String[holidays.size()];
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.UK);
        for (int i = 0; i < holidays.size(); i++) {
            hdArray[i] = "'" + dateFormat.format(holidays.get(i).getHoliday().getTime()) + "'";
            System.out.println(hdArray[i] + "-");
        }
        return hdArray;
    }

    //HWilliamRS
    public boolean existsCalendar() {
        System.out.println(this.project.getChosenNumIteration());
        return (this.project.getChosenNumIteration() >= 1);
    }

    //HWilliamRS
    public void redirectIfNoCalendar(final String view) {
        FacesContext context;
        if (!existsCalendar()) {
            context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No Calendar Exist",
                    "Please configure the calendar's project to be able to edit it."));
            try {
                Logger.getLogger(CalendarProjectBean.class.getName()).log(Level.WARNING, "Not Calendar Setted For the opened Project.", view);
                context.getExternalContext().redirect(view + ".xhtml");
            } catch (IOException ex) {
                Logger.getLogger(CalendarProjectBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
