package pe.code.migracion.seguridad.boot.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * @author jaraj
 *
 */
//@Profile(value = {"development", "production"})
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtCustomAuthenticationFilter customAuthenticationFilter = new JwtCustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl(JwtConstant.JWT_URI_LOGIN_SECURITY);
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(STATELESS);
		http.authorizeRequests().antMatchers(JwtConstant.JWT_URI_LOGIN_SECURITY_PERMISOALL).permitAll();
		http.authorizeRequests().antMatchers(JwtConstant.JWT_URI_TOKEN_REFRESH_PERMISOALL).permitAll();
		//http.authorizeRequests().antMatchers("/swagger-ui/**").hasAnyAuthority("ROLE_ADMIN");
		//http.authorizeRequests().antMatchers("/v3/api-docs").hasAnyAuthority("ROLE_ADMIN");
		//http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().anyRequest().permitAll();
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new JwtCustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
