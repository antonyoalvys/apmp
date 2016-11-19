package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.domain.repository.PurchaseTicketRepository;

@Named
@Dependent
public class PurchaseTicketRepositoryImpl extends BaseRepositoryImpl<PurchaseTicket> implements PurchaseTicketRepository {

	private static final long serialVersionUID = -704198090660310231L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PurchaseTicket> findByCompany( Company company ) {
		return null;
	}

	@Override
	public List<PurchaseTicket> findByAssociate( Associate associate ) {
		return null;
	}

	@Override
	public PurchaseTicket findByCode( String code ) {
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<PurchaseTicket> getClassT() {
		return PurchaseTicket.class;
	}

}
