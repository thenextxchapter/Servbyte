package com.nony.servbyte.serviceProvider.meal.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealSearchParam {
	private String name;
	private double price;
	private Integer prepTime;
}
