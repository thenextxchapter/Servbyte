package com.nony.servbyte.meal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {
	List<Meal> findByServiceProviderId(Integer serviceProvider_id);
}
