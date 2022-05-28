package pe.code.migracion.seguridad.dominio.dao;

import java.util.List;

import pe.code.migracion.seguridad.dominio.RolUsuario;


public interface SeguridadPerfilUsuarioDao {

	public List<RolUsuario> listar(RolUsuario objDao, boolean paginable); 			

}
