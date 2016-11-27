package br.com.apmp.ccompras.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("localDateConverter")
public class LocalDateConverter implements Converter {

	private static final String pattern = "dd/MM/yyyy";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String lPattern = (String) component.getAttributes().get("pattern");
		lPattern = lPattern == null ? pattern : lPattern;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(lPattern);

		return LocalDate.parse(value, formatter);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String lPattern = (String) component.getAttributes().get("pattern");
		lPattern = lPattern == null ? pattern : lPattern;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(lPattern);
		LocalDate dateValue = (LocalDate) value;
		
		return dateValue.format(formatter);
	}
	
	
}
