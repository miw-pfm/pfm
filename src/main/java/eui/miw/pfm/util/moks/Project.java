/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.util.moks;

/**
 *
 * @author ro
 */
public class Project {
    private String name;
    private String description;
    private int iter;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getIter() {
        return iter;
    }

    public void setIter(final int iter) {
        this.iter = iter;
    }

    @Override
    public String toString() {
        return "Project{" + "name=" + name + ", description=" + description + ", iter=" + iter + '}';
    }
    
    
}
