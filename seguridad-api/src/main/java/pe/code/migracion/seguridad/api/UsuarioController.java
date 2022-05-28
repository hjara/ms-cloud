package pe.code.migracion.seguridad.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.code.migracion.seguridad.dominio.Usuario;
import pe.code.migracion.seguridad.dominio.dto.UsuarioDTO;
import pe.code.migracion.seguridad.service.UsuarioService;
import pe.code.migracion.seguridad.util.Utilitario;
import pe.code.migracion.seguridad.util.mapper.GenericoMapperMS;
 

@RestController
@CrossOrigin
@RequestMapping("/api/seguridad/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GenericoMapperMS mapper;
	
	/**
	 * Obtener todos los elementos
	 * 
	 * @return
	 */
	@GetMapping(value = "buscar")
	public ResponseEntity<UsuarioDTO> buscar() {
		Usuario objRest = new Usuario();
		objRest.setUsusuario("ADMIN");		
		Usuario busqueda = usuarioService.buscar(objRest);		 

		if(busqueda!= null) {
			UsuarioDTO dto =  mapper.converterDTOSegUsu(busqueda);
			return ResponseEntity.ok(dto);
		}
		else {
			return ResponseEntity.notFound().build();
		} 
	}
	
	/**
	 * Obtener todos los elementos
	 * 
	 * @return
	 */
	@GetMapping(value = "listar")
	public ResponseEntity<List<UsuarioDTO>> listar() {
		Usuario objRest = new Usuario();
//		objRest.setNombre("ADMIN COMERCIAL");	
		List<Usuario> busqueda = usuarioService.listar(objRest, false);		 
		
		if(Utilitario.esVacio(busqueda)) {
			return ResponseEntity.noContent().build();
		}
		else {
			List<UsuarioDTO> dto = mapper.converterDTOListSegUsu(busqueda);
			return ResponseEntity.ok(dto);
		}
	}

	@GetMapping(value = "listar/pag")
	public ResponseEntity<List<UsuarioDTO>> listarPag() {
		Usuario objRest = new Usuario();
//		objRest.setNombre("ADMIN COMERCIAL");
		objRest.setFirstrow(10);
		objRest.setMaxrow(2);
		
		List<Usuario> busqueda = usuarioService.listar(objRest, true);		 
		long contar = usuarioService.contar(objRest);
	
		if(Utilitario.esVacio(busqueda)) {
			return ResponseEntity.noContent().build();
		}
		else {
			List<UsuarioDTO> dto = mapper.converterDTOListSegUsu(busqueda);
			dto.forEach(c-> c.setTotalrecords(contar));
			return ResponseEntity.ok(dto);
		} 
	}

}
