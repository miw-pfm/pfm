package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.CalendarProjectEjb;
import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.entities.CalendarEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.SessionMap;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Manuel Álvarez
 */
@RequestScoped
@Named
public class CalendarProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private CalendarEntity calendarEntity;
    private ProjectEntity project;
    private SessionMap sessionMap;
    private Calendar calendar = new GregorianCalendar();
    private Calendar startdate = new GregorianCalendar();
    private Calendar enddate = new GregorianCalendar();
    private Date date1;
    private static final Logger LOG = Logger.getLogger(ConfProjectBean.class.getName());//NOPMD

    public CalendarProjectBean() {
        super();
        project = new ProjectEntity();
        calendarEntity = new CalendarEntity();
        this.sessionMap = new SessionMap();

        try {
            this.project = ((ProjectEntity) this.sessionMap.get("project"));
        } catch (Exception e) {
            LOG.warning("No session exist");
        }
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }
    
     public String create() {
        this.calendarEntity.setProject(project);
        CalendarProjectEjb ejb = new CalendarProjectEjb();
        ejb.create(this.calendarEntity);
        return null;
    }
     
      public String update() {
        CalendarProjectEjb ejb = new CalendarProjectEjb();
        this.calendarEntity.setProject(project);
        ejb.update(this.calendarEntity);                  
        return null;
    }
      
      public String delete() {
        CalendarProjectEjb ejb = new CalendarProjectEjb();
        ejb.delete(this.calendarEntity);     
        return null;
    }

    public void handleDateSelect(SelectEvent event) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(date1);
        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
        CalendarEntity calendarEntity= new CalendarEntity();
        CalendarProjectEjb calendarProjectEjb= new CalendarProjectEjb();
        calendarEntity.setHoliday(calendar);
        calendarEntity.setDescription("dd");
        calendarEntity.setName("nn");
        calendarEntity.setProject(project);
        calendarProjectEjb.create(calendarEntity);
        //Así se recupera la fecha seleccionada
        // format.format(calendar.getTime())
        //--- Para mostrar el mensaje con la fecha
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
    
    public String[] getHolidays() {
        String[] a= new String[3];
        a[0]="May 8, 2014";
        a[1]="May 15, 2014";
        a[2]="May 23, 2014";
        return a;
    }
}
