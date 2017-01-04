package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.Role;
import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.jsf.security.configuration.BCryptPasswordService;
import br.com.apmp.ccompras.service.RoleService;
import br.com.apmp.ccompras.service.UserService;

@Named
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = -3435386672160894286L;

	@Inject
	private UserService userService;
	@Inject
	private BCryptPasswordService passwordService;
	@Inject
	private RoleService roleService;

	private User entity;
	private String mailConfirmation;
	private String passwordConfirmation;
	private List<Role> roleList;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void register() {
		String message = "";
		FacesMessage facesMessage = null;

		if ( !entity.getPassword().equals( this.passwordConfirmation ) ) {
			message = String.format( "As senhas não são iguais." );
			facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null );
		} else if ( !entity.getMail().equals( this.mailConfirmation ) ) {
			message = String.format( "Os emails não são iguais." );
			facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null );
		} else {
			this.entity.setPassword( passwordService.encryptPassword( this.entity.getPassword() ) );
			userService.save( this.entity );
			message = String.format( "O usuário %s foi registrado com sucesso.", this.entity.getUsername() );
			facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
			entityClear();
		}

		FacesContext.getCurrentInstance().addMessage( null, facesMessage );

	}

	public void entityClear() {
		this.entity = new User();
		this.entity.setRole( new Role() );
		this.mailConfirmation = "";
		this.passwordConfirmation = "";
		this.roleList = new ArrayList<Role>();
	}

	public List<Role> autocompleteRole( String name ) {
		name = name.trim();
		this.roleList = roleService.autocomplete( name );
		return this.roleList;
	}

	public User getEntity() {
		return entity;
	}

	public void setEntity( User entity ) {
		this.entity = entity;
	}

	public void clear() {
		entityClear();
	}

	public String getMailConfirmation() {
		return mailConfirmation;
	}

	public void setMailConfirmation( String mailConfirmation ) {
		this.mailConfirmation = mailConfirmation;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation( String passwordConfirmation ) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList( List<Role> roleList ) {
		this.roleList = roleList;
	}
	
}
