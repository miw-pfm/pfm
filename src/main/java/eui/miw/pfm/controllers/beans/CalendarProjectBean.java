package eui.miw.pfm.controllers.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private Calendar calendar = new GregorianCalendar();
    private Date date1;
     
    public Date getDate1() {
        return date1;
    }
 
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
 
    public void handleDateSelect(SelectEvent event) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(date1);
        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
        
        //Así se recupera la fecha seleccionada
        // format.format(calendar.getTime())
        
        //--- Para mostrar el mensaje con la fecha
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
}
