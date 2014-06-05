/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto Amor
 * *@author Fred Peña
 * @author Clemencio Morales
 * @modificado Manuel Rodriguez
 */
@Entity
@Table(name = "usecases")
public class UseCaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//NOPMD

    @Column(name = "name", length = 140)
    @NotNull
    @Size(min = 3, max = 140)
    private String name;

    @Column(name = "description", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String description;

    @Column(name = "enabled", length = 1, columnDefinition = "boolean default true")
    @NotNull
    @Size(min = 1, max = 1)
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "iteration_id", referencedColumnName = "id", nullable = true)
    private IterationEntity iteration;

    @JoinTable(name = "risks_usecases", joinColumns = {
        @JoinColumn(name = "usecase_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "risk_id", referencedColumnName = "id")})
    @ManyToMany
    private List<RiskEntity> risks = new ArrayList<>();

    public UseCaseEntity() {
        super();
    }

    public UseCaseEntity(final Integer id) {//NOPMD
        this.id = id;
    }

    public UseCaseEntity(Integer id, String name, String description, boolean enabled, ProjectEntity project, IterationEntity iteration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.project = project;
        this.iteration = iteration;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    public IterationEntity getIteration() {
        return iteration;
    }

    public void setIteration(IterationEntity iteration) {
        this.iteration = iteration;
    }

    public List<RiskEntity> getRisks() {
        return risks;
    }

    public void setRisks(final List<RiskEntity> risks) {
        this.risks = risks;
    }

    public void addRisk(final RiskEntity risk) {
        this.risks.add(risk);
    }

    public void removeRisk(final RiskEntity risk) {
        this.risks.remove(risk);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);//NOPMD
        return hash;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UseCaseEntity)) {
            return false;//NOPMD
        }
        final UseCaseEntity other = (UseCaseEntity) object;
        if (this.id == other.id || (this.getName().equals(other.getName()) && (this.getProject().getId() == other.getProject().getId()))) {//NOPMD
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "UseCaseEntity{" + "id=" + id + ", name=" + name + ", description=" + description + ", enabled=" + enabled + ", project=" + project + ", iteration=" + iteration + ", risks=" + risks + '}';
    }
}
