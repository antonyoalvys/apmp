package br.com.apmp.ccompras.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.Agreement;
import br.com.apmp.ccompras.domain.entities.Company;
import br.com.apmp.ccompras.domain.entities.Rebate;
import br.com.apmp.ccompras.domain.repository.RebateRepository;
import br.com.apmp.ccompras.service.RebateService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class RebateServiceImpl implements RebateService, Serializable {

	private static final long serialVersionUID = -6171243772599480462L;

	@Inject
	private RebateRepository rebateRepository;

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void save( Rebate entity ) {
		rebateRepository.save( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void delete( Rebate entity ) {
		rebateRepository.delete( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<Rebate> findByEntity( Rebate entity ) {
		return rebateRepository.findByEntity( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public Rebate findByCompanyAndAgreement( Company company, Agreement agreement ) {
		return rebateRepository.findByCompanyAndAgreement( company, agreement );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<Rebate> findAll() {
		return rebateRepository.findAll();
	}

}
