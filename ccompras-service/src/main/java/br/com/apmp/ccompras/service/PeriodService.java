package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.Period;

public interface PeriodService extends Serializable {


	public void save( Period entity );

	public void delete( Period entity );

	public List<Period> findByEntity( Period entity );

	public List<Period> findAll();

	public List<Period> findByDescription( String queryPeriod );

}
