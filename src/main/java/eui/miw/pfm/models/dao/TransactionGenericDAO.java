package eui.miw.pfm.models.dao;

import java.util.List;

public interface TransactionGenericDAO<T, ID> extends GenericDAO<T, ID> {

    public abstract void begin();

    public abstract void commit();

    public abstract void rollback();

    public abstract List<T> find(String[] attributes, String[] values);

    public abstract List<T> find(String psql, Object entity);

    public abstract List<T> find(String[] attributes, String[] values, String order, int index, int size);
}
