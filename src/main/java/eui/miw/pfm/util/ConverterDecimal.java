/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fred Peña
 */
public final class ConverterDecimal {

    private static final Logger LOGGER = java.util.logging.Logger.getLogger(ConverterDecimal.class.getName());
    private static final String ONE_DECIMAL = "#,###.0";

    public static double roundOneDecimals(final double value) {
        return Double.parseDouble(new DecimalFormat(ONE_DECIMAL).format(((double) value)).replace(",", "."));
    }

    public static double roundOneDecimals(final String value) {

        final DecimalFormat oneDigit = new DecimalFormat("#,###.0");
        try {
            return (double) oneDigit.parse(oneDigit.format(Double.parseDouble(value.replace(",", "."))));//NOPMD
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }
        return 0.0;
    }
}
