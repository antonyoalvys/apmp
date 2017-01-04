package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.Role;

public interface RoleRepository extends BaseRepository<Role> {

	public List<Role> autocomplete( String name );

}
