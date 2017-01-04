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

import org.apache.commons.lang.exception.ExceptionUtils;

import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.exceptions.ServiceException;


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
//	@Override
//	public void handle() throws FacesException {
//		Logger logger = LoggerFactory.getLogger( CustomExceptionHandler.class );
//
//		final Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
//		while ( iterator.hasNext() ) {
//			ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
//			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
//
//			// Recupera a exceção do contexto
//			Throwable t = context.getException();
//
//			// Aqui tentamos tratar a exeção
//			try {
//				// imprimir a stacktrace no log
//				logger.error( t.getMessage() );
//
//				// Coloca uma mensagem de exceção no mapa da request
//				requestMap.put( "exceptionMessage", t.getMessage() );
//
//				FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro desconhecido, contacte o administrador do sistema.", "" );
//
//				if ( isRethrown( t ) ) {
//					Throwable unwrapped = getRootCause( t );
//					if ( unwrapped.getCause() != null && unwrapped.getCause().getCause() != null )
//						message = new FacesMessage( FacesMessage.SEVERITY_ERROR, unwrapped.getCause().getCause().getMessage(), "" );
//					else if (unwrapped.getCause() != null)
//						message = new FacesMessage( FacesMessage.SEVERITY_ERROR, unwrapped.getCause().getMessage(), "" );
//					else 
//						message = new FacesMessage( FacesMessage.SEVERITY_ERROR, unwrapped.getMessage(), "" );
//						
//				} 
//				
//				// Avisa o usuário do erro
//				FacesContext.getCurrentInstance().addMessage( null, message );
//
//				// Renderiza a pagina de erro e exibe as mensagens
//				facesContext.renderResponse();
//			} finally {
//				// Remove a exeção da fila
//				iterator.remove();
//			}
//		}
//		// Manipula o erro
//		getWrapped().handle();
//	}
	
	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents()
				.iterator(); iterator.hasNext();) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();
			Throwable t = context.getException();
			t.printStackTrace();
			try {
				handleServiceException(t);
			} finally {
				iterator.remove();
			}
		}
		getWrapped().handle();
	}	
	
	private void handleServiceException(Throwable e) {

		// FIXME pog para não aparecer a mensagem de erro no sistema devido a
		// não encontrar um componente com id, por conta da implementação
		// jsf no jboss-as-7.1.1
		if (e instanceof IllegalArgumentException) {
			e.printStackTrace();
			return;
		}

		FacesContext context = FacesContext.getCurrentInstance();
		
		Throwable throwable = ExceptionUtils.getRootCause(e);

		if (throwable instanceof RepositoryException) {

			if (((RepositoryException) throwable).getMessage() != null) {
				context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ((RepositoryException) throwable).getMessage(),""));
			} else {
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro Inesperado !",
						throwable.getMessage()));
			}

		} else if (throwable instanceof ServiceException) {

			if (((ServiceException) throwable).getMessage() != null) {
				context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ((ServiceException) throwable).getMessage(),""));
			} else {
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro Inesperado !",
						throwable.getMessage()));
			}

		}
		
//		else if (throwable instanceof ValidationException) {
//
//			addFacesMessage(context, FacesMessage.SEVERITY_ERROR,
//					((ValidationException) throwable).getMessages());
//
//		} 
//		else if (throwable instanceof ValidationBatchException) {
//
//		} 
//		else if (throwable instanceof ConstraintViolationException) {
//
//
//
//		} else if (throwable instanceof StaleObjectStateException) {
//
//
//		} 
//		else if (throwable != null
//				&& AccessDeniedException.class.isAssignableFrom(throwable
//						.getClass())) {
//
//		} 
		else {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro desconhecido.", null));

		}
	}	

	private boolean isRethrown( Throwable t ) {
		return ( !( t instanceof AbortProcessingException ) );
	}

}