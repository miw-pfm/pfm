package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.Date;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestListProjectsBean {

    private ListProjectsBean listProjectsBean;

    @Before
    public void init() {
        this.listProjectsBean = new ListProjectsBean();
    }

   @Test
   public void listProjects(){
       System.out.println(this.listProjectsBean.getProjects());
       assertNotNull(this.listProjectsBean.getProjects());
   }
}
