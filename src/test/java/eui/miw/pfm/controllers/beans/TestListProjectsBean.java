package eui.miw.pfm.controllers.beans;

import static org.junit.Assert.assertNotNull;
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
