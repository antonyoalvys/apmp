package br.com.apmp.ccompras.domain.repository;

import br.com.apmp.ccompras.domain.entities.User;

public interface UserRepository extends BaseRepository<User> {

	public void save( User entity );

	public User findUser( String username, String password );

	public String findCredentials( String username );
}
