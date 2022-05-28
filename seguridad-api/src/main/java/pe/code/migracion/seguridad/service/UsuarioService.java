package pe.code.migracion.seguridad.service;

import java.util.List;

import pe.code.migracion.seguridad.dominio.Usuario;

public interface UsuarioService {

	public Usuario obtenerPorId(String primaryKey); 
	
	public List<Usuario> listar(Usuario filtro, boolean paginable);
	public long  contar(Usuario filtro);
	public Usuario buscar(Usuario filtro);
	public Usuario obtenerUsuarioConRoles(String usuario);

}
