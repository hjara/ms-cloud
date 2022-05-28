package pe.code.migracion.seguridad.dominio;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * The primary key class for the SeguridadPerfilUsuario database table.
 * 
 */
public class RolUsuarioPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String rurol;
	private String ruusuario;

	public RolUsuarioPK() {
	}

	@Column(name="rurol")
	public String getRurol() {
		return rurol;
	}

	public void setRurol(String rurol) {
		this.rurol = rurol;
	}

	@Column(name="ruusuario")
	public String getRuusuario() {
		return ruusuario;
	}

	public void setRuusuario(String ruusuario) {
		this.ruusuario = ruusuario;
	}

}