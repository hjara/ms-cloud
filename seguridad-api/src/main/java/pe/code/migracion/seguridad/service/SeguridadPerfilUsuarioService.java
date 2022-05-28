package pe.code.migracion.seguridad.service;

import java.util.List;

import pe.code.migracion.seguridad.dominio.RolUsuario;

public interface SeguridadPerfilUsuarioService {

	public List<RolUsuario> listar(RolUsuario objDao, boolean paginable); 
	
}
