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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jean Mubaied
 */
@Entity
@Table (name="calendartemplate")
public class CalendarTemplateEntity implements Serializable {

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

    public CalendarTemplateEntity() {
        super();
    }

    public CalendarTemplateEntity(final Integer id, final Calendar holiday, final String name) {//NOPMD
        this.id = id;
        this.holiday = holiday;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Calendar getHoliday() {
        return holiday;
    }

    public String getName() {
        return name;
    }

    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    public void setHoliday(final Calendar holiday) {
        this.holiday = holiday;
    }

    public void setName(final String name) {
        this.name = name;
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
        if (!(object instanceof CalendarTemplateEntity)) {
            return false;//NOPMD
        }
        final CalendarTemplateEntity other = (CalendarTemplateEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {//NOPMD
            return false;//NOPMD
        }
        return true;
    }

    @Override
    public String toString() {
        return "eui.miw.pfm.models.entities.CalendarTemplateEntity[ id=" + id + " ]";
    }

}
