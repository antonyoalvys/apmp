package br.com.apmp.ccompras.service.security;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.realm.Realm;

import br.com.apmp.ccompras.domain.repository.UserRepository;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
@TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
public class ApmpRealm implements Realm, Serializable {

	private static final long serialVersionUID = 6423629250091183799L;

	@Inject
	private UserRepository userRepository;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "ApmpRealm";
	}

	@Override
	public boolean supports( AuthenticationToken token ) {
		return true;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo( AuthenticationToken token ) throws AuthenticationException {

		String principal = (String) token.getPrincipal();

		String credencial = userRepository.findCredentials( principal );

		if ( credencial != null ) {
			AuthenticationInfo info = new SimpleAuthenticationInfo( principal, credencial, getName() );
			SimpleCredentialsMatcher cmatcher = new SimpleCredentialsMatcher();

			boolean credentialsMatch = cmatcher.doCredentialsMatch( token, info );
			if ( credentialsMatch ) {
				return info;
			}

		}
		throw new AuthenticationException();

	}

}
