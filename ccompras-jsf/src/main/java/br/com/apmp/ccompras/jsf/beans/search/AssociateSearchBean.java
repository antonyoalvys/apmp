package br.com.apmp.ccompras.jsf.beans.search;

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
import org.primefaces.component.tabview.Tab;

import br.com.apmp.ccompras.domain.entities.Address;
import br.com.apmp.ccompras.domain.entities.Associate;
import br.com.apmp.ccompras.domain.entities.Role;
import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.domain.enums.PhoneType;
import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.jsf.security.configuration.BCryptPasswordService;
import br.com.apmp.ccompras.service.AssociateService;
import br.com.apmp.ccompras.service.RoleService;
import br.com.apmp.ccompras.service.UserService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Named
@ViewScoped
public class AssociateSearchBean extends BaseBeanSearch<Associate> {

	private static final long serialVersionUID = 8374277006321260557L;

	@Inject
	private AssociateService associateService;
	@Inject
	private UserService userService;
	@Inject
	private BCryptPasswordService passwordService;
	@Inject
	private RoleService roleService;

	private List<Associate> associateList;

	private Tab searchTab;

	private boolean hasUserAccount;
	private List<User> userList;
	private String password;
	private String passwordConfirmation;
	private List<Role> roleList;

	@PostConstruct
	public void init() {
		clear();
	}

	public void findByEntity() throws ServiceException {
		SecurityUtils.getSubject().checkPermission( "associado:consultar" );
		this.associateList = (List<Associate>) associateService.findByEntity( getSearchEntity() );
	}

	public void show( Associate associate ) {
		SecurityUtils.getSubject().checkPermission( "associado:consultar" );
		setShowEntity( associate );
		super.show();
	}

	public void save() {
		SecurityUtils.getSubject().checkPermission( "associado:salvar" );
		validateUserAccount( getEditEntity().getUser() );
		associateService.save( getEditEntity() );
		String message = String.format( "O associado %s foi atualizado.", getEditEntity().getName() );
		closeEdit();
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void edit() {
		SecurityUtils.getSubject().checkPermission( "associado:salvar" );
		this.hasUserAccount = getEditEntity().getUser() != null;

		if ( getEditEntity().getMainPhoneType() == null )
			getEditEntity().setMainPhoneType( PhoneType.LANDLINE );
		if ( getEditEntity().getSecondaryPhoneType() == null )
			getEditEntity().setSecondaryPhoneType( PhoneType.MOBILE );
		if ( getEditEntity().getAddress() == null )
			getEditEntity().setAddress( new Address() );
		super.edit();
	}

	public void disable() throws ServiceException {
		SecurityUtils.getSubject().checkPermission( "associado:desativar" );
		associateService.disable( getRemoveEntity() );
		String message = String.format( "O associado %s foi desativado.", getRemoveEntity().getName() );
		FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_INFO, message, null );
		FacesContext.getCurrentInstance().addMessage( null, facesMessage );
	}

	public void changeState( ValueChangeEvent event ) {
		if ( event != null && event.getNewValue() != null ) {
			getEditEntity().getAddress().setState( (String) event.getNewValue() );
			getEditEntity().getAddress().setCity( null );
		}
	}
	
	public List<Role> autocompleteRole( String name ) {
		name = name.trim();
		this.roleList = roleService.autocomplete( name );
		return this.roleList;
	}		

	public List<User> autocompleteUser( String name ) {
		name = name.trim();
		this.userList = userService.autocompleteByUsername( name );
		return this.userList;
	}

	public void closeShow() {
		setShowEntity( new Associate() );
		closeTab( getShowTab() );
	}

	public void closeEdit() {
		setEditEntity( new Associate() );
		closeTab( getEditTab() );
	}

	public void clear() {
		setSearchEntity( new Associate() );
		this.associateList = new ArrayList<Associate>();
		this.hasUserAccount = true;
	}

	private void validateUserAccount( User user ) {
		if ( user == null )
			return;

		this.password = this.password == null ? "" : this.password;
		this.passwordConfirmation = this.passwordConfirmation == null ? "" : this.passwordConfirmation;

		if ( !this.password.equals( this.passwordConfirmation ) ) {
			String message = String.format( "As senhas não são iguais." );
			FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null );
			FacesContext.getCurrentInstance().addMessage( null, facesMessage );
			return;
		}

		if ( !this.password.isEmpty() )
			user.setPassword( passwordService.encryptPassword( this.password ) );
	}

	@Override
	protected List<Associate> getList() throws ServiceException, RepositoryException {
		return associateService.findAll();
	}

	@Override
	protected Class<Associate> searchEntityClass() {
		return Associate.class;
	}

	public void hasUserAccountChange( ValueChangeEvent event ) {
		this.hasUserAccount = (boolean) event.getNewValue();
		this.password = "";
		this.passwordConfirmation = "";
		if ( this.hasUserAccount)
			getEditEntity().setUser( new User() );
		else
			getEditEntity().setUser( null );
	}

	public List<Associate> getAssociateList() {
		return associateList;
	}

	public void setAssociateList( List<Associate> associateList ) {
		this.associateList = associateList;
	}

	public Tab getSearchTab() {
		return searchTab;
	}

	public void setSearchTab( Tab searchTab ) {
		this.searchTab = searchTab;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation( String passwordConfirmation ) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public boolean isHasUserAccount() {
		return hasUserAccount;
	}

	public void setHasUserAccount( boolean hasUserAccount ) {
		this.hasUserAccount = hasUserAccount;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList( List<User> userList ) {
		this.userList = userList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList( List<Role> roleList ) {
		this.roleList = roleList;
	}
	
}
