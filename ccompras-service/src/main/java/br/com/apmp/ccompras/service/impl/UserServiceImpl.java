package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.domain.repository.UserRepository;
import br.com.apmp.ccompras.service.UserService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
@TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
public class UserServiceImpl implements UserService {

	private static final long serialVersionUID = -8231613099946407429L;

	@Inject
	private UserRepository userRepository;

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void save( User entity ) {
		this.userRepository.save( entity );
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findByEntity( User entity ) {
		return userRepository.findByEntity( entity );
	}

	@Override
	public List<User> autocompleteByUsername( String username ) {
		return userRepository.autocompleteByUsername( username );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void disable( User entity ) {
		User disabreEntity = userRepository.findById( entity.getId() );
		disabreEntity.setEnabled( false );
		userRepository.save( disabreEntity );
		entity = disabreEntity;
	}

	@Override
	public User findById( Long id ) {
		return userRepository.findById( id );
	}

	@Override
	public User findByUsername( String username ) {
		return userRepository.findByUsername( username );
	}

}
