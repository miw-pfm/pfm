/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

import eui.miw.pfm.models.entities.WorkerEntity;
import eui.miw.pfm.util.moks.LazyWorkerDataModelMock;
import eui.miw.pfm.util.moks.LazyWorkerSorterMock;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real
 * datasource like a database.
 */
public class LazyWorkerDataModel extends LazyDataModel<WorkerEntity> {

    private List<WorkerEntity> datasource;

    public LazyWorkerDataModel(List<WorkerEntity> datasource) {
        this.datasource = datasource;
    }

    @Override
    public WorkerEntity getRowData(String rowKey) {
        for (WorkerEntity project : datasource) {
            if (project.getName().equals(rowKey)) {
                return project;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(WorkerEntity project) {
        return project.getName();
    }

    @Override
    public List<WorkerEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<WorkerEntity> data = new ArrayList<WorkerEntity>();

        //filter
        for (WorkerEntity project : datasource) {
            boolean match = true;

            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    Field f = project.getClass().getDeclaredField(filterProperty);
                    f.setAccessible(true);
                    String fieldValue = String.valueOf(f.get(project));

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
                data.add(project);
            }
        }

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazyGenericSorter<WorkerEntity>(sortField, sortOrder));
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
