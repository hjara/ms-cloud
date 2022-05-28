package pe.code.migracion.seguridad.util.model;

import java.io.Serializable;

public class DominioGeneric implements Serializable {

	private static final long serialVersionUID = 1L;

	private int firstrow;
	private int maxrow;
	private long totalrecords;
	private String flagaccion;

	private boolean espaginable = false;
	private boolean esordenable = false;

	public String getFlagaccion() {
		return flagaccion;
	}

	public void setFlagaccion(String flagaccion) {
		this.flagaccion = flagaccion;
	}

	public boolean isEspaginable() {
		return espaginable;
	}

	public void setEspaginable(boolean espaginable) {
		this.espaginable = espaginable;
	}

	public boolean isEsordenable() {
		return esordenable;
	}

	public void setEsordenable(boolean esordenable) {
		this.esordenable = esordenable;
	}

	public int getFirstrow() {
		return firstrow;
	}

	public void setFirstrow(int firstrow) {
		this.firstrow = firstrow;
	}

	public int getMaxrow() {
		return maxrow;
	}

	public void setMaxrow(int maxrow) {
		this.maxrow = maxrow;
	}

	public long getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(long totalrecords) {
		this.totalrecords = totalrecords;
	}

}
