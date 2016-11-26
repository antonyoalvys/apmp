package br.com.apmp.ccompras.beans.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.Tab;

import br.com.apmp.ccompras.domain.entities.Address;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.CompanyService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class CompanySearchBean extends BaseBeanSearch<Company> {

	private static final long serialVersionUID = 8374277006321260557L;

	@Inject
	private CompanyService companyService;

	private Company companySearch;

	private List<Company> companyList;

	private Tab searchTab;

	@PostConstruct
	public void init() {
		clear();
	}

	public void findByEntity() throws ServiceException {
		this.companyList = (List) companyService.findByEntity( this.companySearch );
	}

	public void show( Company company ) {
		setShowEntity( company );
		super.show();
	}

	public void save() {
		companyService.save( getEditEntity() );
		String message = String.format( "O estabelecimento comercial %s foi atualizado.", getEditEntity().getName() );
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
		companyService.disable( getRemoveEntity() );
		String message = String.format( "O estabelecimento comercial %s foi desativado.", getRemoveEntity().getName() );
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
		setShowEntity( new Company() );
		closeTab( getShowTab() );
	}

	public void closeEdit() {
		setEditEntity( new Company() );
		closeTab( getEditTab() );
	}

	public void clear() {
		this.companySearch = new Company();
		this.companyList = new ArrayList<Company>();
	}

	@Override
	protected List<Company> getList() throws ServiceException, RepositoryException {
		return companyService.findAll();
	}

	@Override
	protected Class<Company> searchEntityClass() {
		return Company.class;
	}

	public Company getCompanySearch() {
		return companySearch;
	}

	public void setCompanySearch( Company companySearch ) {
		this.companySearch = companySearch;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList( List<Company> companyList ) {
		this.companyList = companyList;
	}

	public Tab getSearchTab() {
		return searchTab;
	}

	public void setSearchTab( Tab searchTab ) {
		this.searchTab = searchTab;
	}

}
