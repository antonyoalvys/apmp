package br.com.apmp.ccompras.domain.repository.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.apmp.ccompras.domain.entities.QRole;
import br.com.apmp.ccompras.domain.entities.Role;
import br.com.apmp.ccompras.domain.repository.RoleRepository;

@Named
@Dependent
public class RoleRepositoryImpl extends BaseRepositoryImpl<Role> implements RoleRepository {

	private static final long serialVersionUID = 2979472055244489400L;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Role> autocomplete( String name ) {
		BooleanBuilder bb= new BooleanBuilder();
		JPAQuery<Role> query = new JPAQuery<Role>( em );
		QRole qRole = QRole.role;
		
		if ( name != null && !name.isEmpty() )
			bb.and( qRole.name.containsIgnoreCase( name ));
		
		bb.and( qRole.enabled.isTrue() );
		
		return query.from( qRole ).where( bb ).orderBy( qRole.name.asc() ).fetch();
	}

	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public Class<Role> getClassT() {
		return Role.class;
	}

}
