package br.com.apmp.ccompras.service.exceptions;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 4963689215700011639L;

	private String mensagem;

	public ServiceException( String message ) {
		super( message );
		this.mensagem = message;
	}

	public ServiceException( String message, Throwable cause ) {
		super( message, cause );
	}

	public String getMensagem() {
		return this.mensagem;
	}

}
