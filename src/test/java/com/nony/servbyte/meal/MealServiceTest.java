package com.nony.servbyte.meal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.nony.servbyte.exception.MealNotFoundException;
import com.nony.servbyte.exception.RestaurantNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {
	@Mock
	private MealRepository mealRepo;

	@InjectMocks
	private MealService mealService;

	@Test
	void findByServiceProvider_ShouldReturnMeals_WhenGivenValidServiceProvider()
			throws MealNotFoundException, RestaurantNotFoundException {
		Integer serviceProviderId = 1;
		List<Meal> expectedMeals = Arrays.asList(new Meal(), new Meal());

		when(mealRepo.findByServiceProviderId(serviceProviderId)).thenReturn(expectedMeals);

		List<Meal> actualMeals = mealService.findByServiceProvider(serviceProviderId);

		assertEquals(expectedMeals, actualMeals);
	}

	@Test
	void findByServiceProvider_shouldThrowMealNotFoundException_whenNoMealsFoundForGivenServiceProviderId() {
		Integer serviceProviderId = 1;

		when(mealRepo.findByServiceProviderId(1))
				.thenReturn(Collections.emptyList());

		assertThrows(MealNotFoundException.class,
				() -> mealService.findByServiceProvider(serviceProviderId));
	}

	@Test
	void findByServiceProvider_shouldThrowRestaurantNotFoundException_whenServiceProviderIdIsNotValid() {
		when(mealRepo.findByServiceProviderId(0))
				.thenThrow(NoSuchElementException.class);

		assertThatThrownBy(() ->
				mealService.findByServiceProvider(0))
				.isInstanceOf(RestaurantNotFoundException.class);
	}

	@Test
	void findById_ShouldReturnMeal_WhenGivenValidId() throws MealNotFoundException {
		Integer id = 1;
		Meal expectedMeal = new Meal();

		when(mealRepo.findById(id)).thenReturn(Optional.of(expectedMeal));

		Meal actualMeal = mealService.findById(id);

		assertEquals(expectedMeal, actualMeal);
	}

	@Test
	void findById_ShouldThrowMealNotFoundException_WhenMealNotFoundForGivenId() {
		when(mealRepo.findById(1)).thenReturn(Optional.empty());

		assertThatThrownBy(() ->
				mealService.findById(1))
				.isInstanceOf(MealNotFoundException.class);
	}

	@Test
	void saveMeal_shouldReturnSavedMeal_whenGivenValidMeal() {
		Meal meal = new Meal();
		Meal expectedSavedMeal = new Meal();

		when(mealRepo.save(meal)).thenReturn(expectedSavedMeal);

		Meal actualMeal = mealService.saveMeal(meal);

		assertEquals(expectedSavedMeal, actualMeal);
	}
}
