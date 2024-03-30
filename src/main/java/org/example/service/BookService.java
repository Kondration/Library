package org.example.service;

import java.util.ArrayList;

import org.example.connect.Connector;
import org.example.entity.Author;
import org.example.entity.Book;

public class BookService {

    private Connector connector;
    private static BookService booksService;

    private BookService() {
        connector = Connector.getInstance();
    }

    public static BookService getInstance() {
        if(booksService == null) {
            booksService = new BookService();
        }
        return booksService;
    }

    public void addBook(String name, String authorName) {
        Author author = connector.get(authorName);
        int authorId = author.getId();
        String add = "INSERT INTO books (name, author_id) VALUES ('" + name + "'," + authorId + ")";
        connector.execute(add);
    }

    public ArrayList<Book> getBooks() {
        return connector.getBooks();
    }

    public ArrayList<Book> getAuthorBooks(int authorId) {
        return connector.getBooks(authorId);
    }

    public void deleteBook(String bookName) {
        String delete = "DELETE FROM books WHERE name = '" + bookName + "'";
        connector.execute(delete);        
    }

    public void updateBookName(String bookName, String newBookName) {
        String update = "UPDATE books SET name = '" + newBookName + "' WHERE name = '" + bookName + "'";
        connector.execute(update);
    }
}
