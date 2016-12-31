package br.com.apmp.ccompras.jsf.security.configuration;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import br.com.apmp.ccompras.domain.entities.Role;
import br.com.apmp.ccompras.domain.entities.User;
import br.com.apmp.ccompras.service.UserService;

public class SecurityRealm extends AuthorizingRealm implements Serializable {

	private static final long serialVersionUID = 6423629250091183799L;

	private Logger logger;

	private UserService userService;

	public SecurityRealm() {
		super();
		this.logger = Logger.getLogger( SecurityRealm.class.getName() );

		try {
			InitialContext ctx = new InitialContext();
			String moduleName = (String) ctx.lookup( "java:module/ModuleName" );
			this.userService = (UserService) ctx.lookup( String.format( "java:global/%s/UserServiceImpl", moduleName ) );
		} catch ( NamingException ex ) {
			logger.warning( "Cannot do the JNDI Lookup to instantiate the User service : " + ex );

		}

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken token ) throws AuthenticationException {

		// Identify account to log to
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		String username = userPassToken.getUsername();

		if ( username == null ) {
			logger.warning( "Username is null." );
			return null;
		}

		// Read the user from DB
		User user = this.userService.findByUsername( username );
		if ( user == null ) {
			logger.warning( "No account found for user [" + username + "]" );
			throw new IncorrectCredentialsException();
		}

		return new SimpleAuthenticationInfo( username, user.getPassword(), getName() );

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals ) {

		// null usernames are invalid
		if ( principals == null ) {
			throw new AuthorizationException( "PrincipalCollection method argument cannot be null." );
		}

		String username = (String) getAvailablePrincipal( principals );
		Set<String> roleNames = new HashSet<>();
		roleNames.add( this.userService.findByUsername( username ).getRole().getName() );
		AuthorizationInfo info = new SimpleAuthorizationInfo( roleNames );

		/**
		 * 
		 * If you want to do Permission Based authorization, you can grab the
		 * Permissions List associated to your user:
		 */

		Role role = this.userService.findByUsername( username ).getRole();
		Set<String> permissions = new HashSet<String>();

		if ( role != null )
			permissions.addAll( role.permissionsToSet() );

		( (SimpleAuthorizationInfo) info ).setStringPermissions( permissions );

		return info;
	}

}
