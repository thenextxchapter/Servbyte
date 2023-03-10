package com.nony.servbyte.serviceProvider.view;

import com.blazebit.persistence.view.AttributeFilter;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.filter.ContainsIgnoreCaseFilter;
import com.nony.servbyte.city.City;
import com.nony.servbyte.serviceProvider.ServiceProvider;

@EntityView(ServiceProvider.class)
public interface RestaurantView {

	@IdMapping
	Integer getId();
	@AttributeFilter(ContainsIgnoreCaseFilter.class)
	String getName();
	@AttributeFilter(ContainsIgnoreCaseFilter.class)
	String getEmail();
	String getPhoneNumber();
	String getType();
	CityView getCity();

	@EntityView(City.class)
	interface CityView {
		String getName();
	}
}
