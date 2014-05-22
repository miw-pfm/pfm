/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import eui.miw.pfm.util.TypeIteration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose De Los Santos
 * @author Manuel Alvarez
 */
@Entity
@Table(name = "iterations", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"type", "iterValue", "project"})
})
public class IterationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeIteration typeIteration;

    @Column(name = "iterValue")
    @NotNull
    private int iterValue;

    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Calendar endDate;

    @ManyToOne
    @JoinColumn(name = "project", referencedColumnName = "id", nullable = false)
    private ProjectEntity project;

    /**
     *
     * @author César Martínez
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iteration")
    private List<WorkUnitEntity> workUnits = new ArrayList<>();

    public IterationEntity() {
    }

    public IterationEntity(Integer id, TypeIteration typeIteration, int iterValue, Calendar startDate, Calendar endDate, ProjectEntity project) {
        this.id = id;
        this.typeIteration = typeIteration;
        this.iterValue = iterValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeIteration getTypeIteration() {
        return typeIteration;
    }

    public void setTypeIteration(TypeIteration typeIteration) {
        this.typeIteration = typeIteration;
    }

    public int getIterValue() {
        return iterValue;
    }

    public void setIterValue(int val) {
        this.iterValue = val;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public List<WorkUnitEntity> getWorkUnits() {
        return workUnits;
    }

    public void setWorkUnits(List<WorkUnitEntity> workUnits) {
        this.workUnits = workUnits;
    }

    public void addWorkUnit(final WorkUnitEntity w) {
        this.workUnits.add(w);
    }

    public void removeWorkUnit(final WorkUnitEntity w) {
        this.workUnits.remove(w);
    }

    public String getCodeIteration() {
        return "" + this.getTypeIteration().toString() + this.getIterValue();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IterationEntity)) {
            return false;
        }
        IterationEntity other = (IterationEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IterationEntity[ id=" + id + " type " + typeIteration + " iterValue" + iterValue + " startDate " + startDate + " endDate " + endDate + " project " + project + " ]";
    }
}
