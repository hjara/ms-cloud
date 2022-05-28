package pe.code.migracion.seguridad.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class Utilitario {

	public static <T, E> boolean esInstancia(E instancia, Class<T> clazz) {
		if (instancia == null) {
			return false;
		} else {
			if (clazz.isInstance(instancia)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static String getDateFormat(String pattern, Date valor) {
		if (valor != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(valor);
		} else {
			return null;
		}
	}
	
	public static Date getDateFormat(String pattern, String valor) {
		try {
			if (valor != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.parse(valor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <E> boolean esVacio(List<E> array) {		
		if (array == null || array.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static <E> boolean noEsVacio(List<E> array) {
		return !esVacio(array);
	}
	
	public static boolean esVacio(Date date) {
		if (date == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean esVacio(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean noEsVacio(String str) {
		return !esVacio(str);
	}

	public static boolean noEsVacio(Date date) {
		return !esVacio(date);
	}

	/**
	 * Permite corregir en un campo auxiliar de fecha-cadena (Si existiera) el vslor
	 * fecha
	 * 
	 */
	public static <J> void fixDateFormat(J json, Object valor, Consumer<? super J> action) {
		if (valor == null) {
			action.accept(json);
		}
	}

	public static Date getFecha(String fechaStr) {
		if (fechaStr != null) {
			return Utilitario.getDateFormat(Constant.PATTER_DATE_TIME_DEFAULT, fechaStr);
		}
		return null;
	}

	public static String getFechaFormat(Date fecha) {
		return Utilitario.getDateFormat(Constant.PATTER_DATE_TIME_DEFAULT, fecha);
	}
	
}
