package com.nony.servbyte.meal;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.nony.servbyte.exception.MealNotFoundException;
import com.nony.servbyte.exception.RestaurantNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MealService {

	private final MealRepository mealRepo;

	public MealService(MealRepository mealRepo) {
		this.mealRepo = mealRepo;
	}

	public List<Meal> findByServiceProvider(Integer serviceProvider_id)
			throws RestaurantNotFoundException, MealNotFoundException {
		try {
			List<Meal> meals = mealRepo.findByServiceProviderId(serviceProvider_id);

			if (meals.isEmpty()) {
				throw new MealNotFoundException();
			}
			return meals;
		} catch (NoSuchElementException exception) {
			throw new RestaurantNotFoundException();
		}

	}

	public Meal findById(Integer id) throws MealNotFoundException {
		try {
			return mealRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new MealNotFoundException();
		}
	}

	public Meal saveMeal(Meal meal) {
		return mealRepo.save(meal);
	}
}
