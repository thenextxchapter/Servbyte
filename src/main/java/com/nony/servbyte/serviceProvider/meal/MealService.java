package com.nony.servbyte.serviceProvider.meal;

import static com.nony.servbyte.util.Helper.isNotNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.PaginatedCriteriaBuilder;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.nony.servbyte.exception.BadRequestException;
import com.nony.servbyte.param.Paginated;
import com.nony.servbyte.serviceProvider.ServiceProvider;
import com.nony.servbyte.serviceProvider.ServiceProviderRepository;
import com.nony.servbyte.serviceProvider.ServiceProviderService;
import com.nony.servbyte.serviceProvider.meal.param.MealSearchParam;
import com.nony.servbyte.serviceProvider.meal.value.MealRequest;
import com.nony.servbyte.serviceProvider.meal.view.MealView;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class MealService {

	private final MealRepository mealRepo;
	private final ServiceProviderRepository serviceProvideRepo;
	private final EntityManager em;
	private final CriteriaBuilderFactory cbf;
	private final EntityViewManager evm;
	private final ServiceProviderService serviceProviderService;

	public MealService(MealRepository mealRepo, ServiceProviderRepository serviceProvideRepo, EntityManager em,
			CriteriaBuilderFactory cbf, EntityViewManager evm, ServiceProviderService serviceProviderService) {
		this.mealRepo = mealRepo;
		this.serviceProvideRepo = serviceProvideRepo;
		this.em = em;
		this.cbf = cbf;
		this.evm = evm;
		this.serviceProviderService = serviceProviderService;
	}

	public Paginated<MealView> listMeals(MealSearchParam searchParam, Integer id, int page, int size) {
		ServiceProvider serviceProvider = serviceProviderService.getServiceProviderById(id);

		EntityViewSetting<MealView, PaginatedCriteriaBuilder<MealView>> setting =
				EntityViewSetting.create(MealView.class, page, size);

		CriteriaBuilder<Meal> cb = cbf.create(em, Meal.class);
		cb.orderByAsc("name").orderByDesc("id");
		cb.where("serviceProvider").eq(serviceProvider);

		if (isNotNull(searchParam.getName()))
			setting.addAttributeFilter("name", searchParam.getName());
		if (isNotEmpty(searchParam.getPrice()))
			setting.addAttributeFilter("price", searchParam.getPrice());
		if (isNotNull(searchParam.getPrepTime()))
			setting.addAttributeFilter("prepTime", searchParam.getPrepTime());

		PaginatedCriteriaBuilder<MealView> pcb = evm.applySetting(setting, cb);
		pcb.withCountQuery(true);
		PagedList<MealView> result = pcb.getResultList();
		return Paginated.of(result.getTotalSize(), result.getTotalPages(), result);
	}

	public MealView createMeal(Integer id, MealRequest request) {
		ServiceProvider restaurant = serviceProviderService.getServiceProviderById(id);

		if (mealRepo.existsByNameAndServiceProviderId(request.getName(), restaurant.getId()))
			throw new BadRequestException("Meal already exists");

		Meal meal = Meal.builder()
				.name(request.getName())
				.description(request.getDescription())
				.price(request.getPrice())
				.prepTime(request.getPrepTime())
				.serviceProvider(restaurant)
				.build();
		Meal persistedMeal = mealRepo.save(meal);

		return evm.find(em, MealView.class, persistedMeal.getId());
	}

	public Meal updateMeal(Integer mealId, Integer restaurantId, MealRequest request) {
		Meal meal = mealRepo.findByIdAndServiceProviderId(mealId, restaurantId)
				.orElseThrow(() -> new BadRequestException("Meal not found"));

		ServiceProvider restaurant = serviceProviderService.getServiceProviderById(restaurantId);

		if (mealRepo.existsByNameAndServiceProviderId(request.getName(), restaurant.getId()))
			throw new BadRequestException("Meal already exists");

		meal.setName(request.getName());
		meal.setDescription(request.getDescription());
		meal.setPrice(request.getPrice());
		meal.setPrepTime(request.getPrepTime());
		meal.setServiceProvider(restaurant);

		return mealRepo.save(meal);
	}

	public void deleteMeal(Integer mealId, Integer restaurantId) {
		Meal meal = mealRepo.findByIdAndServiceProviderId(mealId, restaurantId)
				.orElseThrow(() -> new BadRequestException("Meal not found"));
		mealRepo.delete(meal);
	}

	public Meal getMealById(Integer mealId, Integer restaurantId) {
		return mealRepo.findByIdAndServiceProviderId(mealId, restaurantId)
				.orElseThrow(() -> new BadRequestException("Meal not found"));
	}

}
