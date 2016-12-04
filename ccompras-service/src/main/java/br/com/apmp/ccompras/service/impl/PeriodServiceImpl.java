package br.com.apmp.ccompras.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import br.com.apmp.ccompras.service.exceptions.ServiceException;

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
		validatePeriod( entity );
		setDescription( entity );
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
		return periodRepository.findByEntity( entity );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<Period> findAll() {
		return periodRepository.findAll();
	}

	@Override
	public void validatePeriod( Period entity ) {
		if ( entity.getBeginDate().isAfter( entity.getEndDate() ) )
			throw new ServiceException( "Data Inicial não pode ser posterior a Data Final" );

		Period otherPeriod = periodRepository.findByBeginDate( entity );
		if ( otherPeriod != null )
			throw new ServiceException( "Data Inicial já coberta pelo período " + otherPeriod.toString() );

		otherPeriod = periodRepository.findByEndDate( entity );
		if ( otherPeriod != null )
			throw new ServiceException( "Data Final já coberta pelo período " + otherPeriod.toString() );

	}

	@Override
	public List<Period> findByDescription( String queryPeriod ) {
		return periodRepository.findByDescription( queryPeriod );
	}

	private void setDescription( Period entity ) {
		String beginDate = entity.getBeginDate().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );
		String endDate = entity.getEndDate().format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );
		entity.setDescription( beginDate + " - " + endDate );
	}

}
