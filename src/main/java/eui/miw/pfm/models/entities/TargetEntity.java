/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Jean Mubaied
 */
@Entity
@Table(name = "target" , 
        uniqueConstraints={@UniqueConstraint(columnNames={"project_id","discipline_id"})})
public class TargetEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "inception")
    @NotNull
//    @Pattern(regexp="^[1-9][0-9]?$|^100$", message="Inception is invalid.")
    private Integer inception;  
    
    @Column(name = "elaboration")
    @NotNull
//    @Pattern(regexp="^[1-9][0-9]?$|^100$", message="Elaboration is invalid.")
    private Integer elaboration;  
    
    @Column(name = "construction")
    @NotNull
//    @Pattern(regexp="^[1-9][0-9]?$|^100$", message="Construction is invalid.")
    private Integer construction;  
    
    @Column(name = "transition")
    @NotNull
//    @Pattern(regexp="^[1-9][0-9]?$|^100$", message="Transition is invalid.")
    private Integer transition; 
    
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project;
    
    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id", nullable = false)
    private DisciplineEntity discipline;
    
    public TargetEntity() {
        super();
    }

    public TargetEntity(final Integer id,final Integer inception,final Integer construction,final Integer elaboration,final Integer transition,final ProjectEntity project,final DisciplineEntity discipline) {
        this.id = id;
        this.elaboration = elaboration;
        this.inception = inception;
        this.construction = construction;
        this.transition = transition;
        this.project = project;
        this.discipline = discipline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInception() {
        return inception;
    }

    public void setInception(final Integer inception) {
        this.inception = inception;
    }

    public Integer getElaboration() {
        return elaboration;
    }

    public void setElaboration(final Integer elaboration) {
        this.elaboration = elaboration;
    }

    public Integer getConstruction() {
        return construction;
    }

    public void setConstruction(final Integer construction) {
        this.construction = construction;
    }

    public Integer getTransition() {
        return transition;
    }

    public void setTransition(final Integer transition) {
        this.transition = transition;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(final DisciplineEntity discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "TargetEntity{" + "id=" + id + ", inception=" + inception + ", elaboration=" + elaboration + ", construction=" + construction + ", transition=" + transition + ", project=" + project + ", discipline=" + discipline + '}';
    }  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TargetEntity other = (TargetEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
     
}
