package br.com.apmp.ccompras.domain.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.apmp.ccompras.domain.entities.BaseEntity;
import br.com.apmp.ccompras.domain.repository.BaseRepository;

public abstract class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T>, Serializable {

	private static final long serialVersionUID = -5109990949264852282L;

	@Override
	public void save( T entity ) {
		if ( entity.getId() == null ) {
			getEntityManager().persist( entity );
			return;
		}

		getEntityManager().merge( entity );
	}

	@Override
	public void save( List<T> entities ) {
		for ( T baseEntity : entities ) {
			save( baseEntity );
		}
	}

	@Override
	public void delete( T entity ) {
		getEntityManager().remove( entity );

	}

	@Override
	public void delete( List<T> entities ) {
		for ( T t : entities ) {
			delete( t );
		}

	}

	@Override
	public List<T> findAll() {
		TypedQuery<T> query = getEntityManager().createQuery( "FROM " + getClassT().getName(), getClassT() );
		List<T> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public T findById( Object id ) {
		return getEntityManager().find( getClassT(), id );
	}

	public abstract EntityManager getEntityManager();

	public abstract Class<T> getClassT();

}
