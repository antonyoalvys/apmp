package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Period;

public interface PeriodRepository extends BaseRepository<Period> {

	public List<Period> findByEntity( Period entity );

	public List<Period> findByDescription( String queryPeriod );

}
