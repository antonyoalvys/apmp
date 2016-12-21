package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.User;

public interface UserRepository extends BaseRepository<User> {

	public void save( User entity );

	public User findUser( String username, String password );

	public String findCredentials( String username );

	public List<User> findByUsername( String username );

	public List<User> findByEntity( User entity );
}
