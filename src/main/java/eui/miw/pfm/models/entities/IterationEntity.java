/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jose De Los Santos
 */
@Entity
@Table(name = "iterations",  uniqueConstraints={
   @UniqueConstraint(columnNames={"type", "iter_value", "project_id"})
})
public class IterationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "type")
    @NotNull    
    private char type;
    
    @Column(name = "iter_value")
    @NotNull    
    private int iter_value;
    
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
   

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project;
    
    public IterationEntity() {
    }

    public IterationEntity(Integer id, char type, int iter_value, Date startDate, Date endDate, ProjectEntity project) {
        this.id = id;
        this.type = type;
        this.iter_value = iter_value;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer
            id) {
        this.id = id;
    }

    public char geType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getIter_value() {
        return iter_value;
    }

    public void setIter_value(int val) {
        this.iter_value = val;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
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
        return "IterationEntity[ id=" + id +" type "+type+" iter_value"+iter_value+" startDate "+startDate+" endDate "+endDate+" project "+project+" ]";
    }
    
}
