package pe.code.migracion.seguridad.dominio.dto;

import java.util.Date;

import pe.code.migracion.seguridad.util.Utilitario;
import pe.code.migracion.seguridad.util.model.DominioGeneric;

public class UsuarioDTO extends DominioGeneric {

	/**
	 * 
	 */
	private static final long serialVersionUID = 784807356316702700L;
	private String ususuario;
	private String ustipo;
	private String usnombre;
	private String usclave;
	private String usexpiraclaveflag;
	private Date usexpirafecha;
	private String usestado;
	
	private String usexpirafechastr;

	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsusuario() {
		return ususuario;
	}

	public void setUsusuario(String ususuario) {
		this.ususuario = ususuario;
	}

	public String getUstipo() {
		return ustipo;
	}

	public void setUstipo(String ustipo) {
		this.ustipo = ustipo;
	}

	public String getUsnombre() {
		return usnombre;
	}

	public void setUsnombre(String usnombre) {
		this.usnombre = usnombre;
	}

	public String getUsclave() {
		return usclave;
	}

	public void setUsclave(String usclave) {
		this.usclave = usclave;
	}

	public String getUsexpiraclaveflag() {
		return usexpiraclaveflag;
	}

	public void setUsexpiraclaveflag(String usexpiraclaveflag) {
		this.usexpiraclaveflag = usexpiraclaveflag;
	}

	public Date getUsexpirafecha() {
		Utilitario.fixDateFormat(this, usexpirafecha,
				m -> m.setUsexpirafecha(Utilitario.getFecha(usexpirafechastr)));
		return usexpirafecha;
	}

	public void setUsexpirafecha(Date usexpirafecha) {
		this.usexpirafecha = usexpirafecha;
	}

	public String getUsestado() {
		return usestado;
	}

	public void setUsestado(String usestado) {
		this.usestado = usestado;
	}

	public String getUsexpirafechastr() {
		Utilitario.fixDateFormat(this, usexpirafechastr,
				m -> m.setUsexpirafechastr(Utilitario.getFechaFormat(usexpirafecha)));
		return usexpirafechastr;
	}

	public void setUsexpirafechastr(String usexpirafechastr) {
		this.usexpirafechastr = usexpirafechastr;
	}

	 

 

}
