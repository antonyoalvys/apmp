package br.com.apmp.ccompras.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.apmp.ccompras.domain.entities.Role;
import br.com.apmp.ccompras.domain.repository.RoleRepository;
import br.com.apmp.ccompras.service.RoleService;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
@TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
public class RoleServiceImpl implements RoleService {

	private static final long serialVersionUID = 2233743213429116543L;

	@Inject
	private RoleRepository roleRepository;

	@Override
	public List<Role> autocomplete( String name ) {
		return roleRepository.autocomplete( name );
	}

}
