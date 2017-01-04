package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;

import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Role;
import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.domain.enums.PhoneType;
import br.com.apmp.ccompras.service.AssociateService;
import br.com.apmp.ccompras.service.RoleService;

@Named
@ViewScoped
public class AssociateBean implements Serializable {

	private static final long serialVersionUID = -5391076748088986414L;

	@Inject
	private AssociateService associateService;
	@Inject
	private RoleService roleService;
	private Associate entity;

	private String mailConfirmation;
	private String passwordConfirmation;
	private Boolean userAccount;
	private List<Role> roleList;

	@PostConstruct
	public void init() {
		entityClear();
	}

	public void register() {
		SecurityUtils.getSubject().checkPermission( "associado:salvar" );
		if (this.entity.getUser() != null )
			validateUser( this.entity.getUser() );
		associateService.save( this.entity );
		String message = String.format( "O associado %s foi registrado com sucesso.", this.entity.getName() );
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, message, null ) );
		entityClear();
	}

	public void changeState( ValueChangeEvent event ) {
		if ( event != null && event.getNewValue() != null ) {
			this.entity.getAddress().setState( (String) event.getNewValue() );
			this.entity.getAddress().setCity( null );
		}
	}

	public void entityClear() {
		this.entity = new Associate();
		this.entity.setMainPhoneType( PhoneType.LANDLINE );
		this.entity.setSecondaryPhoneType( PhoneType.MOBILE );
		this.userAccount = false;

	}
	
	public List<Role> autocompleteRole( String name ) {
		name = name.trim();
		this.roleList = roleService.autocomplete( name );
		return this.roleList;
	}	

	public void userAccountChange( ValueChangeEvent event ) {
		if ( event != null && event.getNewValue() != null ) {
			this.userAccount = (Boolean) event.getNewValue();
			if ( this.userAccount ) {
				this.entity.setUser( new User() );
				this.entity.getUser().setRole( new Role() );
				this.roleList = new ArrayList<Role>();
			} else {
				this.entity.setUser( null );
			}
		}
	}
	
	private void validateUser( User user ) {
		String message = "";
		FacesMessage facesMessage = null;

		if ( !user.getPassword().equals( this.passwordConfirmation ) ) {
			message = String.format( "As senhas não são iguais." );
			facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null );
			FacesContext.getCurrentInstance().addMessage( null, facesMessage );
		} else if ( !user.getMail().equals( this.mailConfirmation ) ) {
			message = String.format( "Os emails não são iguais." );
			facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null );
			FacesContext.getCurrentInstance().addMessage( null, facesMessage );
		}

	}
	

	public Associate getEntity() {
		return entity;
	}

	public void setEntity( Associate entity ) {
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

	public Boolean getUserAccount() {
		return userAccount;
	}

	public void setUserAccount( Boolean userAccount ) {
		this.userAccount = userAccount;
	}

}
