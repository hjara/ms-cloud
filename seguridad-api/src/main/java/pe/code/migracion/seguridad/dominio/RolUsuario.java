package pe.code.migracion.seguridad.dominio;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import pe.code.migracion.seguridad.util.model.DominioGeneric;

@Entity
@Table(name = "rolusuario")
public class RolUsuario extends DominioGeneric{
	
	private RolUsuarioPK id;
	private String ruestado;
	private String ruruuariocreacion;
	private Date rufechacreacion;
	private String ruruuariomodificacion;
	private Date rufechamodificacion;
	
	public RolUsuario() {
		id = new RolUsuarioPK();
	}
	
	@EmbeddedId
	public RolUsuarioPK getId() {
		return id;
	}
	public void setId(RolUsuarioPK id) {
		this.id = id;
	}
	public String getRuestado() {
		return ruestado;
	}
	public void setRuestado(String ruestado) {
		this.ruestado = ruestado;
	}
	public String getRuruuariocreacion() {
		return ruruuariocreacion;
	}
	public void setRuruuariocreacion(String ruruuariocreacion) {
		this.ruruuariocreacion = ruruuariocreacion;
	}
	public Date getRufechacreacion() {
		return rufechacreacion;
	}
	public void setRufechacreacion(Date rufechacreacion) {
		this.rufechacreacion = rufechacreacion;
	}
	public String getRuruuariomodificacion() {
		return ruruuariomodificacion;
	}
	public void setRuruuariomodificacion(String ruruuariomodificacion) {
		this.ruruuariomodificacion = ruruuariomodificacion;
	}
	public Date getRufechamodificacion() {
		return rufechamodificacion;
	}
	public void setRufechamodificacion(Date rufechamodificacion) {
		this.rufechamodificacion = rufechamodificacion;
	}
	 
	

}