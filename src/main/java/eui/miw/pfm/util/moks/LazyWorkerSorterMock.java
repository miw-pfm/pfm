/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.util.moks;

import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.lang.reflect.Field;
import org.primefaces.model.SortOrder;

/**
 *
 * @author aw0591
 */
public class LazyWorkerSorterMock {

    private String sortField;

    private SortOrder sortOrder;

    public LazyWorkerSorterMock(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(WorkerEntity p1, WorkerEntity p2) {
        System.out.println(p1.toString());
        try {
            Field f = WorkerEntity.class.getDeclaredField(this.sortField);
            f.setAccessible(true);
            Object value1 = f.get(p1);
            System.out.println(f);
            f = WorkerEntity.class.getDeclaredField(this.sortField);
            f.setAccessible(true);
            Object value2 = f.get(p2);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }    
}
