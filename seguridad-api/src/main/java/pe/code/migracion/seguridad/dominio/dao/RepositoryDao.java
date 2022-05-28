package pe.code.migracion.seguridad.dominio.dao;

import java.io.Serializable;

import pe.code.migracion.seguridad.util.model.DominioGeneric;

public interface RepositoryDao<E extends DominioGeneric, I extends Serializable> {

	public E findById(I id);
    public void saveOrUpdate(E e);
    public void save(E e);
    public void update(E e);
    public void delete(E e);
    public void flush(E e);
    public void merge(E e);
  
}
