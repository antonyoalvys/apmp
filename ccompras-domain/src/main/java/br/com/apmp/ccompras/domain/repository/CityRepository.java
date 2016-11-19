package br.com.apmp.ccompras.domain.repository;

import br.com.apmp.ccompras.domain.entities.City;

public interface CityRepository extends BaseRepository<City> {
	
	public City findByName( String name);

}
