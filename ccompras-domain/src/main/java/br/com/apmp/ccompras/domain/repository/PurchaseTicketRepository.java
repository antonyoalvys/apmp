package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;

public interface PurchaseTicketRepository extends BaseRepository<PurchaseTicket> {

	public List<PurchaseTicket> findByCompany( Company company );

	public List<PurchaseTicket> findByAssociate( Associate associate );
	
	public List<PurchaseTicket> findByEntity( PurchaseTicket entity,  Period period  );

	public PurchaseTicket findByCode( String code );

}
