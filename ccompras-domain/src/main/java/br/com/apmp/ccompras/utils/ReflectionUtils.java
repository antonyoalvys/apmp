package br.com.apmp.ccompras.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReflectionUtils {

	private ReflectionUtils() {}

	public static Field findField( Class<?> klass, String name ) {
		return org.springframework.util.ReflectionUtils.findField( klass, name );
	}

	public static Object getFieldValue( String name, Object object ) {

		Field field = findField( object.getClass(), name );

		if ( field != null ) {
			org.springframework.util.ReflectionUtils.makeAccessible( field );
			Object value = org.springframework.util.ReflectionUtils.getField( field, object );
			return value;
		}

		return null;
	}

	/**
	 * Retrieving fields list of specified class If recursively is true,
	 * retrieving fields from all class hierarchy
	 * 
	 * @param clazz
	 *            where fields are searching
	 * @param recursively
	 *            param
	 * @return list of fields
	 */
	public static Field[] getDeclaredFields( Class clazz, boolean recursively ) {
		List<Field> fields = new LinkedList<Field>();
		Field[] declaredFields = clazz.getDeclaredFields();
		Collections.addAll( fields, declaredFields );

		Class superClass = clazz.getSuperclass();

		if ( superClass != null && recursively ) {
			Field[] declaredFieldsOfSuper = getDeclaredFields( superClass, recursively );
			if ( declaredFieldsOfSuper.length > 0 )
				Collections.addAll( fields, declaredFieldsOfSuper );
		}

		return fields.toArray( new Field[fields.size()] );
	}

	/**
	 * Retrieving fields list of specified class and which are annotated by
	 * incoming annotation class If recursively is true, retrieving fields from
	 * all class hierarchy
	 * 
	 * @param clazz
	 *            - where fields are searching
	 * @param annotationClass
	 *            - specified annotation class
	 * @param recursively
	 *            param
	 * @return list of annotated fields
	 */
	public static Field[] getAnnotatedFields( Class clazz, Class<? extends Annotation> annotationClass, boolean recursively ) {
		Field[] allFields = getDeclaredFields( clazz, recursively );
		List<Field> annotatedFields = new LinkedList<Field>();

		for ( Field field : allFields ) {
			if ( field.isAnnotationPresent( annotationClass ) )
				annotatedFields.add( field );
		}

		return annotatedFields.toArray( new Field[annotatedFields.size()] );
	}
}
