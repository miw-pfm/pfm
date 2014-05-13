/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

import eui.miw.pfm.models.entities.RiskEntity;
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
public class LazyRiskDataModel extends LazyDataModel<RiskEntity> {

    private final List<RiskEntity> datasource;

    public LazyRiskDataModel(final List<RiskEntity> datasource) {
        super();
        this.datasource = datasource;
    }

    @Override
    public RiskEntity getRowData(final String rowKey) {
        for (RiskEntity object : datasource) {
            if (object.getName().equals(rowKey)) {
                return object;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(final RiskEntity object) {
        return object.getId();
    }

    @Override
    public List<RiskEntity> load(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final Map<String, String> filters) {
        final List<RiskEntity> data = new ArrayList<>();

        for (RiskEntity object : datasource) {
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
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                    match = false;
                }
            }

            if (match) {
                data.add(object);
            }
        }

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazyGenericSorter<RiskEntity>(sortField, sortOrder));
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
