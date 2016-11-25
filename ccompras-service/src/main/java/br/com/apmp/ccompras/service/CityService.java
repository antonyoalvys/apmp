package br.com.apmp.ccompras.service;

import java.io.Serializable;
import java.util.List;

import br.com.apmp.ccompras.domain.entities.City;

public interface CityService extends Serializable {

	public City findById( Long id );

	public List<City> findAll();

	public List<City> autocomplete( String query, String stateName );

	public List<String> autocompleteCitiesNames( String query, String stateName );

}
