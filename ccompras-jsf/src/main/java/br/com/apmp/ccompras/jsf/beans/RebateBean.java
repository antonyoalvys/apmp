package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Rebate;
import br.com.apmp.ccompras.service.AgreementService;
import br.com.apmp.ccompras.service.CompanyService;
import br.com.apmp.ccompras.service.RebateService;

@Named
@ViewScoped
public class RebateBean implements Serializable {

	private static final long serialVersionUID = -5391076748088986414L;

	@Inject
	private RebateService rebateService;

	@Inject
	private CompanyService companyService;

	@Inject
	private AgreementService agreementService;

	private Rebate entity;
	private List<Company> companyList;
	private List<Agreement> agreementList;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void save() {
		rebateService.save( this.entity );
		String message = String.format( "O desconto foi registrado com sucesso." );
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, message, null ) );
		entityClear();
	}

	public void entityClear() {
		this.entity = new Rebate();
		this.agreementList = new ArrayList<Agreement>();
		this.companyList = new ArrayList<Company>();
	}

	public List<Company> autocompleteCompany( String query ) {
		query = query.trim();
		this.companyList = companyService.findByNameOrCnpj( query );
		return this.companyList;
	}

	public List<Agreement> autocompleteAgreement( String query ) {
		query = query.trim();
		this.agreementList = agreementService.findByName( query );
		return this.agreementList;
	}

	public void clear() {
		entityClear();
	}

	public Rebate getEntity() {
		return entity;
	}

	public void setEntity( Rebate entity ) {
		this.entity = entity;
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
