package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import com.querydsl.core.Tuple;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;

public interface PurchaseTicketRepository extends BaseRepository<PurchaseTicket> {

	public List<PurchaseTicket> findByCompany( Company company );

	public List<PurchaseTicket> findByAssociate( Associate associate );
	
	public List<PurchaseTicket> findByEntity( PurchaseTicket entity );

	public PurchaseTicket findByCode( String code );

	public List<PurchaseTicket> findByPeriodId( Long periodId );
	
	public List<Tuple> findForFile( Long periodId );

}
