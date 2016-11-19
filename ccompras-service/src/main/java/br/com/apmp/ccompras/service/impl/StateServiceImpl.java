package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.State;
import br.com.apmp.ccompras.domain.repository.StateRepository;
import br.com.apmp.ccompras.service.StateService;

@TransactionManagement( TransactionManagementType.CONTAINER )
@Stateless
public class StateServiceImpl implements StateService {

	private static final long serialVersionUID = -5351582069131404482L;

	@Inject
	private StateRepository stateRepository;

	@Override
	public List<State> findAll() {
		return this.stateRepository.findAll();
	}

	@Override
	public State findById( Long id ) {
		return this.stateRepository.findById( id );
	}

	@Override
	public State findByName( String name ) {
		return this.stateRepository.findByName( name );
	}

}
