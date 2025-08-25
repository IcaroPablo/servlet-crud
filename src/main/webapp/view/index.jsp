<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.cesumar.library.model.Book" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
  <h2>Cadastrar novo livro</h2>
  <form method="post" action="books">
    <label for="title">Título:</label>
    <input type="text" id="title" name="title" required><br><br>

    <label for="author">Autor:</label>
    <input type="text" id="author" name="author" required><br><br>

    <label for="year">Ano de publicação:</label>
    <input type="text" id="year" name="year" required><br><br>

    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn" required><br><br>

    <button type="submit">Cadastrar</button>

    <%
        var errorMessage = (String) request.getAttribute("registrationError");
        if (errorMessage != null) {
    %>
            <div style="color: red; font-weight: bold;">
                Erro no cadastro anterior: <%= errorMessage %>
            </div>
    <%
        }
    %>
  </form>

  <hr>

  <h2>Remover livro cadastrado</h2>
  <form method="post" action="books">
    <input type="hidden" name="_method" value="DELETE">
    <label for="isbn">ISBN:</label>
    <input type="text" id="isbndelete" name="isbn" required><br><br>

    <button type="submit">Remover</button>
  </form>

  <hr>

  <h2>Livros cadastrados</h2>
  <ul>
    <%
        var books = (List<Book>) request.getAttribute("books");
        if (books == null) {
    %>
            <li>No books registered yet.</li>
    <%
        } else {
            for (Book book : books) {
    %>
                <li>
                    <strong>Título:</strong> <%= book.getTitle() %><br>
                    <strong>Autor:</strong> <%= book.getAuthor() %><br>
                    <strong>Ano de publicação:</strong> <%= book.getYear() %><br>
                    <strong>ISBN:</strong> <%= book.getIsbn() %>
                </li><br>
    <%
            }
        }
    %>
  </ul>
</body>
</html>
