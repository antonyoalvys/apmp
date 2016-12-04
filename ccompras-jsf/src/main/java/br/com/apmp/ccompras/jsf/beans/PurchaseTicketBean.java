package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.service.AssociateService;
import br.com.apmp.ccompras.service.CompanyService;
import br.com.apmp.ccompras.service.PeriodService;
import br.com.apmp.ccompras.service.PurchaseTicketService;

@Named
@ViewScoped
public class PurchaseTicketBean implements Serializable {

	private static final long serialVersionUID = -5391076748088986414L;

	@Inject
	private PurchaseTicketService purchaseTicketService;
	@Inject
	private AssociateService associateService;
	@Inject
	private CompanyService companyService;
	@Inject
	private PeriodService periodService;
	private PurchaseTicket entity;
	private List<Associate> associateList;
	private List<Company> companyList;

	private List<Period> periodList;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void register() {
		purchaseTicketService.save( this.entity );
		String message = String.format( "O cheque compra %s foi registrado com sucesso.", this.entity.getCode() );
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, message, null ) );
		entityClear();
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

	public void entityClear() {
		this.entity = new PurchaseTicket();
	}

	public PurchaseTicket getEntity() {
		return entity;
	}

	public void setEntity( PurchaseTicket entity ) {
		this.entity = entity;
	}

	public void clear() {
		entityClear();
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

}
