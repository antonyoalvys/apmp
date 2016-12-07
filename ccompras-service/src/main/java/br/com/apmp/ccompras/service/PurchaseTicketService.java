package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import com.querydsl.core.Tuple;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;

public interface PurchaseTicketService extends Serializable {

	public PurchaseTicket findById( Long id );

	public PurchaseTicket findByCode( String code );

	public List<PurchaseTicket> findByCompany( Company company );

	public List<PurchaseTicket> findByAssociate( Associate associate );

	public List<PurchaseTicket> findAll();

	public List<PurchaseTicket> findByEntity( PurchaseTicket entity );

	public void save( PurchaseTicket entity );

	public void delete( PurchaseTicket entity );

	public List<PurchaseTicket> findByPeriodId( Long id );
	
	public List<Tuple> findForFile( Long periodId );

}
