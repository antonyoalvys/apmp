package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.repository.AssociateRepository;
import br.com.apmp.ccompras.service.AssociateService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class AssociateServiceImpl implements AssociateService {

	private static final long serialVersionUID = 241540645165604599L;

	@Inject
	private AssociateRepository associateRepository;

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void save( Associate entity ) {
		associateRepository.save( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void delete( Associate entity ) {
		associateRepository.delete( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<Associate> findAll() {
		return associateRepository.findAll();
	}

	@Override
	public List<Associate> findByEntity( Associate associateSearch ) {
		return associateRepository.findByEntity( associateSearch );
	}

	@Override
	public void disable( Associate associate ) {
		associateRepository.disable( associate );
	}

	@Override
	public List<Associate> findByNameOrEnrollment( String queryAssociate ) {
		return associateRepository.findByNameOrEnrollment( queryAssociate );
	}

}
