package br.com.apmp.ccompras.domain.exceptions;

public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = -1472878571855679579L;
	
	private String mensagem;

	public RepositoryException( String message ) {
		super( message );
		this.mensagem = message;
	}

	public RepositoryException( String message, Throwable cause ) {
		super( message, cause );
	}

	public String getMensagem() {
		return this.mensagem;
	}	

}
