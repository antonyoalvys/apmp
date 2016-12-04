package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.FileCovenant;
import br.com.apmp.ccompras.domain.entities.Period;

public interface FileCovenantRepository extends BaseRepository<FileCovenant> {

	public List<FileCovenant> findByPeriod( Period beginPeriod );

	public FileCovenant findByPeriodId( Long id );

}
