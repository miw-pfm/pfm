
package eui.miw.pfm.util.moks;
/*
@author Clemencio Morales
*/

import eui.miw.pfm.models.entities.WorkerEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

@Named
@RequestScoped
public class ListWorkersBeanMock {

    private transient final LazyDataModel<WorkerEntity> lazyModel;
    private WorkerEntity selectedWorker;
    private List<WorkerEntity> workers;

    public ListWorkersBeanMock() {
        ListWorkersEjbMock eaE;
        eaE = new ListWorkersEjbMock();
        this.workers = eaE.obtainWorker();
        lazyModel = new LazyWorkerDataModelMock(workers);
    }

    public List<WorkerEntity> getWorkers() {
        return workers;
    }

    public void setWorkers(final List<WorkerEntity> workers) {
        this.workers = workers;
    }

    public LazyDataModel<WorkerEntity> getLazyModel() {
        return lazyModel;
    }

  public WorkerEntity getSelectedWorker() {
        return selectedWorker;
    }

    public void setSelectedWorker(final WorkerEntity selectedWorker) {
        this.selectedWorker = selectedWorker;
    }

    public void onRowSelect(SelectEvent event) {//NOPMD
        //FacesMessage msg = new FacesMessage("Project Selected", (ProjectEntity) event.getObject());
//        ProjectEntity project ;
//        project = (ProjectEntity) event.getObject();
//        OpenProjectBean opJSF;
//        opJSF = new OpenProjectBean();
//        opJSF.showOpenProject(project);
//        FacesContext facesContext ;
//        facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext ;
//        externalContext = facesContext.getExternalContext();
//        try {
//            externalContext.redirect(opJSF.showOpenProject(project )  + ".xhtml");
//        } catch (IOException ex) {
//            Logger.getLogger(ListProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
