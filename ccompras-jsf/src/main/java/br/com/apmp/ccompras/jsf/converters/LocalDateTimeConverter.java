package br.com.apmp.ccompras.jsf.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

	private static final String pattern = "dd/MM/yyyy HH:mm:ss";

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		String lPattern = (String) uiComponent.getAttributes().get("pattern");
		lPattern = lPattern == null ? pattern : lPattern;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(lPattern);
		
		return LocalDateTime.parse(value, formatter);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		String lPattern = (String) uiComponent.getAttributes().get("pattern");
		lPattern = lPattern == null ? pattern : lPattern;
		LocalDateTime dateTimeValue = (LocalDateTime) value;

		return dateTimeValue.format(DateTimeFormatter.ofPattern(lPattern));
	}

}
