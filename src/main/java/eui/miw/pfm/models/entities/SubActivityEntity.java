/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * @author Jean Mubaied
 */
@Entity
@Table(name = "activities")
public class SubActivityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     *
     * @author César Martínez
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private List<WorkUnitEntity> workUnits = new ArrayList<>();

    public SubActivityEntity() {
        super();
    }

    public SubActivityEntity(final Integer id, final String name, final String code) {//NOPMD
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
