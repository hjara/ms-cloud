package pe.code.migracion.seguridad.boot.security;

/**
 * 
 * @author jaraj
 *
 */
public class JwtConstant {

	public static final String JWT_BEARER_SPACE = "Bearer ";

	public static final String JWT_SECRET = "adminFS";
  
	public static final long JWT_TOKEN_TIME_TOKEN = (10 * 60 * 1000); 
	public static final long JWT_TOKEN_TIME_REFRESH_TOKEN = (10 * 60 * 1000);
	

	// URI
	public static final String JWT_URI_LOGIN_SECURITY = "/api/login";
	
	// Permiso de URI
	public static final String JWT_URI_LOGIN_SECURITY_PERMISOALL = "/api/login/**";
	public static final String JWT_URI_TOKEN_REFRESH_PERMISOALL = "/api/token/refresh/**";
	
	// MENSAJE
	public static final String MSJ_USER_NOT_FOUND_0001 = "User not found";
	public static final String MSJ_REFRESH_TOKEN_0001 = "Refresh token is missing";
}
