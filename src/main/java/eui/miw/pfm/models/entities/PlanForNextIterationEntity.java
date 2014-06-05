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
import javax.validation.constraints.NotNull;

/**
 *
 * *@author Fred Peña
 */
@Entity
@Table(name = "planfornextiteration")
public class PlanForNextIterationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//NOPMD

    @Column(name = "inception_value", columnDefinition = "Decimal(10,2) default '0.00'")
    @NotNull
    private double inceptionValue;

    @Column(name = "elaboration_value", columnDefinition = "Decimal(10,2) default '0.00'")
    @NotNull
    private double elaborationValue;

    @Column(name = "contrution_value", columnDefinition = "Decimal(10,2) default '0.00'")
    @NotNull
    private double contrutionValue;

    @Column(name = "transition_value", columnDefinition = "Decimal(10,2) default '0.00'")
    @NotNull
    private double transitionValue;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    private ActivityEntity activity;

    public PlanForNextIterationEntity() {
        super();
    }

    public PlanForNextIterationEntity(final Integer id, final double elaborationValue, final double contrutionValue, final double transitionValue, final ActivityEntity activity) {
        this.id = id;
        this.elaborationValue = elaborationValue;
        this.contrutionValue = contrutionValue;
        this.transitionValue = transitionValue;
        this.activity = activity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public double getInceptionValue() {
        return inceptionValue;
    }

    public void setInceptionValue(final double inceptionValue) {
        this.inceptionValue = inceptionValue;
    }

    public double getElaborationValue() {
        return elaborationValue;
    }

    public void setElaborationValue(final double elaborationValue) {
        this.elaborationValue = elaborationValue;
    }

    public double getContrutionValue() {
        return contrutionValue;
    }

    public void setContrutionValue(final double contrutionValue) {
        this.contrutionValue = contrutionValue;
    }

    public double getTransitionValue() {
        return transitionValue;
    }

    public void setTransitionValue(final double transitionValue) {
        this.transitionValue = transitionValue;
    }

    public ActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(final ActivityEntity activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final PlanForNextIterationEntity other = (PlanForNextIterationEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanForNextIterationEntity{" + "id=" + id + ", inceptionValue=" + inceptionValue + ", elaborationValue=" + elaborationValue + ", contrutionValue=" + contrutionValue + ", transitionValue=" + transitionValue + ", activity=" + activity + '}';
    }

}
