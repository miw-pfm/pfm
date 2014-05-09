/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jean Mubaied
 * @author Fred Pena
 */
@Entity
@Table (name="calendar")
public class CalendarEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//NOPMD

    @Column(name = "holiday")
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar holiday;

    @Column(name = "name", length = 100)
    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "description", length = 255)
    @NotNull
    @Size(min = 3, max = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectEntity project;

    public CalendarEntity() {
        super();
    }

    public CalendarEntity(final Integer id, final Calendar holiday, final String name, final String description, final ProjectEntity project) {//NOPMD
        this.id = id;
        this.holiday = holiday;
        this.name = name;
        this.description = description;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    public Calendar getHoliday() {
        return holiday;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setHoliday(final Calendar holiday) {
        this.holiday = holiday;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);//NOPMD
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendarEntity)) {
            return false;//NOPMD
        }
        final CalendarEntity other = (CalendarEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {//NOPMD
            return false;//NOPMD
        }
        return true;
    }

    @Override
    public String toString() {
        return "eui.miw.pfm.models.entities.CalendarEntity[ id=" + id + " ]";
    }

}
