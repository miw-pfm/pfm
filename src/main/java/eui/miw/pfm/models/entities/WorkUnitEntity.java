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
@Table(name = "risks")
public class WorkUnitEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    private WorkerEntity worker;
    
    @ManyToOne
    @JoinColumn(name = "iteration_id", referencedColumnName = "id", nullable = false)
    private IterationEntity iteration;
    
    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    private ActivityEntity activity;

    public WorkUnitEntity() {
    }

    public WorkUnitEntity(final Integer id) {
        this.id = id;
    }

    public WorkUnitEntity(final Integer id, final WorkerEntity worker, final IterationEntity iteration, final ActivityEntity activity) {
        this.id = id;
        this.worker = worker;
        this.iteration = iteration;
        this.activity = activity;
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

    public void setIteration(IterationEntity iteration) {
        this.iteration = iteration;
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
        final WorkUnitEntity other = (WorkUnitEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RiskEntity{" + "id=" + id + ", worker=" + worker + ", iteration=" + iteration + '}';
    }

}
