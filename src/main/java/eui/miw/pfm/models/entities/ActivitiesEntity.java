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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose Mª Villar
 * @author Jean Mubaied
 */
public class ActivitiesEntity implements Serializable {
    
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
    private List<ActivityEntity> subactivity = new ArrayList<>();      

    public ActivitiesEntity(final Integer id, final String name, final String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public ActivitiesEntity() {
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

    public List<ActivityEntity> getSubactivity() {
        return subactivity;
    }

    public void setSubactivity(List<ActivityEntity> subactivity) {
        this.subactivity = subactivity;
    }

    public void addSubactivity(final ActivityEntity sa) {
        this.subactivity.add(sa);
    }

    public void removeSubactivity(final ActivityEntity sa) {
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
        final ActivitiesEntity other = (ActivitiesEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ActivitiesEntity{" + "id=" + id + ", name=" + name + ", code=" + code + ", subactivity=" + subactivity + '}';
    }
    
    
    
}
