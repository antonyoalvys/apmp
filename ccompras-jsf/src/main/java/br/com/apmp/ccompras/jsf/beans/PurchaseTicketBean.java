package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.service.AgreementService;
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
	@Inject
	private AgreementService agreementService;
	private PurchaseTicket entity;
	private List<Associate> associateList;
	private List<Company> companyList;
	private List<Agreement> agreementList;

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

	public List<Agreement> autocompleteAgreement( String queryAgreement ) {
		queryAgreement = queryAgreement.trim();
		this.agreementList = agreementService.findByName( queryAgreement );
		return this.agreementList;
	}

	public Boolean getShowTicketDatas() {
		return this.entity.getAgreement() != null && entity.getAgreement().getName().equals( "Convênio Compras" );
	}

	public void changeAgreement( ValueChangeEvent event ) {
		if ( event != null && event.getNewValue() != null ) {
			this.entity.setAgreement( (Agreement) event.getNewValue() );
			this.entity.setUsageDate( null );
			this.entity.setCode( null );
		}
	}

	public void entityClear() {
		this.entity = new PurchaseTicket();
		this.companyList = new ArrayList<Company>();
		this.agreementList = new ArrayList<Agreement>();

		// retirar
		this.entity.setPeriod( autocompletePeriod( "11/2016" ).get( 0 ) );
		this.entity.setAgreement( agreementService.findById( new Long( 7 ) ) );
		this.agreementList.add( agreementService.findById( new Long( 7 ) ) );
		this.entity.setCompany( companyService.findById( new Long( 15 ) ) );
		this.companyList.add( companyService.findById( new Long( 15 ) ) );
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

	public List<Agreement> getAgreementList() {
		return agreementList;
	}

	public void setAgreementList( List<Agreement> agreementList ) {
		this.agreementList = agreementList;
	}

}
