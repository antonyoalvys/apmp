package br.com.apmp.ccompras.beans.search;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.entities.City;
import br.com.apmp.ccompras.service.CityService;

@Named
@ViewScoped
public class CitySearchBean implements Serializable {

	private static final long serialVersionUID = -1332454590787023185L;

	@Inject
	private CityService cityService;

	private List<String> availableCitiesNames;
	private List<City> availableCities;

	public List<City> autocomplete( String query ) {
		query = query.trim();
		String stateName = (String) UIComponent.getCurrentComponent( FacesContext.getCurrentInstance() ).getAttributes().get( "stateName" );
		this.availableCities = cityService.autocomplete( query, stateName );
		return this.availableCities;
	}

	public List<String> autocompleteCitiesNames( String query ) {
		query = query.trim();
		String stateName = (String) UIComponent.getCurrentComponent( FacesContext.getCurrentInstance() ).getAttributes().get( "stateName" );
		this.availableCitiesNames = cityService.autocompleteCitiesNames( query, stateName );
		return this.availableCitiesNames;
	}

	public List<String> getAvailableCitiesNames() {
		return availableCitiesNames;
	}

	public void setAvailableCitiesNames( List<String> availableCitiesNames ) {
		this.availableCitiesNames = availableCitiesNames;
	}

	public List<City> getAvailableCities() {
		return availableCities;
	}

	public void setAvailableCities( List<City> availableCities ) {
		this.availableCities = availableCities;
	}

}
