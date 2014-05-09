package eui.miw.pfm.util.moks;
/*
 @author Clemencio Morales
 @author Jose M Villar
 */

import eui.miw.pfm.controllers.beans.Bean;
import eui.miw.pfm.controllers.beans.WorkerBean;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

@Named
@RequestScoped
public class ListWorkersBeanMock {

    private transient final LazyDataModel<WorkerEntity> lazyModel;
    private WorkerEntity selectedWorker;
    private List<WorkerEntity> workers;
    private static final Logger LOGGER = Logger.getLogger(WorkerBean.class.getName());

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
//        WorkerEntity worker;
//        worker = (WorkerEntity) event.getObject();
//        WorkerBean wbJSF;
//        wbJSF = new WorkerBean();
//        wbJSF.editWorker(worker);
//        
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        request.setAttribute("workerEntity", worker);
//
//        
//        ExternalContext externalContext;
//        externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        try {
//            externalContext.redirect(wbJSF.editWorker(worker) + ".xhtml");
//        } catch (IOException ex) {
//            Logger.getLogger(ListWorkersBeanMock.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
   
    public String editWorker(final WorkerEntity worker) {
        return "editWorker";
    }
}
