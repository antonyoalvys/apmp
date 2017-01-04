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
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Rebate;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.AgreementService;
import br.com.apmp.ccompras.service.CompanyService;
import br.com.apmp.ccompras.service.RebateService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class RebateSearchBean extends BaseBeanSearch<Rebate> {

	private static final long serialVersionUID = 3381512025802060662L;

	@Inject
	private RebateService rebateService;

	@Inject
	private CompanyService companyService;
	@Inject
	private AgreementService agreementService;

	private List<Rebate> rebateList;

	private List<Company> companyList;

	private List<Agreement> agreementList;

	private Tab searchTab;

	@PostConstruct
	public void init() {
		clear();
	}

	public void findByEntity() throws ServiceException {
		this.rebateList = (List<Rebate>) rebateService.findByEntity( getSearchEntity() );
	}

	public void show( Rebate rebate ) {
		setShowEntity( rebate );
		super.show();
	}

	public void save() {
		rebateService.save( getEditEntity() );
		String message = String.format( "O desconto foi atualizado." );
		closeEdit();

		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void delete() throws ServiceException {
		rebateService.delete( getRemoveEntity() );
		this.rebateList.remove( getRemoveEntity() );
		String message = String.format( "O desconto foi removido.");
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public List<Company> autocompleteCompany( String queryCompany ) {
		queryCompany = queryCompany.trim();
		this.companyList = companyService.findByNameOrCnpj( queryCompany );
		return this.companyList;
	}

	public List<Agreement> autocompleteAgreement( String queryAgreement ) {
		queryAgreement = queryAgreement.trim();
		this.agreementList = agreementService.findByName( queryAgreement );
		return this.agreementList;
	}

	public Boolean getShowCode() {
		return getSearchEntity().getAgreement() != null && getSearchEntity().getAgreement().getName().equals( "Convênio Compras" );
	}

	public void closeShow() {
		setShowEntity( new Rebate() );
		closeTab( getShowTab() );
	}

	public void closeEdit() {
		setEditEntity( new Rebate() );
		closeTab( getEditTab() );
	}

	public void clear() {
		this.companyList = new ArrayList<Company>();
		this.agreementList = new ArrayList<Agreement>();
		
		setSearchEntity( new Rebate() );
	}

	public void edit() {
		this.agreementList.add( getEditEntity().getAgreement() );
		this.companyList.add( getEditEntity().getCompany() );
		super.edit();
	}

	@Override
	protected List<Rebate> getList() throws ServiceException, RepositoryException {
		return rebateService.findAll();
	}

	@Override
	protected Class<Rebate> searchEntityClass() {
		return Rebate.class;
	}


	public List<Rebate> getRebateList() {
		return rebateList;
	}

	public void setRebateList( List<Rebate> rebateList ) {
		this.rebateList = rebateList;
	}

	public Tab getSearchTab() {
		return searchTab;
	}

	public void setSearchTab( Tab searchTab ) {
		this.searchTab = searchTab;
	}


	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList( List<Company> companyList ) {
		this.companyList = companyList;
	}


	public List<Agreement> getAgreementList() {
		return agreementList;
	}

	public void setAgreementList( List<Agreement> agreementList ) {
		this.agreementList = agreementList;
	}

}
