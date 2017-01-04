package br.com.apmp.ccompras.jsf.security.configuration;

import javax.enterprise.inject.Produces;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;

public class ShiroConfiguration {

	@Produces
	public WebSecurityManager getSecurityManager() {
		DefaultWebSecurityManager securityManager = null;

		if ( securityManager == null ) {
			AuthorizingRealm realm = new SecurityRealm();
			CredentialsMatcher credentialsMatcher = new PasswordMatcher();
			( (PasswordMatcher) credentialsMatcher ).setPasswordService( new BCryptPasswordService() );

			realm.setCredentialsMatcher( credentialsMatcher );

			securityManager = new DefaultWebSecurityManager( realm );

			CacheManager cacheManager = new EhCacheManager();
			( (EhCacheManager) cacheManager ).setCacheManagerConfigFile( "classpath:ehcache.xml" );
			securityManager.setCacheManager( cacheManager );

			byte[] cypherKey = String.format( "0x%s", Hex.encodeToString( new AesCipherService().generateNewKey().getEncoded() ) ).getBytes();

			RememberMeManager rememberMeManager = new CookieRememberMeManager();
			( (CookieRememberMeManager) rememberMeManager ).setCipherKey( cypherKey );
			securityManager.setRememberMeManager( rememberMeManager );

		}

		return securityManager;

	}

	@Produces
	public FilterChainResolver getFilterChainResolver() {

		FilterChainResolver filterChainResolver = null;
		if ( filterChainResolver == null ) {
			FormAuthenticationFilter authc = new FormAuthenticationFilter();
			PermissionsAuthorizationFilter perms = new PermissionsAuthorizationFilter();
			AnonymousFilter anon = new AnonymousFilter();
			UserFilter user = new UserFilter();
			
			authc.setLoginUrl( "/login.xhtml" );
			perms.setLoginUrl( "/login.xhtml" );
			user.setLoginUrl( "/login.xhtml" );

			FilterChainManager fcMan = new DefaultFilterChainManager();
			fcMan.addFilter( "authc", authc );
			fcMan.addFilter( "anon", anon );
			fcMan.addFilter( "user", user );
			fcMan.addFilter( "perms", perms );

			fcMan.createChain( "/views/usuario/Cadastro.xhtml", "anon" );
			fcMan.createChain( "/*javax.faces.resource/**", "anon" );
			fcMan.createChain( "/login.xhtml", "authc" );
			
			//Associado
			fcMan.addToChain( "/views/associado/Cadastro.xhtml", "perms", "associado:cadastro" );
			fcMan.addToChain( "/views/associado/Associado.xhtml", "perms", "associado:associado" );
			
			//Desconto Convênio
			fcMan.addToChain( "/views/chequeCompra/Cadastro.xhtml", "perms", "chequeCompra:cadastro" );
			fcMan.addToChain( "/views/chequeCompra/ChequeCompra.xhtml", "perms", "chequeCompra:chequeCompra" );
			
			//Desconto Convênio
			fcMan.addToChain( "/views/descontoConvenio/Cadastro.xhtml", "perms", "descontoConvenio:cadastro" );
			fcMan.addToChain( "/views/descontoConvenio/DescontoConvenio.xhtml", "perms", "descontoConvenio:descontoConvenio" );
			
			//Fornecedor
			fcMan.addToChain( "/views/fornecedor/Cadastro.xhtml", "perms", "fornecedor:cadastro" );
			fcMan.addToChain( "/views/fornecedor/Fornecedor.xhtml", "perms", "fornecedor:fornecedor" );
			
			//Período
			fcMan.addToChain( "/views/periodo/Cadastro.xhtml", "perms", "periodo:cadastro" );
			fcMan.addToChain( "/views/periodo/Periodo.xhtml", "perms", "periodo:periodo" );
			
			//Convênio
			fcMan.addToChain( "/views/convenio/Cadastro.xhtml", "perms", "convenio:cadastro" );
			fcMan.addToChain( "/views/convenio/Convenio.xhtml", "perms", "convenio:convenio" );

			//Geração de arquivo
			fcMan.addToChain( "/views/arquivoconvenio/GerarArquivo.xhtml", "perms", "arquivoconvenio:gerarArquivo" );
			fcMan.addToChain( "/views/arquivoconvenio/Consulta.xhtml", "perms", "arquivoconvenio:consulta" );
			
	
			//Relatório
			fcMan.addToChain( "/views/relatorio/ChequeCompra.xhtml", "perms", "relatorio:chequeCompra" );
						
			
			fcMan.createChain( "/**", "user" );

			PathMatchingFilterChainResolver resolver = new PathMatchingFilterChainResolver();
			resolver.setFilterChainManager( fcMan );
			filterChainResolver = resolver;
			
		}
		return filterChainResolver;

	}
}
