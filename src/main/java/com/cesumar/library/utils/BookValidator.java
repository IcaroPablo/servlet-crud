package com.cesumar.library.utils;

public interface BookValidator extends Validator {
	default void validateISBN(String isbn, String message) {
		validateString(isbn, message);

		var sanitizedIsbn = isbn.replace("-", "").replace(" ", "");

		if (sanitizedIsbn.length() != 10 && sanitizedIsbn.length() != 13)
			throw new IllegalArgumentException(message);

		validateLong(sanitizedIsbn, message);
	}

	default void validateYear(String year, String message) {
		validateString( year, message );
		validateInteger( year, message );

		if (Integer.parseInt( year ) < 0)
			throw new IllegalArgumentException( message );
	}
}
