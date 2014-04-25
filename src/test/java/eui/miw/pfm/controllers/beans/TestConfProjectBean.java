package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.Date;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestConfProjectBean {

    private ConfProjectBean confProjectBean;
    private ProjectEntity project;

    @Before
    public void init() {
        this.confProjectBean = new ConfProjectBean(); 
        this.project = new ProjectEntity();
    }

    @Test
    public void testValidDates() {

        Date[][] dates = new Date[3][2];

        dates[0][0] = new Date(System.currentTimeMillis());
        dates[0][1] = new Date(System.currentTimeMillis() + (1 * 30 * 24 * 60 * 1000));

        dates[1][0] = new Date(System.currentTimeMillis());
        dates[1][1] = new Date(System.currentTimeMillis() + (1 * 30 * 24 * 60 * 1000));

        dates[2][0] = new Date(System.currentTimeMillis());
        dates[2][1] = new Date(System.currentTimeMillis() + (1 * 30 * 24 * 60 * 1000));

        for (int i = 0; i < dates.length; i++) {

            validDates(dates[i][0], dates[i][1]);

        }

    }

    public void validDates(Date start, Date end) {

        assertTrue("Valid Dates", confProjectBean.validDates(start, end));

    }
    
     @Test
    public void testStimateIter() {

//        this.project.setStartDate(new Date("9/2/13"));
//        this.project.setEndDate(new Date("12/27/14"));
//        this.project.setWeekNumIteration(3);
//        confProjectBean.setProject(project);
//        
//        confProjectBean.stimateIter();
//        assertSame("Correct", this.project.getEstimatedNumIteration() ,22.9 );
    }

}
