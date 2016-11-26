package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.domain.repository.PurchaseTicketRepository;
import br.com.apmp.ccompras.service.PurchaseTicketService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class PurchaseTicketServiceImpl implements PurchaseTicketService {

	private static final long serialVersionUID = 5800146719546673066L;

	@Inject
	private PurchaseTicketRepository purchaseTicketRepository;

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public PurchaseTicket findById( Long id ) {
		return this.purchaseTicketRepository.findById( id );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<PurchaseTicket> findByCompany( Company company ) {
		return this.purchaseTicketRepository.findByCompany( company );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<PurchaseTicket> findAll() {
		return this.purchaseTicketRepository.findAll();
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void save( PurchaseTicket entity ) {
		this.purchaseTicketRepository.save( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void delete( PurchaseTicket entity ) {
		this.purchaseTicketRepository.delete( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public PurchaseTicket findByCode( String code ) {
		return this.purchaseTicketRepository.findByCode( code );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<PurchaseTicket> findByAssociate( Associate associate ) {
		return this.purchaseTicketRepository.findByAssociate( associate );
	}

	@Override
	public List<PurchaseTicket> findByEntity( PurchaseTicket entity, Period period ) {
		return this.purchaseTicketRepository.findByEntity( entity, period );
	}

}
