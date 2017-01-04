package br.com.apmp.ccompras.jsf.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.service.UserService;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -5046496166307344462L;

	@Inject
	private UserService userService;
	private User currentUser;
	private String password;
	private String username;

	@PostConstruct
	public void init() {}

	public void login() throws IOException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UsernamePasswordToken token = new UsernamePasswordToken( this.username, this.password );
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login( token );
			this.currentUser = userService.findByUsername( (String) subject.getPrincipal() );
			ctx.getExternalContext().redirect( ctx.getExternalContext().getApplicationContextPath() + "/views/index.xhtml?faces-redirect=true" );
		} catch ( AuthenticationException ae ) {

			ctx.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_WARN, "Usuário/senha inválido(s)!", "Usuário/senha inválido(s)!" ) );
		}

	}

	public void logout() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Subject subject = SecurityUtils.getSubject();
		try {
			if ( subject.isAuthenticated() ) {
				subject.logout();
				this.currentUser = null;
				ctx.getExternalContext().redirect( ctx.getExternalContext().getApplicationContextPath() + "/login.xhtml?faces-redirect=true" );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser( User currentUser ) {
		this.currentUser = currentUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername( String username ) {
		this.username = username;
	}

}
