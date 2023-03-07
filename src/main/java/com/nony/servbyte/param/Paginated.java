package com.nony.servbyte.param;

import java.util.Collection;

import lombok.Data;

@Data(staticConstructor = "of")
public class Paginated<T> {
	private final Long count;
	private final Integer pages;
	private final Collection<T> items;
}
