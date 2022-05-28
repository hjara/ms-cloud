package pe.code.migracion.seguridad.dominio.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import pe.code.migracion.seguridad.dominio.RolUsuario;
import pe.code.migracion.seguridad.dominio.RolUsuarioPK;
import pe.code.migracion.seguridad.dominio.dao.SeguridadPerfilUsuarioDao;
import pe.code.migracion.seguridad.util.Utilitario;
import pe.code.migracion.seguridad.util.model.DominioGeneric;

@Repository
public class SeguridadPerfilUsuarioDaoImpl extends RepositoryDaoImpl<RolUsuario, RolUsuarioPK>
		implements SeguridadPerfilUsuarioDao {

	protected SeguridadPerfilUsuarioDaoImpl() {
		super(RolUsuario.class);
	}

	@Override
	public List<RolUsuario> listar(RolUsuario filtro, boolean paginable) {
		List<RolUsuario> result = this.listar(filtro, RolUsuario.class, paginable);
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <V extends DominioGeneric> void filtrarEntidad(V entidad, CriteriaQuery criteriaQuery,
			CriteriaBuilder builder, Root root) {
		if (Utilitario.esInstancia(entidad, RolUsuario.class)) {
			RolUsuario filtro = (RolUsuario) entidad;

			if (filtro.getId() != null) {
				if (filtro.getId().getRurol() != null && !"".equals(filtro.getId().getRurol())) {
					criteriaQuery.where(
							builderCriteriaEqual(criteriaQuery, builder, root, "id.rurol", filtro.getId().getRurol()));
				}
				if (filtro.getId().getRuusuario() != null && !"".equals(filtro.getId().getRuusuario())) {
					criteriaQuery.where(builderCriteriaEqual(criteriaQuery, builder, root, "id.ruusuario",
							filtro.getId().getRuusuario()));
				}
			}

			if (filtro.getRuestado() != null && !"".equals(filtro.getRuestado())) {
				criteriaQuery
						.where(builderCriteriaEqual(criteriaQuery, builder, root, "ruestado", filtro.getRuestado()));
			}

		}
	}

}
