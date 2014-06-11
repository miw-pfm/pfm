/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm;

import eui.miw.pfm.controllers.ejb.TestCalendarProject;
import eui.miw.pfm.controllers.ejb.TestCreateProject;
import eui.miw.pfm.controllers.ejb.TestIteration;
import eui.miw.pfm.controllers.ejb.TestListCaseUse;
import eui.miw.pfm.controllers.ejb.TestListProjectWorkersEjb;
import eui.miw.pfm.controllers.ejb.TestLogin;
import eui.miw.pfm.controllers.ejb.TestNameProjectValidator;
import eui.miw.pfm.controllers.ejb.TestRisk;
import eui.miw.pfm.controllers.ejb.TestUseCase;
import eui.miw.pfm.controllers.ejb.TestWorker;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Fred Peña
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestWorker.class, TestCalendarProject.class, TestCreateProject.class,
    TestListCaseUse.class, TestListProjectWorkersEjb.class, TestLogin.class,
    TestNameProjectValidator.class, TestRisk.class, TestUseCase.class, TestIteration.class})
public class AllTest {

}
