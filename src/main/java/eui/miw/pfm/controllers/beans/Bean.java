/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

/**
 *
 * @author Roberto Amor
 */
public class Bean {
    private String errors;

    public String getErrors() {
        return errors;
    }
    
    public void setErrors(final String errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return !this.errors.equals("");
    }

    public void addFieldError(final String fieldError) {
        if(this.hasErrors()){
            this.errors += "; " + fieldError;
        } else {
            this.errors = fieldError;
            
        }
    }
}
