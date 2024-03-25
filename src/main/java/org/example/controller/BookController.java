package org.example.controller;

import java.util.ArrayList;
import org.example.entity.Book;
import org.example.service.BookService;

public class BookController {
    private BookService booksService;
    private static BookController bookController;

    private BookController() {
        booksService = BookService.getInstance();
    }

    public static BookController getInstance() {
        if(bookController == null) {
            bookController = new BookController();
        }
        return bookController;
    }

    public ArrayList<Book> getBooks() {
        return booksService.getBooks();
    }

    public ArrayList<Book> getAuthorBooks(int authorId) {
        return booksService.getAuthorBooks(authorId);
    }

    public void deleteBook(String bookName) {
        booksService.deleteBook(bookName);
    }

    public void addBook(String bookName, String authorName) {
        booksService.addBook(bookName, authorName);
    }

    public void updateBookName(String bookName, String newBookName) {
        booksService.updateBookName(bookName, newBookName);
    }
}
