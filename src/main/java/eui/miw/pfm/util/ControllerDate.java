/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fred Peña
 */
public final class ControllerDate {

    private static final Logger LOGGER = Logger.getLogger(ControllerDate.class.getName());

    private ControllerDate() {
    }

    /**
     * Encuentra la diferencia de dos fecha establecida
     *
     * @param startingDate La fecha de inicio
     * @param endDate La fecha de fin
     * @return Retorna el numero de dias entre dos fechas
     */
    public static double getDifferenceDate(final Date startingDate, final Date endDate) {
        Date starting = null;//NOPMD
        Date end = null;//NOPMD
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        try {
            starting = dateFormat.parse(dateFormat.format(startingDate));
            end = dateFormat.parse(dateFormat.format(endDate));
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return (double) Math.floor((end.getTime() - starting.getTime()) / (1000 * 60 * 60 * 24));
    }
}
