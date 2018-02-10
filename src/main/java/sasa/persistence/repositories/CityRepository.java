package sasa.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sasa.persistence.entities.City;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query("from City c where c.name=:name")
    City findByName(@Param("name")String name);

}
