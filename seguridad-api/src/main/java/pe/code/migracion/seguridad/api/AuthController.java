package pe.code.migracion.seguridad.api;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.code.migracion.seguridad.boot.security.JwtConstant;
import pe.code.migracion.seguridad.dominio.Usuario;
import pe.code.migracion.seguridad.service.UsuarioService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private UsuarioService usuarioService;
   
    @GetMapping("/token/refresh")
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);	
    	if (authorizationHeader != null && authorizationHeader.startsWith(JwtConstant.JWT_BEARER_SPACE)) {
			try {
				String refresh_token = authorizationHeader.substring(JwtConstant.JWT_BEARER_SPACE.length());
				Algorithm algorithm = Algorithm.HMAC256(JwtConstant.JWT_SECRET.getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				Usuario user = usuarioService.obtenerUsuarioConRoles(username);
								 
				String access_token = JWT.create().withSubject(user.getUsusuario())
						.withExpiresAt(new Date(System.currentTimeMillis() + JwtConstant.JWT_TOKEN_TIME_TOKEN))
						.withIssuer(request.getRequestURI().toString())
						.withClaim("roles", user.getRolesAux().stream().map(p-> p.getId().getRurol()).collect(Collectors.toList()))
						.sign(algorithm);
 
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
				 				
			} 
			catch (Exception exception) {  
				response.setHeader("error", exception.getMessage());
				response.setStatus(FORBIDDEN.value()); 				
				Map<String, String> error = new HashMap<>();
				error.put("error_message", exception.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);					
			}
		}
		else {
			throw new RuntimeException(JwtConstant.MSJ_REFRESH_TOKEN_0001);
		}
    	 
    }
    
   

}
