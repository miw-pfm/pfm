package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.CalendarProjectEjb;
import eui.miw.pfm.models.entities.CalendarEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.SessionMap;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Manuel Álvarez
 * @code added Manuel Rodríguez
 * @code added Jean Mubaied
 */
@RequestScoped
@Named
public class CalendarProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CalendarEntity calendarEntity;
    private ProjectEntity project;
    private String name;
    private String description;
    private SimpleDateFormat format = new SimpleDateFormat("d/M/yy");
    private Date date1;
    private static final Logger LOG = Logger.getLogger(ConfProjectBean.class.getName());//NOPMD
    private boolean disableAdd = false;
    private boolean disableEditRemove = false;

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

    public int getWorkingDays() {
        final Calendar startDate;
        startDate = new GregorianCalendar();
        final Calendar endDate;
        endDate = new GregorianCalendar();
        startDate.setTime(this.project.getStartDate());
        endDate.setTime(this.project.getEndDate());
        int diffDays = 0;
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

    public Calendar fecha_seleccionada() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        return calendar;
    }

    //Manuel Rodríguez
    public void addDateSelect() {
        new CalendarProjectEjb().create(readycalendarentity());
    }

    //Manuel Rodríguez
    public void updateDateSelect() {
        CalendarProjectEjb c = new CalendarProjectEjb();
        CalendarEntity ce = c.obtainHoliday(project, fecha_seleccionada());
        ce.setDescription(description);
        ce.setName(name);
        c.update(ce);
    }

    //Manuel Rodríguez
    public void deleteDateSelect() {
        CalendarProjectEjb c = new CalendarProjectEjb();
        c.delete(c.obtainHoliday(project, fecha_seleccionada()));
    }

    //Manuel Rodríguez
    public CalendarEntity readycalendarentity() {
        CalendarEntity calendarentity = new CalendarEntity();
        calendarentity.setHoliday(fecha_seleccionada());
        calendarentity.setDescription(description);
        calendarentity.setName(name);
        calendarentity.setProject(project);
        return calendarentity;
    }

    //Manuel Rodríguez
    public String llena() {
        List<CalendarEntity> ces = new CalendarProjectEjb().obtainHolidays(project);
        for (CalendarEntity ce : ces) {
            String a = format.format(date1);
            String b = format.format(ce.getHoliday().getTime());
            if (a.equals(b)) {
                name = ce.getName();
                description = ce.getDescription();
                this.disableAdd = true;
                this.disableEditRemove = false;
            }
        }
        return null;
    }

    public boolean isDisableAdd() {
        return disableAdd;
    }

    //Manuel Rodríguez
    public String[] getHolidays() {
        CalendarProjectEjb calendarProjectEjb = new CalendarProjectEjb();
        List<CalendarEntity> holidays = calendarProjectEjb.obtainHolidays(project);
        String[] a = new String[holidays.size()];
        SimpleDateFormat formato = new SimpleDateFormat("MMMM d, yyyy", Locale.UK);
        for (int i = 0; i < holidays.size(); i++) {
            a[i] = "'" + formato.format(holidays.get(i).getHoliday().getTime()) + "'";
            System.out.println(a[i] + "-");
        }
        return a;
    }
}
