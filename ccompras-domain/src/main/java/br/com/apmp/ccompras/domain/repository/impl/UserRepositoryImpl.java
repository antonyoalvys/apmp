package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.QUser;
import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.domain.repository.UserRepository;

@Named
@Dependent
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

	private static final long serialVersionUID = -7257427568074640728L;

	@PersistenceContext
	private EntityManager em;

	@Override
	public User findUser( String username, String password ) {
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<User> query = new JPAQuery<User>( this.em );
		QUser qUser = QUser.user;

		if ( username == null || password == null )
			return null;

		bb.and( qUser.active.isTrue() );
		bb.and( qUser.username.eq( username ) );
		bb.and( qUser.password.eq( password ) );

		return query.from( qUser ).where( bb ).fetchOne();

	}

	@Override
	public String findCredentials( String username ) {
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<User> query = new JPAQuery<User>( this.em );
		QUser qUser = QUser.user;

		if ( username == null )
			return null;

		bb.and( qUser.active.isTrue() );
		bb.and( qUser.username.eq( username ) );

		User user = query.from( qUser ).where( bb ).fetchOne();
		if ( user != null )
			return user.getPassword();
		else
			return null;

	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<User> getClassT() {
		return User.class;
	}

	@Override
	public List<User> findByUsername( String username ) {
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<User> query = new JPAQuery<User>( this.em );
		QUser qUser = QUser.user;

		if ( username == null )
			return null;

		bb.and( qUser.active.isTrue() );
		bb.and( qUser.username.eq( username ) );

		return query.from( qUser ).where( bb ).orderBy( qUser.username.asc() ).fetch();
	}

	@Override
	public List<User> findByEntity( User entity ) {
		BooleanBuilder bb = new BooleanBuilder();
		JPQLQuery<User> query = new JPAQuery<User>( this.em );
		QUser qUser = QUser.user;

		if ( entity != null && entity.getUsername() != null )
			bb.and( qUser.username.containsIgnoreCase( entity.getUsername() ) );

		if ( entity != null && entity.getMail() != null )
			bb.and( qUser.mail.containsIgnoreCase( entity.getMail() ) );

		return query.from( qUser ).where( bb ).orderBy( qUser.username.asc() ).fetch();
	}

}
