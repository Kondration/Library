package org.example.repository;

import org.example.connect.Connector;
import org.example.service.BookService;

public class BookRepository {
    
    private Connector connector;
    private static BookRepository booksRepository;
    private BookService booksService;

    private BookRepository() {
        connector = Connector.getInstance(); 
        booksService = BookService.getInstance();
    }

    public static BookRepository getInstance() {
        if(booksRepository == null) {
            booksRepository = new BookRepository();
        }
        return booksRepository;
    }

    public void createAndFillTable() {
        dropTables();
        createTables();
        fillTable();
    }

    private void fillTable() {
        booksService.addBook("It", "Stephen King");
        booksService.addBook("Carry", "Stephen King");
        booksService.addBook("The Green Mile", "Stephen King");
        booksService.addBook("We", "Yevgeny Zamyatin");
        booksService.addBook("Dracula", "Bram Stocker");
        booksService.addBook("Harry Potter and the Philosopher's Stone", "Joanne Rowling");
        booksService.addBook("Harry Potter and the Chamber of Secrets", "Joanne Rowling");
        booksService.addBook("Harry Potter and the Prisoner of Azkaban", "Joanne Rowling");
        booksService.addBook("The Alchemist", "Howard Phillips Lovecraft");
        booksService.addBook("Dagon", "Howard Phillips Lovecraft");
        booksService.addBook("The Hobbit", "John Ronald Reuel Tolkien");
        booksService.addBook("The Lord of the Rings", "John Ronald Reuel Tolkien");
    }

    private void createTables() {
        dropTables();
        String createBooks = "CREATE TABLE IF NOT EXISTS books (id SERIAL PRIMARY KEY, name CHAR(255)," + 
        "author_id INTEGER, FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE)";
        connector.execute(createBooks);
    }

    private void dropTables() {
        String dropBooks = "DROP TABLE IF EXISTS books CASCADE";
        connector.execute(dropBooks);
    }
}
