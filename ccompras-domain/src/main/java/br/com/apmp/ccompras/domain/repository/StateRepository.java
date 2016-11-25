package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.State;

public interface StateRepository extends BaseRepository<State> {

	public List<State> findByName( String name );

	public List<String> autocompleteNames( String name );

	public List<String> autocompleteShortNames( String shortName );
	
}
