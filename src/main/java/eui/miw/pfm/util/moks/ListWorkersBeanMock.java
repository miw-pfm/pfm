
package eui.miw.pfm.util.moks;
/*
@author Clemencio Morales
*/
import eui.miw.pfm.controllers.ejb.ListProjectsEjb;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.UserDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.LazyProjectDataModel;
import eui.miw.pfm.util.moks.WorkerEntityMock;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

@ManagedBean(name="listWorkersBeanMock")
public class ListWorkersBeanMock {

    private LazyDataModel<WorkerEntityMock> lazyModel;

    private WorkerEntityMock selectedWorker;

    private List<WorkerEntityMock> workers;

    public ListWorkersBeanMock() {
//        ListProjectsEjb eaE;
//        eaE = new ListProjectsEjb();
//        UserDAO userDAO = AbstractDAOFactory.getFactory().getUserDAO();
//        this.setProjects(eaE.obtainProjects(userDAO.read(1)));
//        lazyModel = new LazyProjectDataModel(this.projects);
    }

    public List<WorkerEntityMock> getWorkers() {
        return workers;
    }

    public void setWorkers(final List<WorkerEntityMock> workers) {
        this.workers = workers;
    }

    public LazyDataModel<WorkerEntityMock> getLazyModel() {
        return lazyModel;
    }

  public WorkerEntityMock getSelectedWorker() {
        return selectedWorker;
    }

    public void setSelectedWorker(WorkerEntityMock selectedWorker) {
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
