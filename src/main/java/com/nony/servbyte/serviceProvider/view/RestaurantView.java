package com.nony.servbyte.serviceProvider.view;

import com.blazebit.persistence.view.AttributeFilter;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.filter.ContainsIgnoreCaseFilter;
import com.nony.servbyte.serviceProvider.ServiceProvider;

@EntityView(ServiceProvider.class)
public interface RestaurantView {

	@IdMapping
	Integer id();
	@AttributeFilter(ContainsIgnoreCaseFilter.class)
	String getName();
	@AttributeFilter(ContainsIgnoreCaseFilter.class)
	String getEmail();
	String getPhoneNumber();
	String getType();
	City getCity();

	@EntityView(City.class)
	interface City {
		String getName();
	}
}
