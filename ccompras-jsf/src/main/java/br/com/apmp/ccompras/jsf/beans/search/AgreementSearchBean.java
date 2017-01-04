package br.com.apmp.ccompras.jsf.beans.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.Tab;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.AgreementService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class AgreementSearchBean extends BaseBeanSearch<Agreement> {

	private static final long serialVersionUID = 8374277006321260557L;

	@Inject
	private AgreementService agreementService;

	private List<Agreement> agreementList;

	private Tab searchTab;

	@PostConstruct
	public void init() {
		clear();
	}

	public void findByEntity() throws ServiceException {
		this.agreementList = agreementService.findByName( getSearchEntity().getName() );
	}

	public void show( Agreement agreement ) {
		setShowEntity( agreement );
		super.show();
	}

	public void save() {
		agreementService.save( getEditEntity() );
		String message = String.format( "O convênio %s foi atualizado.", this.getEditEntity().getName() );
		closeEdit();
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void delete() throws ServiceException {
		agreementService.delete( getRemoveEntity() );
		this.agreementList.remove( getRemoveEntity() );
		String message = String.format( "O convênio %s removido.", getRemoveEntity().getName() );
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void closeShow() {
		setShowEntity( new Agreement() );
		closeTab( getShowTab() );
	}

	public void closeEdit() {
		setEditEntity( new Agreement() );
		closeTab( getEditTab() );
	}

	public void clear() {
		setSearchEntity( new Agreement() );
		this.agreementList = new ArrayList<Agreement>();
	}

	@Override
	protected List<Agreement> getList() throws ServiceException, RepositoryException {
		return agreementService.findAll();
	}

	@Override
	protected Class<Agreement> searchEntityClass() {
		return Agreement.class;
	}

	public List<Agreement> getAgreementList() {
		return agreementList;
	}

	public void setAgreementList( List<Agreement> agreementList ) {
		this.agreementList = agreementList;
	}

	public Tab getSearchTab() {
		return searchTab;
	}

	public void setSearchTab( Tab searchTab ) {
		this.searchTab = searchTab;
	}

}
