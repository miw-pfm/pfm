/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

/**
 *
 * @author mrrm & Roberto Amor
 */
import java.lang.reflect.Field;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

public class LazyGenericSorter<T> implements Comparator<T> {

    private String sortField;

    private SortOrder sortOrder;

    public LazyGenericSorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(T p1, T p2) {
        try {
            Field f = p1.getClass().getDeclaredField(this.sortField);
            f.setAccessible(true);
            Object value1 = f.get(p1);
            f = p2.getClass().getDeclaredField(this.sortField);
            f.setAccessible(true);
            Object value2 = f.get(p2);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
