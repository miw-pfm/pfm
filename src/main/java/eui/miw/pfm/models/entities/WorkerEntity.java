/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import eui.miw.pfm.util.moks.entities.TasksEntityMock;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    @NotNull
    @Size(min = 2, max = 100, message = "Error. Name logitud incorrecta")
    private String name;

    @Column(name = "surname", length = 100)
    @NotNull
    @Size(min = 2, max = 100, message = "Error. Surname logitud incorrecta")
    private String surname;

    @Column(name = "dni", length = 10, unique=true)    
    @NotNull (message = "Error. DNI not NULL")
    @Size(min = 2, max = 10, message = "Error. DNI logitud incorrecta")
    @Pattern(regexp = "^[0-9]{8}[a-zA-Z]{1}$", message = "DNI Incorrecto")       
    private String dni;

    @Column(name = "email", length = 255, unique=true)
    @NotNull (message = "Error. Email not NULL")
    @Size(min = 6, max = 50, message = "Error. Email logitud incorrecta")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{2,15}@[a-zA-Z0-9_-]{2,15}.[a-zA-Z]{2,4}(.[a-zA-Z]{2,4})?$", message = "Email Incorrect")
    private String email;

    @Column(name = "git_user", length = 100, unique=true)
    @NotNull(message = "Error. Git User not NULL")
    @Size(min = 2, max = 100, message = "Error. Git User longitud incorrecta")
    private String gitUser;

    @JoinTable(name = "projects_workers", joinColumns = {
        @JoinColumn(name = "worker_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "project_id", referencedColumnName = "id")})
    @ManyToMany
    private List<ProjectEntity> projects = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker")
    private List<TasksEntityMock> taskMock = new ArrayList<>();
    
    /**
     *
     * @author César Martínez
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker")
    private Set<WorkUnitEntity> workUnits = new HashSet<>();

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

    public void addProject(final ProjectEntity p) {
        this.projects.add(p);
    }

    public void removeProject(final ProjectEntity p) {
        this.projects.remove(p);
    }

    public void addTask(final TasksEntityMock t) {
        this.taskMock.add(t);
    }

    public void removeTask(final TasksEntityMock t) {
        this.taskMock.remove(t);
    }

    public List<TasksEntityMock> getTaskMock() {
        return taskMock;
    }

    public void setTaskMock(final List<TasksEntityMock> taskMock) {
        this.taskMock = taskMock;
    }

    public Set<WorkUnitEntity> getWorkUnits() {
        return workUnits;
    }

    public void setWorkUnits(Set<WorkUnitEntity> workUnits) {
        this.workUnits = workUnits;
    }
    
    public void addWorkUnit(final WorkUnitEntity w) {
        this.workUnits.add(w);
    }

    public void removeWorkUnit(final WorkUnitEntity w) {
        this.workUnits.remove(w);
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
        final WorkerEntity other = (WorkerEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WorkerEntity{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", dni=" + dni + ", gitUser=" + gitUser + '}';
    }

}
