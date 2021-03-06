package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
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

    @Column(name = "name")
    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Transient
    private String stringStartDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Transient
    private String stringEndDate;

    @Column(name = "week_per_iteration")
    private int weekNumIteration;

    @Transient
    private float estimatedNumIteration;

    @Column(name = "chosen_num_iteration")
    private int chosenNumIteration; //NOPMD 

    @Column(name = "description")
    private String description; //NOPMD
    
    @Column(name = "workers_in_plan")
    @Min(0)
    private int workersInPlan;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<UseCaseEntity> useCases = new HashSet<>();

    /**
     *
     * @author César Martínez
     * @Roberto Amor
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<RiskEntity> risk = new ArrayList<>();

    @JoinTable(name = "projects_workers", joinColumns = {
        @JoinColumn(name = "project_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "worker_id", referencedColumnName = "id")})
    @ManyToMany
    private List<WorkerEntity> workers = new ArrayList<>();

    public ProjectEntity(Integer id, String name, Date startDate, String stringStartDate, Date endDate, String stringEndDate, int weekNumIteration, int chosenNumIteration, String description, UserEntity owner) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.stringStartDate = stringStartDate;
        this.endDate = endDate;
        this.stringEndDate = stringEndDate;
        this.weekNumIteration = weekNumIteration;
        this.chosenNumIteration = chosenNumIteration;
        this.description = description;
        this.owner = owner;
    }

    public ProjectEntity() {
        super();
    }

    public ProjectEntity(final Integer id) {  //NOPMD
        this.id = id;
    }

    public ProjectEntity(final Integer id, final String name, final Date startDate, final Date endDate, final int weekNumIteration, final int chosenNumIteration, final String description, final UserEntity owner) {  //NOPMD
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weekNumIteration = weekNumIteration;
        this.chosenNumIteration = chosenNumIteration;
        this.description = description;
        this.owner = owner;
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

    public String getStringStartDate() {
        return this.stringStartDate;
    }

    public void updateStringDates() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy");
        this.stringStartDate = sdf.format(this.startDate);
        this.stringEndDate = sdf.format(this.endDate);
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

    public float getEstimatedNumIteration() {
        return estimatedNumIteration;
    }

    public void setEstimatedNumIteration(float estimatedNumIteration) {
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

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(final UserEntity owner) {
        this.owner = owner;
    }

    public String getStringEndDate() {
        return stringEndDate;
    }

    public void setStringEndDate(final String stringEndDate) {
        this.stringEndDate = stringEndDate;
    }

    public Set<UseCaseEntity> getUseCases() {
        return useCases;
    }

    public void setUseCases(final Set<UseCaseEntity> useCases) {
        this.useCases = useCases;
    }

    public void addUseCases(final UseCaseEntity usecases) {
        this.useCases.add(usecases);
    }

    public void removeUseCases(final UseCaseEntity usecases) {
        this.useCases.remove(usecases);
    }

    public List<RiskEntity> getRisk() {
        return risk;
    }

    public void setRisk(final List<RiskEntity> risk) {
        this.risk = risk;
    }

    public void addRisk(final RiskEntity risk) {
        this.risk.add(risk);
    }

    public void removeRisk(final RiskEntity risk) {
        this.risk.remove(risk);
    }

    public List<WorkerEntity> getWorkers() {
        return workers;
    }

    public void setWorkers(final List<WorkerEntity> workers) {
        this.workers = workers;
    }

    public void addWorker(final WorkerEntity w) {
        this.workers.add(w);
    }

    public void removeWorker(final WorkerEntity w) {
        this.workers.remove(w);
    }

    public int getWorkersInPlan() {
        return workersInPlan;
    }

    public void setWorkersInPlan(int workersInPlan) {
        this.workersInPlan = workersInPlan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProjectEntity other = (ProjectEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" + "id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", weekNumIteration=" + weekNumIteration + ", chosenNumIteration=" + chosenNumIteration + ", description=" + description + ", owner=" + owner + '}';
    }
}
