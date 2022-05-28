package pe.code.migracion.seguridad.boot.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.code.migracion.seguridad.dominio.Usuario;
import pe.code.migracion.seguridad.service.UsuarioService; 

/**
 * 
 * @author jaraj
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Usuario userData = repo.obtenerUsuarioConRoles(username);
		if (userData == null) {
			throw new UsernameNotFoundException(JwtConstant.MSJ_USER_NOT_FOUND_0001);
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		if (userData.getRolesAux().isEmpty()) {
			userData.getRolesAux().forEach(role -> {
				String perfil = role.getId().getRurol();
				roles.add(new SimpleGrantedAuthority(perfil));
			});
		}

		String usuario = userData.getUsusuario();

		String claveBD = userData.getUsclave();
		String clave = passwordEncoder.encode(claveBD);
		boolean enabled = true;

		return new User(usuario, clave, enabled, true, true, true, roles);
	}

}
