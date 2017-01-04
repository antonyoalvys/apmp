package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.querydsl.core.Tuple;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.domain.enums.PeriodStatus;
import br.com.apmp.ccompras.domain.repository.PurchaseTicketRepository;
import br.com.apmp.ccompras.service.PurchaseTicketService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class PurchaseTicketServiceImpl implements PurchaseTicketService {

	private static final long serialVersionUID = 5800146719546673066L;

	@Inject
	private PurchaseTicketRepository purchaseTicketRepository;

	@Override
	@TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
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
		if ( entity.getPeriod().getPeriodStatus().equals( PeriodStatus.CLOSE ) )
			throw new ServiceException( "O período " + entity.getPeriod().getDescription() + " encontra-se fechado." );
		this.purchaseTicketRepository.save( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void delete( PurchaseTicket entity ) {
		if ( entity.getPeriod().getPeriodStatus().equals( PeriodStatus.CLOSE ) )
			throw new ServiceException( "O período " + entity.getPeriod().getDescription() + " encontra-se fechado." );
		entity = this.purchaseTicketRepository.findById( entity.getId() );
		this.purchaseTicketRepository.delete( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
	public PurchaseTicket findByCode( String code ) {
		return this.purchaseTicketRepository.findByCode( code );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<PurchaseTicket> findByAssociate( Associate associate ) {
		return this.purchaseTicketRepository.findByAssociate( associate );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<PurchaseTicket> findByEntity( PurchaseTicket entity ) {
		return this.purchaseTicketRepository.findByEntity( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
	public List<PurchaseTicket> findByPeriodId( Long periodId ) {
		return this.purchaseTicketRepository.findByPeriodId( periodId );
	}

	@Override
	public List<Tuple> findForFile( Long periodId ) {
		return this.purchaseTicketRepository.findForFile( periodId );
	}

}
