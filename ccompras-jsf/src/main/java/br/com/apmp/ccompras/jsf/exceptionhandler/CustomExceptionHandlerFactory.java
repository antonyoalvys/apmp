package br.com.apmp.ccompras.jsf.exceptionhandler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author antoniocarvalho
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
	private ExceptionHandlerFactory parent;

	public CustomExceptionHandlerFactory( ExceptionHandlerFactory parent ) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler handler = new CustomExceptionHandler( parent.getExceptionHandler() );
		return handler;
	}

}