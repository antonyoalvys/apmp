package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.Period;

public interface PeriodService extends Serializable {

	public Period findByDate( LocalDate date );

	public void save( Period entity );

	public void delete( Period entity );

	public List<Period> findByEntity( Period entity );

	public List<Period> findAll();

	public void validatePeriod( Period entity );

}
