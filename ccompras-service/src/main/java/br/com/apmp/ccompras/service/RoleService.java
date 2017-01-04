package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.Role;

public interface RoleService extends Serializable {

	public List<Role> autocomplete( String name );

}
