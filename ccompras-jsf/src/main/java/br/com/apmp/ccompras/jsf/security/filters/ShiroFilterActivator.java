package br.com.apmp.ccompras.jsf.security.filters;

import javax.servlet.annotation.WebFilter;

import org.apache.shiro.web.servlet.ShiroFilter;

@WebFilter( "/*" )
public class ShiroFilterActivator extends ShiroFilter {

	private ShiroFilterActivator() {

	}

}
