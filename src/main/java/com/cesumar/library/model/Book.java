package com.cesumar.library.model;

import com.cesumar.library.utils.BookValidator;

public class Book implements BookValidator {
	private long isbn;
	private String title;
	private String author;
	private int year;

	public Book(String title, String author, String year, String isbn) {
		setTitle(title);
		setAuthor(author);
		setYear(year);
		setIsbn(isbn);
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		validateISBN(isbn, "ISBN inválido");

		this.isbn = Long.parseLong(isbn);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		validateString(title, "Título não pode ser nulo ou vazio");

		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		validateString(title, "Autor não pode ser nulo ou vazio");

		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(String year) {
		validateYear(year, "Ano deve ser um número válido");

		this.year = Integer.parseInt(year);
	}
}
