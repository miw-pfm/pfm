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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author César Martínez
 * @author Fred Peña
 * @author Roberto Amor
 */
@Entity
@Table(name = "risks")
public class RiskEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100, unique = true)
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project;

    @Column(name = "risk_value")
    @NotNull
    @Min(1)
    @Max(5)
    private int value;

    @JoinTable(name = "risks_usecases", joinColumns = {
        @JoinColumn(name = "risk_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "usecase_id", referencedColumnName = "id")})
    @ManyToMany
    private List<UseCaseEntity> usecases = new ArrayList<>();

    public RiskEntity() {
    }

    public RiskEntity(final Integer id) {
        this.id = id;
    }

    public RiskEntity(final Integer id, final String name, final int value, final ProjectEntity project) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<UseCaseEntity> getUsecases() {
        return usecases;
    }

    public void setUsecases(final List<UseCaseEntity> usecases) {
        this.usecases = usecases;
    }

    public void addUsecase(final UseCaseEntity usecase) {
        this.usecases.add(usecase);
    }

    public void removeUsecase(final UseCaseEntity usecase) {
        this.usecases.remove(usecase);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
        final RiskEntity other = (RiskEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RiskEntity{" + "id=" + id + ", name=" + name + ", project=" + project + '}';
    }

}
