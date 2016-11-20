package br.com.apmp.ccompras.utils;

import org.hibernate.proxy.HibernateProxy;

public class Utils {
	
	@SuppressWarnings( "unchecked" )
	public static <E> Class<E> getEntityClass( Class<E> klass ) {
		if ( HibernateProxy.class.isAssignableFrom( klass ) ) {
			return (Class<E>) klass.getSuperclass();
		}
		return klass;
	}

}
