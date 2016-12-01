package br.com.apmp.ccompras.service.exceptions;

public class ServiceException extends RuntimeException {


	private static final long serialVersionUID = 4963689215700011639L;

	public ServiceException( String message ) {
		super( message );
	}

}
