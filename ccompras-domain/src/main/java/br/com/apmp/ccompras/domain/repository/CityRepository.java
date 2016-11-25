package br.com.apmp.ccompras.domain.repository;

import java.util.List;

import br.com.apmp.ccompras.domain.entities.City;

public interface CityRepository extends BaseRepository<City> {
	
	public List<City> autocomplete( String query, String stateName );

	public List<String> autocompleteCitiesNames( String query, String stateName );

}
