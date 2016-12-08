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
import br.com.apmp.ccompras.domain.repository.AgreementRepository;
import br.com.apmp.ccompras.service.AgreementService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class AgreementServiceImpl implements AgreementService, Serializable {

	private static final long serialVersionUID = -2788256557892249792L;

	@Inject
	private AgreementRepository agreementRepository;

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void save( Agreement entity ) {
		this.agreementRepository.save( entity );
	}

	@Override
	public void delete( Agreement entity ) {
		this.agreementRepository.delete( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
	public Agreement findById( Long id ) {
		return this.agreementRepository.findById( id );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
	public List<Agreement> findByName( String queryName ) {
		return this.agreementRepository.findByName( queryName );
	}

	@Override
	public List<Agreement> findAll() {
		return this.agreementRepository.findAll();
	}

}
