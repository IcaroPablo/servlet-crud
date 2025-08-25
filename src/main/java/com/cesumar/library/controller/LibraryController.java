package com.cesumar.library.controller;

import com.cesumar.library.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/books")
public class LibraryController extends HttpServlet {

	private static final String BOOK_LIST = "bookList";
	private static final String REGISTRATION_ERROR = "registrationError";

	@Override
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		var books = Optional.ofNullable((List<Book>) getServletContext().getAttribute(BOOK_LIST))
						.orElseGet(ArrayList::new);

		if ("DELETE".equalsIgnoreCase(request.getParameter("_method"))) {
			var isbn = request.getParameter("isbn");

			books.removeIf(book -> String.valueOf(book.getIsbn()).equals(isbn));
			getServletContext().setAttribute(BOOK_LIST, books);
		} else {
			var title = request.getParameter("title");
			var author = request.getParameter("author");
			var year = request.getParameter("year");
			var isbn = request.getParameter("isbn");

			try {
				var newBook = new Book(title, author, year, isbn);

				books.add(newBook);
				getServletContext().setAttribute(BOOK_LIST, books);
			} catch (IllegalArgumentException e) {
				getServletContext().setAttribute(REGISTRATION_ERROR, e.getMessage());
			}
		}

		response.sendRedirect("books");
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		var books = Optional.ofNullable((List<Book>) getServletContext().getAttribute(BOOK_LIST))
				.orElseGet(ArrayList::new);

//		books.add(new Book("The Hobbit", "J.R.R. Tolkien", "1000", "9780547928227"));
//		books.add(new Book("1984", "George Orwell", "1000", "9780451524935"));
//		books.add(new Book("Clean Code", "Robert C. Martin", "1000",  "9780132350884"));

		request.setAttribute("books", books);
		request.setAttribute(REGISTRATION_ERROR, getServletContext().getAttribute(REGISTRATION_ERROR));
		request.getRequestDispatcher("/view/index.jsp").forward(request, response);
	}
}
