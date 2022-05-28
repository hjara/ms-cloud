package pe.code.migracion.seguridad.dominio.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import pe.code.migracion.seguridad.dominio.Usuario;
import pe.code.migracion.seguridad.dominio.dao.UsuarioDao;
import pe.code.migracion.seguridad.util.Utilitario;
import pe.code.migracion.seguridad.util.model.DominioGeneric;
 

@Repository
public class UsuarioDaoImpl extends RepositoryDaoImpl<Usuario, String> implements UsuarioDao {

	protected UsuarioDaoImpl() {
		super(Usuario.class);
	}

	@Override
	public Usuario obtenerPorId(String primaryKey) {
		Object result = findById(primaryKey);
		return result != null ? (Usuario) result : null;
	}
	
	@Override
	public Usuario buscar(Usuario filtro) {
		Usuario result =  this.buscar(filtro, Usuario.class);
		return result; 
	}

	@Override
	public List<Usuario> listar(Usuario filtro, boolean paginable) {
		List<Usuario> result =  this.listar(filtro, Usuario.class, paginable);
		return result;
	}
	
	@Override
	public long contar(Usuario filtro) {
		long result = this.contar(filtro, Usuario.class);
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <V extends DominioGeneric> void filtrarEntidad(V entidad, CriteriaQuery criteriaQuery, CriteriaBuilder builder, Root root) {
		if (Utilitario.esInstancia(entidad, Usuario.class)) {
			Usuario filtro = (Usuario) entidad;
			if (filtro.getUstipo() != null && !"".equals(filtro.getUstipo())) {
				criteriaQuery.where(builderCriteriaEqual(criteriaQuery, builder, root, "ustipo", filtro.getUstipo()));
			}
			if (filtro.getUsestado() != null && !"".equals(filtro.getUsestado())) {
				criteriaQuery.where(builderCriteriaEqual(criteriaQuery, builder, root, "usestado", filtro.getUsestado()));
			}
			if (filtro.getUsnombre() != null && !"".equals(filtro.getUsnombre())) {
				criteriaQuery.where(builderCriteriaLike(criteriaQuery, builder, root, "usnombre", filtro.getUsnombre(), filtro.getClass()));
			}
			if (filtro.getUsusuario() != null && !"".equals(filtro.getUsusuario())) {
				criteriaQuery.where(builderCriteriaLike(criteriaQuery, builder, root, "ususuario", filtro.getUsusuario(), filtro.getClass()));
			}
		}
	}

	 

}
