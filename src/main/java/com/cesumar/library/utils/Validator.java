package com.cesumar.library.utils;

public interface Validator {
	default void validateString(String string, String message) {
		if (string == null || string.trim().isEmpty())
			throw new IllegalArgumentException(message);
	}

	default void validateInteger(String number, String message) {
		validateString(number, message);

		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(message);
		}
	}

	default void validateLong(String number, String message) {
		validateString(number, message);

		try {
			Long.parseLong(number);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(message);
		}
	}
}
