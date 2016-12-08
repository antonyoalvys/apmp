package br.com.apmp.ccompras.domain.repository;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<T> extends Serializable {

	void save( T entity );

	void save( List<T> entities );

	void delete( T entity );

	void delete( List<T> entities );

	T findById( Object id );

	List<T> findAll();

}
