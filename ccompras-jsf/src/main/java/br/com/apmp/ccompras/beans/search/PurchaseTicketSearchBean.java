package br.com.apmp.ccompras.beans.search;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.Tab;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.PurchaseTicketService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class PurchaseTicketSearchBean extends BaseBeanSearch<PurchaseTicket> {

	private static final long serialVersionUID = 8374277006321260557L;

	@Inject
	private PurchaseTicketService purchaseTicketService;

	private PurchaseTicket purchaseTicketSearch;

	private List<PurchaseTicket> purchaseTicketList;

	private List<Associate> associateList;

	private List<Company> companyList;

	private Period period;

	private Tab searchTab;

	@PostConstruct
	public void init() {
		clear();
	}

	public void findByEntity() throws ServiceException {
		this.purchaseTicketList = (List) purchaseTicketService.findByEntity( this.purchaseTicketSearch, period );
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
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
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
		this.purchaseTicketSearch = new PurchaseTicket();
		this.period = new Period();
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

}
