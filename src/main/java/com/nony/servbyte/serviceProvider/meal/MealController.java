package com.nony.servbyte.serviceProvider.meal;

import com.nony.servbyte.param.Paginated;
import com.nony.servbyte.serviceProvider.ServiceProviderService;
import com.nony.servbyte.serviceProvider.meal.param.MealSearchParam;
import com.nony.servbyte.serviceProvider.meal.value.MealRequest;
import com.nony.servbyte.serviceProvider.meal.view.MealView;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Service Provider Meals")
@RequestMapping(value = "/service-provider/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {

	private final MealService mealService;

	@Autowired
	public MealController(MealService mealService) {
		this.mealService = mealService;
	}

	@GetMapping(path = "{id}/menu")
	public ResponseEntity<Paginated<MealView>> meals(
			@PathVariable("id") Integer id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "price", required = false) Double price,
			@RequestParam(value = "prepTime", required = false) Integer prepTime,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size) {
		MealSearchParam searchParam = MealSearchParam.builder()
				.name(name)
				.price(price)
				.prepTime(prepTime)
				.build();

		Paginated<MealView> meals = mealService.listMeals(searchParam, id, page, size);
		return ResponseEntity.ok(meals);
	}

	@PostMapping(path = "{id}/menu")
	public ResponseEntity<MealView> createMeal(@PathVariable("id") Integer id, @RequestBody @Valid MealRequest mealRequest) {
		MealView meal = mealService.createMeal(id, mealRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(meal);
	}

	@PutMapping(path = "{id}/menu/{mealId}")
	public ResponseEntity<Meal> updateMeal(@PathVariable("id") Integer id, @PathVariable("mealId") Integer mealId, @RequestBody @Valid MealRequest mealRequest) {
		Meal meal = mealService.updateMeal(mealId, id, mealRequest);
		return ResponseEntity.ok(meal);
	}

	@DeleteMapping(path = "{id}/menu/{mealId}")
	public ResponseEntity<Void> deleteMeal(@PathVariable("id") Integer id, @PathVariable("mealId") Integer mealId) {
		mealService.deleteMeal(mealId, id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "{id}/menu/{mealId}")
	public ResponseEntity<MealView> getMeal(@PathVariable("id") Integer id, @PathVariable("mealId") Integer mealId) {
		MealView meal = (MealView) mealService.getMealById(mealId, id);
		return ResponseEntity.ok(meal);
	}
}
