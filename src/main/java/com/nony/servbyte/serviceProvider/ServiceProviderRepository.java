package com.nony.servbyte.serviceProvider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {
	List<ServiceProvider> findByCityId(Integer cityId);
	List<ServiceProvider> findByNameContainingIgnoreCase(String name);
	List<ServiceProvider> findByType(Type type);
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM ServiceProvider s WHERE s.name = :name")
	boolean existsByName(@Param("name") String name);
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END from ServiceProvider  s WHERE s.name = :name AND s.id != :id")
	boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Integer id);
}
