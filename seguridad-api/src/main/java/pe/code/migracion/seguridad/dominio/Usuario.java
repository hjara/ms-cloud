package pe.code.migracion.seguridad.dominio;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import pe.code.migracion.seguridad.util.model.DominioGeneric;

@Entity
@Table(name = "usuario")
public class Usuario extends DominioGeneric {

	private String ususuario;
	private String ustipo;
	private String usnombre;
	private String usclave;
	private String usexpiraclaveflag;
	private Date usexpirafecha;
	private String usestado;
	private String ususuariocreacion;
	private Date usfechacreacion;
	private String ususuariomodificacion;
	private Date usfechamodificacion;

	private Collection<RolUsuario> rolesAux;

	@Id
	@Column(name = "ususuario")
	public String getUsusuario() {
		return ususuario;
	}

	public void setUsusuario(String ususuario) {
		this.ususuario = ususuario;
	}

	@Column(name = "ustipo")
	public String getUstipo() {
		return ustipo;
	}

	public void setUstipo(String ustipo) {
		this.ustipo = ustipo;
	}

	@Column(name = "usnombre")
	public String getUsnombre() {
		return usnombre;
	}

	public void setUsnombre(String usnombre) {
		this.usnombre = usnombre;
	}

	@Column(name = "usclave")
	public String getUsclave() {
		return usclave;
	}

	public void setUsclave(String usclave) {
		this.usclave = usclave;
	}

	@Column(name = "usexpiraclaveflag")
	public String getUsexpiraclaveflag() {
		return usexpiraclaveflag;
	}

	public void setUsexpiraclaveflag(String usexpiraclaveflag) {
		this.usexpiraclaveflag = usexpiraclaveflag;
	}

	@Column(name = "usexpirafecha")
	public Date getUsexpirafecha() {
		return usexpirafecha;
	}

	public void setUsexpirafecha(Date usexpirafecha) {
		this.usexpirafecha = usexpirafecha;
	}

	@Column(name = "usestado")
	public String getUsestado() {
		return usestado;
	}

	public void setUsestado(String usestado) {
		this.usestado = usestado;
	}

	@Column(name = "ususuariocreacion")
	public String getUsusuariocreacion() {
		return ususuariocreacion;
	}

	public void setUsusuariocreacion(String ususuariocreacion) {
		this.ususuariocreacion = ususuariocreacion;
	}

	@Column(name = "usfechacreacion")
	public Date getUsfechacreacion() {
		return usfechacreacion;
	}

	public void setUsfechacreacion(Date usfechacreacion) {
		this.usfechacreacion = usfechacreacion;
	}

	@Column(name = "ususuariomodificacion")
	public String getUsusuariomodificacion() {
		return ususuariomodificacion;
	}

	public void setUsusuariomodificacion(String ususuariomodificacion) {
		this.ususuariomodificacion = ususuariomodificacion;
	}

	@Column(name = "usfechamodificacion")
	public Date getUsfechamodificacion() {
		return usfechamodificacion;
	}

	public void setUsfechamodificacion(Date usfechamodificacion) {
		this.usfechamodificacion = usfechamodificacion;
	}

	@Transient
	public Collection<RolUsuario> getRolesAux() {
		return rolesAux;
	}

	public void setRolesAux(Collection<RolUsuario> rolesAux) {
		this.rolesAux = rolesAux;
	}

}
