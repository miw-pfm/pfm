package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.util.moks.ContextMocker;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestListProjectsBean {

    private ListProjectsBean listProjectsBean;

    @Before
    public void init() {
        FacesContext context = ContextMocker.mockFacesContext();
        try {
            Map<String, Object> session = new HashMap<>();
            ExternalContext ext = mock(ExternalContext.class);
            when(ext.getSessionMap()).thenReturn(session);
            when(context.getExternalContext()).thenReturn(ext);
            this.listProjectsBean = new ListProjectsBean();
        } finally {
            context.release();
        }        
    }

   @Test
   public void listProjects(){
       System.out.println(this.listProjectsBean.getProjects());
       assertNotNull(this.listProjectsBean.getProjects());
   }
}
