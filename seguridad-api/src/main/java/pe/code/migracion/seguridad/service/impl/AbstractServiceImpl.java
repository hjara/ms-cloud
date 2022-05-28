package pe.code.migracion.seguridad.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

public abstract class AbstractServiceImpl {

	public void rollback() {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	public void exception(Throwable error) {
		error.printStackTrace();
		if (error instanceof Exception) {
//			Exception ex = (Exception) error;
			// Log.error(ex, "error controlado: ");
		}
	}

	public void rollback(Throwable error) {
		exception(error);
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	public <E> List<E> getNewList() {
		return new ArrayList<E>();
	}
}
