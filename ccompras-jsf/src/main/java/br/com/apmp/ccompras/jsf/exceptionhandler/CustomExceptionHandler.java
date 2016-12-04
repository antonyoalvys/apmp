package br.com.apmp.ccompras.jsf.exceptionhandler;

import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antoniocarvalho
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	// Obtém uma instância do FacesContext
	final FacesContext facesContext = FacesContext.getCurrentInstance();

	// Obtém um mapa do FacesContext
	final Map requestMap = facesContext.getExternalContext().getRequestMap();

	// Obtém o estado atual da navegação entre páginas do JSF
	final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

	// Declara o construtor que recebe uma exceptio do tipo ExceptionHandler
	// como parâmetro
	CustomExceptionHandler( ExceptionHandler exception ) {
		this.wrapped = exception;
	}

	// Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	// Sobrescreve o método handle que é responsável por manipular as exceções
	// do JSF
	@Override
	public void handle() throws FacesException {
		Logger logger = LoggerFactory.getLogger( CustomExceptionHandler.class );

		final Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
		while ( iterator.hasNext() ) {
			ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			// Recupera a exceção do contexto
			Throwable t = context.getException();

			// Aqui tentamos tratar a exeção
			try {
				// imprimir a stacktrace no log
				logger.error( t.getMessage() );

				// Coloca uma mensagem de exceção no mapa da request
				requestMap.put( "exceptionMessage", t.getMessage() );

				FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro desconhecido, contacte o administrador do sistema.", "" );

				if ( isRethrown( t ) ) {
					Throwable unwrapped = getRootCause( t );
					if ( unwrapped.getCause() != null && unwrapped.getCause().getCause() != null )
						message = new FacesMessage( FacesMessage.SEVERITY_ERROR, unwrapped.getCause().getCause().getMessage(), "" );
					else if (unwrapped.getCause() != null)
						message = new FacesMessage( FacesMessage.SEVERITY_ERROR, unwrapped.getCause().getMessage(), "" );
					else 
						message = new FacesMessage( FacesMessage.SEVERITY_ERROR, unwrapped.getMessage(), "" );
						
				} 
				
				// Avisa o usuário do erro
				FacesContext.getCurrentInstance().addMessage( null, message );

				// Renderiza a pagina de erro e exibe as mensagens
				facesContext.renderResponse();
			} finally {
				// Remove a exeção da fila
				iterator.remove();
			}
		}
		// Manipula o erro
		getWrapped().handle();
	}

	private boolean isRethrown( Throwable t ) {
		return ( !( t instanceof AbortProcessingException ) );
	}

}