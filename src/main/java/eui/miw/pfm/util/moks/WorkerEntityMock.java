package eui.miw.pfm.util.moks;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * @author Clemencio Morales Lucas
 */
@Entity
@Table(name = "workerEntityMock")
public class WorkerEntityMock implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWorker")
    private Integer idWorker; //NOPMD
    
    @Column(name = "nameWorker")
    @NotNull
    @Size(min = 3, max = 100)
    private String nameWorker;

    @Column(name = "surnameWorker")
    @NotNull
    @Size(min = 6, max = 100)
    private String surnameWorker;
    
    @Column(name = "dniWorker")
    @NotNull
    @Size(min = 8, max = 9)
    private String dniWorker;
    
    @Column(name = "emailWorker")
    @NotNull
    @Size(min = 10, max = 100)
    private String emailWorker;
    
    @Column(name = "gitUserWorker")
    @NotNull
    @Size(min = 5, max = 30)
    private String gitUserWorker;

    public WorkerEntityMock(Integer idWorker, String nameWorker, String surnameWorker, String dniWorker, String emailWorker, String gitUserWorker) {//NOPMD
        this.idWorker = idWorker;
        this.nameWorker = nameWorker;
        this.surnameWorker = surnameWorker;
        this.dniWorker = dniWorker;
        this.emailWorker = emailWorker;
        this.gitUserWorker = gitUserWorker;
    }

    public WorkerEntityMock() {
        super();
    }

    public Integer getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(final Integer idWorker) {
        this.idWorker = idWorker;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public void setNameWorker(final String nameWorker) {
        this.nameWorker = nameWorker;
    }

    public String getSurnameWorker() {
        return surnameWorker;
    }

    public void setSurnameWorker(final String surnameWorker) {
        this.surnameWorker = surnameWorker;
    }

    public String getDniWorker() {
        return dniWorker;
    }

    public void setDniWorker(final String dniWorker) {
        this.dniWorker = dniWorker;
    }

    public String getEmailWorker() {
        return emailWorker;
    }

    public void setEmailWorker(final String emailWorker) {
        this.emailWorker = emailWorker;
    }

    public String getGitUserWorker() {
        return gitUserWorker;
    }

    public void setGitUserWorker(final String gitUserWorker) {
        this.gitUserWorker = gitUserWorker;
    }

    @Override
    public String toString() {
        return "WorkerEntityMock{" + "idWorker=" + idWorker + ", nameWorker=" + nameWorker + ", surnameWorker=" + surnameWorker + ", dniWorker=" + dniWorker + ", emailWorker=" + emailWorker + ", gitUserWorker=" + gitUserWorker + '}';
    }

    
}

