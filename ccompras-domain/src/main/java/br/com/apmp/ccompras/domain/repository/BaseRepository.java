package br.com.apmp.ccompras.domain.repository;

import java.util.List;

public interface BaseRepository<T> {

	void save( T entity );

	void save( List<T> entities );

	void delete( T entity );

	void delete( List<T> entities );

	T findById( Object id );

	List<T> findAll();

}
