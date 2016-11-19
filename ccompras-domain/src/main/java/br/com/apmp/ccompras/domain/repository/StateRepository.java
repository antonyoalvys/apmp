package br.com.apmp.ccompras.domain.repository;

import br.com.apmp.ccompras.domain.entities.State;

public interface StateRepository extends BaseRepository<State> {

	public State findByName( String name );

}
