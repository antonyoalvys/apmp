//package br.com.apmp.ccompras.converters;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.metamodel.EntityType;
//import javax.persistence.metamodel.SingularAttribute;
//
//import jodd.bean.BeanUtil;
//
//import org.springframework.util.ReflectionUtils;
//
//import br.com.apmp.ccompras.utils.Utils;
//
//@FacesConverter( value = "autocompleteConverter" )
//public class AutocompleteConverter implements Converter {
//	
//	@PersistenceContext
//	private EntityManager em;
//
//	private static final String ATTRIBUTE_CLASS = "AutocompleteConverter.class";
//
//	@Override
//	public Object getAsObject( FacesContext arg0, UIComponent component, String value ) {
//		if ( value == null || "null".equals( value ) || value.trim().isEmpty() )
//			return null;
//
//		Object newInstance = null;
//		Class<?> klass = null;
//		klass = (Class<?>) component.getAttributes().get( ATTRIBUTE_CLASS );
//
//		if ( klass == null && component.getAttributes().containsKey( "className" ) )
//			try {
//				klass = Class.forName( component.getAttributes().get( "className" ).toString() );
//			} catch ( ClassNotFoundException e ) {
////				e.printStackTrace();
//			}
//
//		if ( klass == null )
//			return null;
//
//		if ( !component.getAttributes().containsKey( "fieldName" ) ) {
////			EntityManager manager = SpringContextProvider.getContext().getBean( EntityManager.class );
//			EntityType<?> entityType = em.getMetamodel().entity( klass );
//			SingularAttribute<?, ?> singularAttribute = entityType.getId( entityType.getIdType().getJavaType() );
//
//			Class<?> idType = ( (Field) singularAttribute.getJavaMember() ).getType();
//			Object id = null;
//			try {
//				if ( Long.class.isAssignableFrom( idType ) || Long.TYPE.isAssignableFrom( idType ) ) {
//					id = Long.valueOf( value );
//				} else if ( Integer.class.isAssignableFrom( idType ) || Integer.TYPE.isAssignableFrom( idType ) ) {
//					id = Integer.valueOf( value );
//				}
//			} catch ( NumberFormatException e ) {
////				e.printStackTrace();
//				return null;
//			}
//			newInstance = em.find( klass, id );
//		} 
//		
//		return newInstance;
//	}
//
//	@Override
//	public String getAsString( FacesContext arg0, UIComponent component, Object value ) {
//		String result = null;
//
//		if ( value != null ) {
//			Class<?> entity = Utils.getEntityClass( value.getClass() );
//			component.getAttributes().put( ATTRIBUTE_CLASS, entity );
//
//			String fieldName = (String) component.getAttributes().get( "fieldName" );
//			if ( fieldName != null ) {
//				Object propertyValue = BeanUtil.getProperty( value, fieldName );
//				if ( propertyValue != null )
//					result = String.valueOf( propertyValue );
//			} else {
////				EntityManager manager = SpringContextProvider.getContext().getBean( EntityManager.class );
//				EntityType<?> entityType = em.getMetamodel().entity( entity );
//				SingularAttribute<?, ?> singularAttribute = entityType.getId( entityType.getIdType().getJavaType() );
//				
//				Method method = ReflectionUtils.findMethod( entity, "get"+singularAttribute.getName().toUpperCase().charAt( 0 ) + singularAttribute.getName().substring( 1 ) );
//
//				ReflectionUtils.makeAccessible( method );
//				Object idValue = ReflectionUtils.invokeMethod( method, value );
//				if ( idValue != null )
//					result = String.valueOf( idValue );
//			}
//		}
//		return result;
//	}
//
//}