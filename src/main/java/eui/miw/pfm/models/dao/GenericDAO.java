package eui.miw.pfm.models.dao;

import java.util.List;

public interface GenericDAO<T, ID> {

    public abstract void create(T entity);

    public abstract T read(ID code);

    public abstract void update(T entity);

    public abstract void delete(T entity);

    public abstract void deleteByID(ID code);

    public abstract List<T> findAll();

}
