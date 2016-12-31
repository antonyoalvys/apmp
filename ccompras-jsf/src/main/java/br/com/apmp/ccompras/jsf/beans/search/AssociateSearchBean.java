package br.com.apmp.ccompras.jsf.beans.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.primefaces.component.tabview.Tab;

import br.com.apmp.ccompras.domain.entities.Address;
import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.AssociateService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class AssociateSearchBean extends BaseBeanSearch<Associate> {

	private static final long serialVersionUID = 8374277006321260557L;

	@Inject
	private AssociateService associateService;

	private List<Associate> associateList;

	private Tab searchTab;

	@PostConstruct
	public void init() {
		clear();
	}

	public void findByEntity() throws ServiceException {
		this.associateList = (List<Associate>) associateService.findByEntity( getSearchEntity() );
	}

	public void show( Associate associate ) {
		SecurityUtils.getSubject().checkPermission( "associado:exibir" );
		setShowEntity( associate );
		super.show();
	}

	@RequiresPermissions("associate:save")
	public void save() {
		associateService.save( getEditEntity() );
		String message = String.format( "O associado %s foi atualizado.", getEditEntity().getName() );
		closeEdit();
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void edit() {
		if ( getEditEntity().getAddress() == null )
			getEditEntity().setAddress( new Address() );
		super.edit();
	}

	public void disable() throws ServiceException {
		associateService.disable( getRemoveEntity() );
		String message = String.format( "O associado %s foi desativado.", getRemoveEntity().getName() );
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void changeState( ValueChangeEvent event ) {
		if ( event != null && event.getNewValue() != null ) {
			getEditEntity().getAddress().setState( (String) event.getNewValue() );
			getEditEntity().getAddress().setCity( null );
		}
	}

	public void closeShow() {
		setShowEntity( new Associate() );
		closeTab( getShowTab() );
	}

	public void closeEdit() {
		setEditEntity( new Associate() );
		closeTab( getEditTab() );
	}

	public void clear() {
		setSearchEntity( new Associate() );
		this.associateList = new ArrayList<Associate>();
	}

	@Override
	protected List<Associate> getList() throws ServiceException, RepositoryException {
		return associateService.findAll();
	}

	@Override
	protected Class<Associate> searchEntityClass() {
		return Associate.class;
	}

	public List<Associate> getAssociateList() {
		return associateList;
	}

	public void setAssociateList( List<Associate> associateList ) {
		this.associateList = associateList;
	}

	public Tab getSearchTab() {
		return searchTab;
	}

	public void setSearchTab( Tab searchTab ) {
		this.searchTab = searchTab;
	}

}
