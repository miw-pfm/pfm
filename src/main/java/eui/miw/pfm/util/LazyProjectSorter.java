/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

/**
 *
 * @author bk0805
 */
import eui.miw.pfm.models.entities.ProjectEntity;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

public class LazyProjectSorter implements Comparator<ProjectEntity> {

    private String sortField;

    private SortOrder sortOrder;

    public LazyProjectSorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(ProjectEntity p1, ProjectEntity p2) {
        System.out.println(p1.toString());
        try {
            Object value1 = ProjectEntity.class.getField(this.sortField).get(p1);
            //ProjectEntity.class.getField(this.sortField).
            System.out.println(value1);
            Object value2 = ProjectEntity.class.getField(this.sortField).get(p2);
            System.out.println(value2);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }
}
