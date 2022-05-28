package pe.code.migracion.seguridad.dominio.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pe.code.migracion.seguridad.dominio.dao.RepositoryDao;
import pe.code.migracion.seguridad.util.model.DominioGeneric; 

public abstract class RepositoryDaoImpl<E extends DominioGeneric, I extends Serializable> implements RepositoryDao<E, I> {

	public <V extends DominioGeneric, T> T buscar(V filtro, Class<?> classe) {
		CriteriaBuilder criteriaBuilder = this.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = this.createCriteriaQuery(criteriaBuilder, classe);
		Root<?> root = criteriaQuery.from(classe);
		criteriaQuery.select(root);
		
		filtrarEntidad(filtro, criteriaQuery, criteriaBuilder, root);		 
		return (T) this.getSingleResult(filtro, criteriaQuery);
	}
	
	public <V extends DominioGeneric, T> List<T> listar(V filtro, Class<?> classe, boolean paginable) {
		CriteriaBuilder criteriaBuilder = this.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = this.createCriteriaQuery(criteriaBuilder, classe);
		Root<?> root = criteriaQuery.from(classe);
		criteriaQuery.select(root);
		
		filtrarEntidad(filtro, criteriaQuery, criteriaBuilder, root);		 
		return (List<T>) this.getResultList(filtro, paginable, criteriaQuery); 
	}
	
	public <V extends DominioGeneric, T> long contar(V filtro, Class<?> classe) {
		CriteriaBuilder criteriaBuilder = this.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = this.createCriteriaQuery(criteriaBuilder, Long.class);
		Root<?> root = criteriaQuery.from(classe);
		criteriaQuery.select(criteriaBuilder.count(root));
		
		filtrarEntidad(filtro, criteriaQuery, criteriaBuilder, root);		 
		return (long) this.getSingleResult(filtro, criteriaQuery);
	}
	
	
	public <V extends DominioGeneric> void filtrarEntidad(V entidad, CriteriaQuery criteriaQuery, CriteriaBuilder builder, Root root) {
		
	}
	
	
	
	
	
	/******************************/
	public <T> T getSingleResult(DominioGeneric filtro, CriteriaQuery<T> criteriaQuery) {
		filtro.setEspaginable(false);
		TypedQuery<T> type = this.createTypeQuery(filtro, criteriaQuery);
		try {
			return type.getSingleResult();
		} catch (NoResultException nre) {
			// Ok
		}
		return null;
	}
	
	public <T> List<T> getResultList(DominioGeneric filtro, boolean paginable, CriteriaQuery<T> criteriaQuery) {
		filtro.setEspaginable(paginable);
		TypedQuery<T> tq = this.createTypeQuery(filtro, criteriaQuery); 
		return tq.getResultList();
		
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return this.getCurrentSession().getCriteriaBuilder();
	}

	public <T> CriteriaQuery<T> createCriteriaQuery(CriteriaBuilder criteriaBuilder, Class<T> resultClass) {
		return criteriaBuilder.createQuery(resultClass);
	}

	public <T> TypedQuery<T> createTypeQuery(DominioGeneric entidad, CriteriaQuery<T> criteriaQuery) {
		if (entidad.isEspaginable()) {
			return this.getCurrentSession().createQuery(criteriaQuery).setMaxResults(entidad.getMaxrow())
					.setFirstResult(entidad.getFirstrow());
		} else {
			return this.getCurrentSession().createQuery(criteriaQuery);
		}
	}

	public <T> Expression<Boolean> builderCriteriaEqual(CriteriaQuery<?> query, CriteriaBuilder builder, Root<T> root,
			String column, Object object) {
		return builder.equal(root.get(column), object);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Expression<Boolean> builderCriteriaLike(CriteriaQuery<T> query, CriteriaBuilder builder, Root<T> root, String column, String object, Class<?> entityClazz) {
		EntityType type = getCurrentSession().getMetamodel().entity(entityClazz);
		return builder.like(builder.lower(root.get(type.getDeclaredSingularAttribute(column, String.class))), "%" + object + "%");
	}
 

	/*******************************************************************/
	private Class<E> entityClass;

	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";

	protected RepositoryDaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public E findById(I id) {
		return (E) getCurrentSession().get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(E e) {
		getCurrentSession().saveOrUpdate(e);
		// iniciarAuditoriaTrace(e,"SoU","");
	}

	@Override
	public void update(E e) {
		getCurrentSession().update(e);
		// iniciarAuditoriaTrace(e,"U","");
	}

	@Override
	public void save(E e) {
		getCurrentSession().save(e);
		// iniciarAuditoriaTrace(e,"S","");
	}

	@Override
	public void delete(E e) {
		getCurrentSession().delete(e);
		// iniciarAuditoriaTrace(e,"D","");
	}

	@Override
	public void flush(E e) {
		getCurrentSession().flush();
		getCurrentSession().clear();
	}

	@Override
	public void merge(E e) {
		getCurrentSession().merge(e);

	}
	

	/********* TRATAMIENTO AUDITOR *****/
	/**
	 * Construye dinamicamente un nuevo objetode Una clase determinada a partir del
	 * objeto Original
	 */
	public static Object getNuevoObjetoGenerico(Object objetoOrigen, Class<?> claseNuevoObjeto) {
		try {
			Object objNuevo = null;
			if (objetoOrigen != null && claseNuevoObjeto != null) {
				String type = claseNuevoObjeto.getName();
				objNuevo = Class.forName(type).getDeclaredConstructor().newInstance();
				//// obtener los valores del objetoOrigen y set a objNuevo
				Field[] fields = objetoOrigen.getClass().getDeclaredFields();
				for (Field field : fields) {
					try {
						if ((field.getModifiers()
								& java.lang.reflect.Modifier.FINAL) == java.lang.reflect.Modifier.FINAL) {
							// ES FINAL
						} else {
							String fieldName = field.getName();
							field.setAccessible(true);
							Object fieldValue = field.get(objetoOrigen);
							// Object fieldValue = field.get(objAntiguo);
							Field fieldNuevo = claseNuevoObjeto.getDeclaredField(fieldName);
							if (fieldNuevo != null) {
								fieldNuevo.setAccessible(true);
								fieldNuevo.set(objNuevo, fieldValue);
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
			}
			return objNuevo;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void iniciarAuditoriaTrace(Object objTrace, String typeTrace, String primKeyData) {
		try {
			if (objTrace != null) {
				// POR IMPLEMENTAR
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
