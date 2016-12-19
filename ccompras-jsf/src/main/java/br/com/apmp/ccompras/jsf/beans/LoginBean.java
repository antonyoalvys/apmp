package br.com.apmp.ccompras.jsf.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.apmp.ccompras.domain.entities.User;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -5046496166307344462L;

	private User user;

	public void login() throws IOException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UsernamePasswordToken token = new UsernamePasswordToken( user.getUsername(), user.getPassword() );
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login( token );
			ctx.getExternalContext().redirect( "/views/index.xhtml" );
		} catch ( AuthenticationException ae ) {

			ctx.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_WARN, "Usuário/senha inválido(s)!", "Usuário/senha inválido(s)!" ) );
		}

	}

	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}

	public User getUser() {
		return user;
	}

	public void setUser( User user ) {
		this.user = user;
	}

}
