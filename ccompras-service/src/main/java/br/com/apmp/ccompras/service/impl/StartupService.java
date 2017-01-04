package br.com.apmp.ccompras.service.impl;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.shiro.authc.credential.PasswordService;

import br.com.apmp.ccompras.domain.entities.Role;
import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.domain.repository.PermissionRepository;
import br.com.apmp.ccompras.service.RoleService;
import br.com.apmp.ccompras.service.UserService;

@Singleton
@Startup
public class StartupService implements Serializable {

	private static final long serialVersionUID = -1660944101622700075L;
	
	@Inject
	private UserService userService;
	@Inject
	private PasswordService bCryptPasswordService;
	@Inject
	private RoleService roleService;
	@Inject
	private PermissionRepository permissionRepository; 
	
	@PostConstruct
	public void init() {
		
		if (roleService.autocomplete( "admin" ) == null ) {
			Role role = new Role();
			role.setName( "admin" );
			role.setPermissions( permissionRepository.findAll() );
			roleService.save( role );
		}
		
		if ( userService.findByUsername( "admin" ) == null ) {
			Role role = roleService.findByName( "admin" );
			User user = new User();
			user.setRole( role );
			user.setMail( "admin@email.com" );
			user.setUsername( "admin" );
			user.setPassword( bCryptPasswordService.encryptPassword( "admin" ) );
			userService.save( user );
		}
		
	}

}
