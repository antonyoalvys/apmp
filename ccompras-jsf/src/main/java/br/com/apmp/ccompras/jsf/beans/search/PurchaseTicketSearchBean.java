package br.com.apmp.ccompras.jsf.beans.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.Tab;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.domain.enums.PeriodStatus;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.AgreementService;
import br.com.apmp.ccompras.service.AssociateService;
import br.com.apmp.ccompras.service.CompanyService;
import br.com.apmp.ccompras.service.PeriodService;
import br.com.apmp.ccompras.service.PurchaseTicketService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class PurchaseTicketSearchBean extends BaseBeanSearch<PurchaseTicket> {

	private static final long serialVersionUID = 8374277006321260557L;

	@Inject
	private PurchaseTicketService purchaseTicketService;

	@Inject
	private CompanyService companyService;
	@Inject
	private AssociateService associateService;

	@Inject
	private PeriodService periodService;
	@Inject
	private AgreementService agreementService;

	private PurchaseTicket purchaseTicketSearch;

	private List<PurchaseTicket> purchaseTicketList;

	private List<Associate> associateList;

	private List<Company> companyList;

	private List<Period> periodList;

	private List<Agreement> agreementList;

	private Period period;

	private Tab searchTab;

	@PostConstruct
	public void init() {
		this.companyList = new ArrayList<Company>();
		this.associateList = new ArrayList<Associate>();
		this.periodList = new ArrayList<Period>();
		this.agreementList = new ArrayList<Agreement>();
		clear();
	}

	public void findByEntity() throws ServiceException {
		this.purchaseTicketList = (List<PurchaseTicket>) purchaseTicketService.findByEntity( getSearchEntity() );
	}

	public void show( PurchaseTicket purchaseTicket ) {
		setShowEntity( purchaseTicket );
		super.show();
	}

	public void save() {
		purchaseTicketService.save( getEditEntity() );
		String message = String.format( "O cheque de compra %s foi atualizado.", getEditEntity().getCode() );
		closeEdit();

		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void delete() throws ServiceException {
		purchaseTicketService.delete( getRemoveEntity() );
		String message = String.format( "O cheque de compra %s foi removido.", getRemoveEntity().getCode() );
		this.purchaseTicketList.remove( getRemoveEntity() );
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public List<Associate> autocompleteAssociate( String queryAssociate ) {
		queryAssociate = queryAssociate.trim();
		this.associateList = associateService.findByNameOrEnrollment( queryAssociate );
		return this.associateList;
	}

	public List<Company> autocompleteCompany( String queryCompany ) {
		queryCompany = queryCompany.trim();
		this.companyList = companyService.findByNameOrCnpj( queryCompany );
		return this.companyList;
	}

	public List<Period> autocompletePeriod( String queryPeriod ) {
		queryPeriod = queryPeriod.trim();
		this.periodList = periodService.findByDescription( queryPeriod );
		return this.periodList;
	}

	public List<Agreement> autocompleteAgreement( String queryAgreement ) {
		queryAgreement = queryAgreement.trim();
		this.agreementList = agreementService.findByName( queryAgreement );
		return this.agreementList;
	}

	public void changeAgreement( ValueChangeEvent event ) {
		if ( event != null && event.getNewValue() != null ) {
			String viewName = (String) UIComponent.getCurrentComponent( FacesContext.getCurrentInstance() ).getAttributes().get( "viewName" );
			if ( viewName.equals( "consulta" ) ) {
				getSearchEntity().setAgreement( (Agreement) event.getNewValue() );
				getSearchEntity().setCode( null );
			} else if ( viewName.equals( "editar" ) ) {
				getEditEntity().setAgreement( (Agreement) event.getNewValue() );
				getEditEntity().setCode( null );
				getEditEntity().setUsageDate( null );
			}
		}
	}

	public Boolean getShowCode() {
		return getSearchEntity().getAgreement() != null && getSearchEntity().getAgreement().getName().equals( "Convênio Compras" );
	}

	public void closeShow() {
		setShowEntity( new PurchaseTicket() );
		closeTab( getShowTab() );
	}

	public void closeEdit() {
		setEditEntity( new PurchaseTicket() );
		closeTab( getEditTab() );
	}

	public void clear() {
		setSearchEntity( new PurchaseTicket() );
		getSearchEntity().setPeriod( new Period() );
	}

	public void edit() {
		this.associateList.add( getEditEntity().getAssociate() );
		this.companyList.add( getEditEntity().getCompany() );
		this.periodList.add( getEditEntity().getPeriod() );
		this.agreementList.add( getEditEntity().getAgreement() );
		super.edit();
	}

	@Override
	protected List<PurchaseTicket> getList() throws ServiceException, RepositoryException {
		return purchaseTicketService.findAll();
	}

	@Override
	protected Class<PurchaseTicket> searchEntityClass() {
		return PurchaseTicket.class;
	}

	public PurchaseTicket getPurchaseTicketSearch() {
		return purchaseTicketSearch;
	}

	public void setPurchaseTicketSearch( PurchaseTicket purchaseTicketSearch ) {
		this.purchaseTicketSearch = purchaseTicketSearch;
	}

	public List<PurchaseTicket> getPurchaseTicketList() {
		return purchaseTicketList;
	}

	public void setPurchaseTicketList( List<PurchaseTicket> purchaseTicketList ) {
		this.purchaseTicketList = purchaseTicketList;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod( Period period ) {
		this.period = period;
	}

	public Tab getSearchTab() {
		return searchTab;
	}

	public void setSearchTab( Tab searchTab ) {
		this.searchTab = searchTab;
	}

	public List<Associate> getAssociateList() {
		return associateList;
	}

	public void setAssociateList( List<Associate> associateList ) {
		this.associateList = associateList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList( List<Company> companyList ) {
		this.companyList = companyList;
	}

	public List<Period> getPeriodList() {
		return periodList;
	}

	public void setPeriodList( List<Period> periodList ) {
		this.periodList = periodList;
	}

	public List<Agreement> getAgreementList() {
		return agreementList;
	}

	public void setAgreementList( List<Agreement> agreementList ) {
		this.agreementList = agreementList;
	}

}
