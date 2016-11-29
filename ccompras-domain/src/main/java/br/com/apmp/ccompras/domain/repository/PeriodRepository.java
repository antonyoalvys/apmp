package br.com.apmp.ccompras.domain.repository;

import java.time.LocalDate;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.Period;

public interface PeriodRepository extends BaseRepository<Period> {

	public Period findByDate( LocalDate date );

	public List<Period> findByEntity( Period entity );


}
