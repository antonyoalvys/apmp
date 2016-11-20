package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;

public interface PurchaseTicketService extends Serializable {

	public PurchaseTicket findById( Long id );

	public PurchaseTicket findByCode( String code );

	public List<PurchaseTicket> findByCompany( Company company );

	public List<PurchaseTicket> findByAssociate( Associate associate );

	public List<PurchaseTicket> findAll();

	public void save( PurchaseTicket entity );

	public void delete( PurchaseTicket entity );

}
