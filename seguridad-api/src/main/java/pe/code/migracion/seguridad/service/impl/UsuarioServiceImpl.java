package pe.code.migracion.seguridad.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.code.migracion.seguridad.dominio.RolUsuario;
import pe.code.migracion.seguridad.dominio.Usuario;
import pe.code.migracion.seguridad.dominio.dao.UsuarioDao;
import pe.code.migracion.seguridad.service.SeguridadPerfilUsuarioService;
import pe.code.migracion.seguridad.service.UsuarioService;
import pe.code.migracion.seguridad.util.Constant;
 
@Service("usuarioService")
@Transactional(readOnly = true)
public class UsuarioServiceImpl extends AbstractServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private SeguridadPerfilUsuarioService seguridadPerfilUsuarioService;
	
	@Override
	public Usuario obtenerPorId(String primaryKey) {		
		try {
			return usuarioDao.obtenerPorId(primaryKey);
		} catch (Exception e) {
			e.printStackTrace();
			//Log.logger.error(Log.getStackTrace(e));			
		}
		return null;	
	}	
	
	@Override
	public Usuario obtenerUsuarioConRoles(String usuario) {		
		try {
			List<RolUsuario> roles = new ArrayList<>();
			Usuario busqusuario = usuarioDao.obtenerPorId(usuario);
			 
			if(busqusuario != null) {
				RolUsuario filtroSPU = new RolUsuario();
				filtroSPU.getId().setRuusuario(usuario);
				filtroSPU.setRuestado(Constant.ACTIVO);
				roles = seguridadPerfilUsuarioService.listar(filtroSPU, false);
			}
	 
			busqusuario.setRolesAux(roles);
			return busqusuario;
			
		} catch (Exception e) {
			e.printStackTrace();
//			Log.logger.error(Log.getStackTrace(e));			
		}
		return null;	
	}
	

	@Override
	public List<Usuario> listar(Usuario filtro, boolean paginable) {
		try {
			return usuarioDao.listar(filtro, paginable);
		} catch (Exception e) {
			e.printStackTrace();
//			Log.logger.error(Log.getStackTrace(e));			
		}
		return new ArrayList<Usuario>();	
	}	
	
	@Override
	public long contar(Usuario filtro) {		
		try {
			return usuarioDao.contar(filtro);
		} catch (Exception e) {
			e.printStackTrace();
//			Log.logger.error(Log.getStackTrace(e));			
		}
		return 0;	
	}
	 
	@Override
	public Usuario buscar(Usuario filtro) {
		try {
			return usuarioDao.buscar(filtro);
		} catch (Exception e) {
			e.printStackTrace();
//			Log.logger.error(Log.getStackTrace(e));			
		}
		return null;	
	}	
}
