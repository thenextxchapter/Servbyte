package com.nony.servbyte.serviceProvider.meal.view;

import java.util.UUID;

import com.blazebit.persistence.view.AttributeFilter;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.filter.ContainsIgnoreCaseFilter;
import com.blazebit.persistence.view.filter.EqualFilter;
import com.nony.servbyte.serviceProvider.meal.Meal;
import com.nony.servbyte.serviceProvider.ServiceProvider;

@EntityView(Meal.class)
public interface MealView {

	@IdMapping
	Integer getId();
	@AttributeFilter(ContainsIgnoreCaseFilter.class)
	String getName();
	String getDescription();
	String getPicture();
	Double getPrice();
	Integer getPrepTime();
	RestaurantView getServiceProvider();

	@EntityView(ServiceProvider.class)
	interface RestaurantView {
		@AttributeFilter(EqualFilter.class)
		UUID getId();
		String getName();
	}

}
