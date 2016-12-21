package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.User;

public interface UserService extends Serializable {

	public void save( User entity );

	public List<User> findAll();

	public List<User> findByEntity( User entity );

	public List<User> findByUsername( String username );

	public void disable( User entity );

	public User findById( Long id );

}
