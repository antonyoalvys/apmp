package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.FileCovenant;
import br.com.apmp.ccompras.domain.entities.Period;

public interface FileCovenantService extends Serializable {

	public List<FileCovenant> findByPeriod( Period period );

	public void generate( Period period );

	public void delete( FileCovenant entity );
	
	public enum SideFit {
		LEFT,
		RIGHT;

	}

}
