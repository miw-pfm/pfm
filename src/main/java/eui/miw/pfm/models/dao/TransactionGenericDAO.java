package eui.miw.pfm.models.dao;

import java.util.List;

public interface TransactionGenericDAO<T, ID> extends GenericDAO<T, ID> {

    void begin();

    void commit();

    void rollback();

    List<T> find(String[] attributes, String[] values);

    List<T> find(String[] attributes, String[] values, String order, int index, int size);

    List<T> find(String psql, Object[] entities);
}
