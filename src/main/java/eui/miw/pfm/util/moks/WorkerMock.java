package eui.miw.pfm.util.moks;

import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Clemencio Morales
 */
@SessionScoped
@Named
public class WorkerMock implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private LazyDataModel<WorkerEntityMock> lazyModel;
    
    private String idWorker;
    private String nameWorker;
    private String surnameWorker;
    private String dniWorker;
    private String emailWorker;
    private String gitUserWorker;
    
    public WorkerMock(){//NOPMD
        super();
    }

    public String getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(final String idWorker) {
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
    
    public String update(){
        return "This is the update method from the mock";
    }
}
