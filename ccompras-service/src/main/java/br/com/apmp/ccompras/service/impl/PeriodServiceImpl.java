package br.com.apmp.ccompras.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.repository.PeriodRepository;
import br.com.apmp.ccompras.service.PeriodService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class PeriodServiceImpl implements PeriodService {

	private static final long serialVersionUID = -2257785836196538411L;

	@Inject
	private PeriodRepository periodRepository;

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public Period findByDate( LocalDate date ) {
		return periodRepository.findByDate( date );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void save( Period entity ) {
		periodRepository.save( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void delete( Period entity ) {
		periodRepository.delete( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<Period> findByEntity( Period entity ) {
		return periodRepository.findByEntity( entity ) ;
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<Period> findAll() {
		return periodRepository.findAll();
	}

}
