/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose Mª Villar
 * @author Jean Mubaied
 */
@Entity
@Table(name = "activities")
public class ActivityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    @NotNull
    private String name;

    @Column(name = "code", length = 5)
    @NotNull
    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private List<SubActivityEntity> subactivity = new ArrayList<>();

    public ActivityEntity(final Integer id, final String name, final String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public ActivityEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public List<SubActivityEntity> getSubActivity() {
        return subactivity;
    }

    public void setSubActivity(List<SubActivityEntity> subactivity) {
        this.subactivity = subactivity;
    }

    public void addSubActivity(final SubActivityEntity sa) {
        this.subactivity.add(sa);
    }

    public void removeSubactivity(final SubActivityEntity sa) {
        this.subactivity.remove(sa);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final ActivityEntity other = (ActivityEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return code + ".-" + name;
    }

}
