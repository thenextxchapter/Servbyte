package com.nony.servbyte.serviceProvider.meal.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealRequest {
	private String name;
	private String description;
	private Double price;
	private Integer prepTime;
	private Integer serviceProviderId;
}
