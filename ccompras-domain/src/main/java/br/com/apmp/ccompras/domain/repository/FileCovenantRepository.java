package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.FileCovenant;
import br.com.apmp.ccompras.domain.entities.Period;

public interface FileCovenantRepository {

	public List<FileCovenant> findByPeriod( Period beginPeriod, Period endPeriod );

}
