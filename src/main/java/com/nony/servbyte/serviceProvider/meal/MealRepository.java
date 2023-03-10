package com.nony.servbyte.serviceProvider.meal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {
	List<Meal> findByServiceProviderId(Integer serviceProvider_id);

	@Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Meal m WHERE m.name = :name AND m.serviceProvider.id = :serviceProvider_id")
	boolean existsByNameAndServiceProviderId(@Param("name") String name, @Param("serviceProviderId") Integer serviceProvider_id);

	@Query("SELECT m FROM Meal m WHERE m.id = :id AND m.serviceProvider.id = :serviceProvider_id")
	Optional<Meal> findByIdAndServiceProviderId(Integer id, Integer serviceProvider_id);
}
