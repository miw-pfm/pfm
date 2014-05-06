/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto Amor
 */
@Entity
@Table(name = "workers")
public class WorkerEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", length = 100)
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @Column(name = "surname", length = 100)
    @NotNull
    @Size(min = 2, max = 100)
    private String surname;
    
    @Column(name = "dni", length = 10)
    @NotNull
    @Size(min = 2, max = 10)
    private String dni;
    
    @Column(name = "email", length = 255)
    @NotNull
    @Size(min = 6, max = 50)
    @Pattern(regexp = "^.+@.+\\..+$")
    private String email;
    
    @Column(name = "git_user", length = 100)
    @NotNull
    @Size(min = 2, max = 100)
    private String gitUser;
    
    @ManyToMany
    @JoinTable(
      name="projects_workers",
      joinColumns={@JoinColumn(name="worker_id", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="project_id", referencedColumnName="id")})
    private List<ProjectEntity> projects = new ArrayList<ProjectEntity>();

    public WorkerEntity() {
    }

    public WorkerEntity(final Integer id) {
        this.id = id;
    }

    public WorkerEntity(final Integer id, final String name, final String surname, final String dni, final String gitUser) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.gitUser = gitUser;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(final String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGitUser() {
        return gitUser;
    }

    public void setGitUser(final String gitUser) {
        this.gitUser = gitUser;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(final List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public void addProject(final ProjectEntity p){
        this.projects.add(p);
    }
    
    public void deleteProject(final ProjectEntity p){
        this.projects.remove(p);
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
        if (!(object instanceof WorkerEntity)) {
            return false;
        }
        WorkerEntity other = (WorkerEntity) object;
        if (this.id.equals(other.getId())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "WorkerEntity{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", dni=" + dni + ", gitUser=" + gitUser + '}';
    }
    
}
