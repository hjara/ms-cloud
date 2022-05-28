package pe.code.migracion.seguridad.dominio.dao;
 
import java.util.List;

import pe.code.migracion.seguridad.dominio.Usuario;
  
public interface UsuarioDao {
 
	public Usuario obtenerPorId(String primaryKey);
	public List<Usuario> listar(Usuario filtro, boolean paginable);
	public long contar(Usuario filtro);
	public Usuario buscar(Usuario filtro);
	 
}
