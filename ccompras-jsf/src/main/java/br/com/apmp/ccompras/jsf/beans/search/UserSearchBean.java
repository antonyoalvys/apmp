package br.com.apmp.ccompras.jsf.beans.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.Tab;

import br.com.apmp.ccompras.domain.entities.Role;
import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.jsf.security.configuration.BCryptPasswordService;
import br.com.apmp.ccompras.service.RoleService;
import br.com.apmp.ccompras.service.UserService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class UserSearchBean extends BaseBeanSearch<User> {

	private static final long serialVersionUID = -7625713664613996858L;

	@Inject
	private UserService userService;
	@Inject
	private BCryptPasswordService passwordService;
	@Inject
	private RoleService roleService;

	private List<User> userList;
	private List<Role> roleList;

	private Tab searchTab;

	private String currentPassword;
	private String newPassword;
	private String newPasswordConfirmation;

	@PostConstruct
	public void init() {
		clear();
	}

	public void findByEntity() throws ServiceException {
		this.userList = (List<User>) userService.findByEntity( getSearchEntity() );
	}

	public void show( User user ) {
		setShowEntity( user );
		super.show();
	}

	public void save() {
		String message = "";
		FacesMessage facesMessage = null;

		if ( this.newPassword != null && !this.newPassword.trim().isEmpty() ) {
			this.currentPassword = this.currentPassword != null ? this.currentPassword : "";
			this.newPasswordConfirmation = this.newPasswordConfirmation != null ? this.newPasswordConfirmation : "";
			if ( !this.newPassword.equals( this.newPasswordConfirmation ) ) {
				message = String.format( "A nova senha e a confirmação não são iguais." );
				facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null );
				FacesContext.getCurrentInstance().addMessage( null, facesMessage );
				return;
			} else if ( !getEditEntity().getPassword().equals( this.currentPassword ) ) {
				message = String.format( "A senha atual é diferente." );
				facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null );
				FacesContext.getCurrentInstance().addMessage( null, facesMessage );
				return;
			}
		}
		String encryptedPassword = passwordService.encryptPassword( this.newPassword );
		getEditEntity().setPassword( encryptedPassword );
		userService.save( getEditEntity() );
		message = String.format( "O usuário %s foi atualizado com sucesso.", getEditEntity().getUsername() );
		facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void disable() throws ServiceException {
		userService.disable( getRemoveEntity() );
		String message = String.format( "O usuário %s foi desativado.", getRemoveEntity().getUsername() );
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public List<Role> autocompleteRole( String name ) {
		name = name.trim();
		this.roleList = roleService.autocomplete( name );
		return this.roleList;
	}

	public void closeShow() {
		setShowEntity( new User() );
		closeTab( getShowTab() );
	}

	public void closeEdit() {
		setEditEntity( new User() );
		closeTab( getEditTab() );
	}

	public void clear() {
		setSearchEntity( new User() );
		this.currentPassword = "";
		this.newPassword = "";
		this.newPasswordConfirmation = "";
		this.userList = new ArrayList<User>();
		this.roleList = new ArrayList<Role>();
	}

	public void edit() {
		if ( getEditEntity().getRole() == null )
			getEditEntity().setRole( new Role() );
		super.edit();
	}

	@Override
	protected List<User> getList() throws ServiceException, RepositoryException {
		return userService.findAll();
	}

	@Override
	protected Class<User> searchEntityClass() {
		return User.class;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList( List<User> userList ) {
		this.userList = userList;
	}

	public Tab getSearchTab() {
		return searchTab;
	}

	public void setSearchTab( Tab searchTab ) {
		this.searchTab = searchTab;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword( String currentPassword ) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword( String newPassword ) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	public void setNewPasswordConfirmation( String newPasswordConfirmation ) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}

}
