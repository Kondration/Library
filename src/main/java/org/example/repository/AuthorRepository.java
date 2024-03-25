package org.example.repository;

import org.example.connect.Connector;
import org.example.service.AuthorService;

public class AuthorRepository {

    private Connector connector; 
    private AuthorService service;
    private static AuthorRepository authorRepository;

    private AuthorRepository() {
        connector = Connector.getInstance();
        service = AuthorService.getInstance();
    }

    public static AuthorRepository getInstance() {
        if(authorRepository == null) {
            authorRepository = new AuthorRepository();
        }
        return authorRepository;
    }

    public void createAndFillTable() {
        dropTable();
        createTable();
        fillTable();
    }

    private void dropTable() {
        String dropAuthors = "DROP TABLE IF EXISTS authors CASCADE";
        connector.execute(dropAuthors);
    }

    private void fillTable() {
        service.add("Stephen King");
        service.add("Howard Phillips Lovecraft");
        service.add("Bram Stocker");
        service.add("Yevgeny Zamyatin");
        service.add("Joanne Rowling");
        service.add("John Ronald Reuel Tolkien");
    }

    private void createTable() {
        String createAuthors = "CREATE TABLE authors (id SERIAL PRIMARY KEY, name CHAR(255))";
        connector.execute(createAuthors);
    }
}
