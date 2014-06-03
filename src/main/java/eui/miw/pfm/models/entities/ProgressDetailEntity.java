package eui.miw.pfm.models.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Clemencio Morales
 */
@Entity
@Table(name = "progressdetail")
public class ProgressDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "useCase_id", referencedColumnName = "id", nullable = false)
    private UseCaseEntity useCase;

    @ManyToOne
    @JoinColumn(name = "iteration_id", referencedColumnName = "id", nullable = false)
    private IterationEntity iteration;

    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id", nullable = false)
    private DisciplineEntity discipline;

    @Column(name = "percent", length = 5)
    @NotNull
    @Size(min = 1, max = 5)
    private Integer percent;
    
    @Column(name = "isChecked", length = 10)
    @NotNull
    @Size(min = 1, max = 10)
    private boolean isChecked;

    public ProgressDetailEntity(){
        super();
    }
    
    public ProgressDetailEntity(Integer id){
        this.setId(id);
    }
    
    public ProgressDetailEntity(Integer id, UseCaseEntity useCase, IterationEntity iteration, DisciplineEntity discipline, Integer percent, boolean isChecked) {
        this.id = id;
        this.useCase = useCase;
        this.iteration = iteration;
        this.discipline = discipline;
        this.percent = percent;
        this.isChecked = isChecked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UseCaseEntity getUseCase() {
        return useCase;
    }

    public void setUseCase(UseCaseEntity useCase) {
        this.useCase = useCase;
    }

    public IterationEntity getIteration() {
        return iteration;
    }

    public void setIteration(IterationEntity iteration) {
        this.iteration = iteration;
    }

    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEntity discipline) {
        this.discipline = discipline;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "ProgressDetailEntity{" + "id=" + id + ", useCase=" + useCase + ", iteration=" + iteration + ", discipline=" + discipline + ", percent=" + percent + ", isChecked=" + isChecked + '}';
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
        final ProgressDetailEntity other = (ProgressDetailEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.useCase, other.useCase)) {
            return false;
        }
        if (!Objects.equals(this.iteration, other.iteration)) {
            return false;
        }
        if (!Objects.equals(this.discipline, other.discipline)) {
            return false;
        }
        if (!Objects.equals(this.percent, other.percent)) {
            return false;
        }
        return this.isChecked == other.isChecked;
    }
}
