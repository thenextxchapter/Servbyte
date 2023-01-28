package com.nony.servbyte.serviceProvider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {
	List<ServiceProvider> findByCityId(Integer cityId);
	List<ServiceProvider> findByNameContainingIgnoreCase(String name);
	List<ServiceProvider> findByType(Type type);
}
