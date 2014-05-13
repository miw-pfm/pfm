/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

/**
 *
 * @author Fred Peña
 */
public class ExceptionCatch {

    private static ExceptionCatch instace;
    private boolean exception;

    private ExceptionCatch() {
        super();
    }

    public static ExceptionCatch getInstance() {
        if (ExceptionCatch.instace == null) {
            ExceptionCatch.instace = new ExceptionCatch();
        }
        return ExceptionCatch.instace;
    }

    public boolean isException() {
        return exception;
    }

    public void setException(final boolean exception) {
        this.exception = exception;
    }

}
