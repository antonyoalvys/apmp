package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.jsf.security.configuration.BCryptPasswordService;
import br.com.apmp.ccompras.service.UserService;

@Named
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = -3435386672160894286L;

	@Inject
	private UserService userService;
	@Inject
    private BCryptPasswordService passwordService;
	
	private User entity;

	private String mailConfirmation;
	private String passwordConfirmation;

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
		this.mailConfirmation = "";
		this.passwordConfirmation = "";
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

}
