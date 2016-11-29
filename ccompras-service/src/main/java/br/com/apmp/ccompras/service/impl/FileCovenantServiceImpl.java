package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.FileCovenant;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.repository.FileCovenantRepository;
import br.com.apmp.ccompras.service.FileCovenantService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class FileCovenantServiceImpl implements FileCovenantService {

	private static final long serialVersionUID = 3175158112059892738L;

	@Inject
	private FileCovenantRepository fileCovenantRepository;

	@Override
	public List<FileCovenant> findByPeriod( Period beginPeriod, Period endPeriod ) {
		return fileCovenantRepository.findByPeriod( beginPeriod, endPeriod );
	}

}
