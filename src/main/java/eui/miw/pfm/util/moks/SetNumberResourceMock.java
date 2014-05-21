/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util.moks;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author bk0804
 */
@RequestScoped
@Named
public class SetNumberResourceMock {

    private String worker;

    public String getWorker() {
        return worker;
    }

    public void setWorker(final String worker) {
        this.worker = worker;
    }

    public void save() {
    }

    public void edit() {
    }

}
