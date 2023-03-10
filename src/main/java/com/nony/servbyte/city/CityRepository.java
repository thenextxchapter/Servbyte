package com.nony.servbyte.city;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	City findByName(String name);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM City c WHERE c.name = :name")
	boolean existsByName(@Param("name") String name);

	@Query("SELECT c FROM City c WHERE c.id = :id")
	Optional<City> findOneOptional(@Param("id") Integer id);
}
