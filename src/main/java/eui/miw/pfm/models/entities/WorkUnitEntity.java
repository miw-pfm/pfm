/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author César Martínez
 */
@Entity
@Table(name = "work_units")
public class WorkUnitEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = true)
    private WorkerEntity worker;
    
    @ManyToOne
    @JoinColumn(name = "iteration_id", referencedColumnName = "id", nullable = false)
    private IterationEntity iteration;
    
    @ManyToOne
    @JoinColumn(name = "subactivity_id", referencedColumnName = "id", nullable = false)
    private SubActivityEntity subactivity;

    public WorkUnitEntity() {
        super();
    }

    /**
    *
    * @author Jose Mª Villar
    */    
    public WorkUnitEntity(final IterationEntity iteration, final SubActivityEntity subactivity) {
        this.iteration = iteration;
        this.subactivity = subactivity;
    }

    public WorkUnitEntity(final Integer id, final WorkerEntity worker, final IterationEntity iteration, final SubActivityEntity activity) {
        this.id = id;
        this.worker = worker;
        this.iteration = iteration;
        this.subactivity = activity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    public WorkerEntity getWorker() {
        return worker;
    }

    public void setWorker(final WorkerEntity worker) {
        this.worker = worker;
    }

    public IterationEntity getIteration() {
        return iteration;
    }

    public void setIteration(final IterationEntity iteration) {
        this.iteration = iteration;
    }

    public SubActivityEntity getSubactivity() {
        return subactivity;
    }

    public void setSubactivity(final SubActivityEntity subactivity) {
        this.subactivity = subactivity;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorkUnitEntity other = (WorkUnitEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "WorkUnitEntity{" + "id=" + id + ", worker=" + worker + ", iteration=" + iteration + ", subactivity=" + subactivity + '}';
    }
}
