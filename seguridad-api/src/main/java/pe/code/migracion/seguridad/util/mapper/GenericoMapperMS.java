package pe.code.migracion.seguridad.util.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import pe.code.migracion.seguridad.dominio.Usuario;
import pe.code.migracion.seguridad.dominio.dto.UsuarioDTO;

@Mapper(componentModel = "spring")
public interface GenericoMapperMS {

	UsuarioDTO converterDTOSegUsu(Usuario entidad);
	Usuario converterEntidadSegUsu(UsuarioDTO entidad);
	
	List<UsuarioDTO> converterDTOListSegUsu(List<Usuario> lista);
	List<Usuario> converterEntidadListSegUsu(List<UsuarioDTO> lista);
}