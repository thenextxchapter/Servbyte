package com.nony.servbyte.meal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MealRepositoryTest {

	@Autowired
	private MealRepository mealRepository;

	@Test
	void findByServiceProviderId() {
		List<Meal> meals = mealRepository.findByServiceProviderId(1);
		assertThat(meals).isNotNull();
	}
}
