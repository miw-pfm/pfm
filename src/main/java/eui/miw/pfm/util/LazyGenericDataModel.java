/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Fred Peña
 *
 */
public class LazyGenericDataModel<T> extends LazyDataModel<T> {

    private List<T> datasource;

    public LazyGenericDataModel(List<T> datasource) {
        this.datasource = datasource;
    }

//    @Override
//    public T getRowData(String rowKey) {
//        for (T object : datasource) {
//            if (object.equals(rowKey)) {
//                return object;
//            }
//        }
//        
//        return null;
//    }
    @Override
    public Object getRowKey(T object) {
        return object;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<T> data = new ArrayList<T>();

        for (T object : datasource) {
            boolean match = true;

            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    Field f = object.getClass().getDeclaredField(filterProperty);
                    f.setAccessible(true);
                    String fieldValue = String.valueOf(f.get(object));

                    if (filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    } else {
                        match = false;
                        break;
                    }
                } catch (Exception e) {
                    match = false;
                }
            }

            if (match) {
                data.add(object);
            }
        }

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazyGenericSorter<T>(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
