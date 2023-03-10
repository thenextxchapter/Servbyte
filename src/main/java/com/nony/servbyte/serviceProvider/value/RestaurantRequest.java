package com.nony.servbyte.serviceProvider.value;

import com.nony.servbyte.serviceProvider.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {
	@NotBlank
	private String name;
	private Type type;
	private String email;
	private String phoneNumber;
/*
	private String picture;
	Will implement this later
*/
	private Integer cityId;
}
