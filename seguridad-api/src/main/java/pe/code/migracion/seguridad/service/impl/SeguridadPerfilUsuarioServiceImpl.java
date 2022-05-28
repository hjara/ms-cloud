package pe.code.migracion.seguridad.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.code.migracion.seguridad.dominio.RolUsuario;
import pe.code.migracion.seguridad.dominio.dao.SeguridadPerfilUsuarioDao;
import pe.code.migracion.seguridad.service.SeguridadPerfilUsuarioService;

@Service("seguridadPerfilUsuarioService")
@Transactional(readOnly = true)
public class SeguridadPerfilUsuarioServiceImpl implements SeguridadPerfilUsuarioService{

	@Autowired
	private SeguridadPerfilUsuarioDao seguridadPerfilUsuarioDao;
	
	@Override
	public List<RolUsuario> listar(RolUsuario filtro, boolean paginable) {
		try {
			return seguridadPerfilUsuarioDao.listar(filtro, paginable);
		} catch (Exception e) {
			e.printStackTrace();
//			Log.logger.error(Log.getStackTrace(e));			
		}
		return new ArrayList<RolUsuario>();
	}

}
