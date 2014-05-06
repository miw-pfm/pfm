/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jean Mubaied
 */
@Entity
public class CalendarTemplateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public CalendarTemplateEntity(Integer id, Calendar holiday, String name) {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHoliday(Calendar holiday) {
        this.holiday = holiday;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof CalendarTemplateEntity)) {
            return false;
        }
        CalendarTemplateEntity other = (CalendarTemplateEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eui.miw.pfm.models.entities.CalendarTemplateEntity[ id=" + id + " ]";
    }

}
