package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.service.UserService;

@Named
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = -3435386672160894286L;

	@Inject
	private UserService userService;

	private User entity;

	private String emailConfirmation;
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
		} else if ( !entity.getMail().equals( this.emailConfirmation ) ) {
			message = String.format( "Os emails não são iguais." );
			facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null );
		} else {
			userService.save( this.entity );
			message = String.format( "O usuário %s foi registrado com sucesso.", this.entity.getUsername() );
		}

		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
		entityClear();
	}

	public void entityClear() {
		this.entity = new User();
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

}
