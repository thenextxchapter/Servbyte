package com.nony.servbyte.serviceProvider.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantSearchParam {
	private String name;
	private String type;
	private Integer cityId;
}
