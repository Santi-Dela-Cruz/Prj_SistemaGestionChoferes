package dataAccesComponent;

import java.util.List;

public interface IDAO<T> {
    public boolean create(T entity) throws Exception; // CRUD

    public List<T> readAll() throws Exception; // CRUD

    public boolean update(T entity) throws Exception; // CRUD

    public boolean delete(Integer id) throws Exception; // CRUD

    public T readBy(Integer id) throws Exception; // Adicional
}
