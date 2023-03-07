package com.nony.servbyte.util;

import java.util.Collection;

public class Helper {
	public static boolean isNotBlank(String value) {
		return value != null && !value.isBlank();
	}

	public static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}

	public static boolean isNotNull(Object value) {
		return value != null;
	}

	public static boolean isNull(Object value) {
		return value == null;
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		return collection != null && !collection.isEmpty();
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
}
