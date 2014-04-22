package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "projects")
public class ProjectEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; //NOPMD
    
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate; 

    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(name = "week_num_iteration")
    private int weekNumIteration;

    @Column(name = "estimated_num_iteration")
    private int estimatedNumIteration;  //NOPMD

    @Column(name = "chosen_num_iteration")
    private int chosenNumIteration; //NOPMD 
    
    @Column(name = "description")
    private String description; //NOPMD 
 
    public ProjectEntity() {
        super();
    }

    public ProjectEntity(final Integer id) {  //NOPMD
        this.id = id;
    }

    public ProjectEntity(final Integer id, final String name, final Date startDate, final Date endDate, final int weekNumIteration, final int estimatedNumIteration, final int chosenNumIteration, final String description) {  //NOPMD
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weekNumIteration = weekNumIteration;
        this.estimatedNumIteration = estimatedNumIteration;
        this.chosenNumIteration = chosenNumIteration;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {  //NOPMD
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public int getWeekNumIteration() {
        return weekNumIteration;
    }

    public void setWeekNumIteration(final int weekNumIteration) {
        this.weekNumIteration = weekNumIteration;
    }

    public int getEstimatedNumIteration() {
        return estimatedNumIteration;
    }

    public void setEstimatedNumIteration(final int estimatedNumIteration) { //NOPMD
        this.estimatedNumIteration = estimatedNumIteration;
    }

    public int getChosenNumIteration() {
        return chosenNumIteration;
    }

    public void setChosenNumIteration(final int chosenNumIteration) { //NOPMD
        this.chosenNumIteration = chosenNumIteration;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }    

    @Override
    public String toString() {
        return "Projects[ id=" + id + " ]";
    }
    
}
