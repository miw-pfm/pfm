/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.models.entities;

import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author HWilliamRS
 */
public class ProjectTest {

    private ProjectEntity project ;
    /*
     Validate dates : 
     Initial Date(Greater or Equal to the actual date) 
     Ending Date(Lower than the begining date)
     */

    @Before
    public void init() {
        this.project = new ProjectEntity();
    }

    @Test
    public void testProjectDates() {
        
        Date[][] dates = new Date[3][2];
        
        dates[0][0] = new Date(System.currentTimeMillis());
        dates[0][1] = new Date(System.currentTimeMillis() + (1 * 30 * 24 * 60 * 1000 ) );
        
        dates[1][0] = new Date(System.currentTimeMillis());
        dates[1][1] = new Date(System.currentTimeMillis() + (1 * 30 * 24 * 60 * 1000 ) );
        
        dates[2][0] = new Date(System.currentTimeMillis());
        dates[2][1] = new Date(System.currentTimeMillis() + (1 * 30 * 24 * 60 * 1000 ) );
        
        
        for(int i = 0;i < dates.length ; i++){
           project.setStartDate(dates[i][0]);
           project.setEndDate(dates[i][1]);
           testProjectEndDate(); 
           testProjectStartDate(new Date(System.currentTimeMillis())); 
        }
    }

    public void testProjectStartDate(Date actual) {
        
        assertTrue("Start Date",project.getStartDate().after(actual) || project.getStartDate().equals(actual) );
        
    }

    public void testProjectEndDate() {
        
        assertTrue("End Date", project.getStartDate().before(project.getEndDate()) || project.getEndDate().equals(project.getStartDate())  );
        
    }

}
