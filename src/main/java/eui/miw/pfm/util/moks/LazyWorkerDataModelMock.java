/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.util.moks;

import eui.miw.pfm.models.entities.WorkerEntity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyWorkerDataModelMock extends LazyDataModel<WorkerEntity> {
    
    private List<WorkerEntity> datasource;
    
    public LazyWorkerDataModelMock(List<WorkerEntity> datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public WorkerEntity getRowData(String rowKey) {
        for(WorkerEntity worker : datasource) {
            if(worker.getName().equals(rowKey))
                return worker;
        }

        return null;
    }

    @Override
    public Object getRowKey(WorkerEntity worker) {
        return worker.getName();
    }

    @Override
    public List<WorkerEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<WorkerEntity> data = new ArrayList<WorkerEntity>();

        //filter
        for(WorkerEntity worker : datasource) {
            boolean match = true;

            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    System.out.println(filterProperty);
                    Field f = worker.getClass().getDeclaredField(filterProperty);
                    f.setAccessible(true);
                    String fieldValue = String.valueOf(f.get(worker));

                    if(filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    }
                    else {
                        match = false;
                        break;
                    }
                } catch(Exception e) {
                    match = false;
                } 
            }

            if(match) {
                data.add(worker);
            }
        }

        //sort
        if(sortField != null) {
            //Collections.sort(data, new LazyWorkerSorterMock(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
                    