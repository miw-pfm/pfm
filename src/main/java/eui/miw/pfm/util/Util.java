/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.util;

/**
 *
 * @author bg0155
 */
public class Util {
    private int id;

    @Override
    public String toString() {
        return "Util{" + "id=" + id + '}';
    }
    
    public int doblar(){
        return this.id * 2;
    }
}
