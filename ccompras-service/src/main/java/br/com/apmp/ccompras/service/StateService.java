package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.State;

public interface StateService extends Serializable {

	public List<State> findAll();

	public State findById( Long id );

	public List<State> findByName( String name );

	public List<String> autocompleteNames( String name );

	public List<String> autocompleteShortNames( String shortName );

}
