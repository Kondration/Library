package org.example.service;

import java.util.ArrayList;
import org.example.connect.Connector;
import org.example.entity.Author;


public class AuthorService {
    private Connector connector;
    private static AuthorService authorService;

    private AuthorService() {
        connector = Connector.getInstance();
    }

    public static AuthorService getInstance() {
        if(authorService == null) {
            authorService = new AuthorService();
        }
        return authorService;
    }

    public void add(String name) {
        String add = "INSERT INTO authors (name) VALUES ('" + name + "')";
        connector.execute(add);
    }

    public Author get(String name) {
        return connector.get(name);
    }

    public void delete(String name) {
        String deleteAuthor = "DELETE FROM authors WHERE name = '" + name + "'";
        connector.execute(deleteAuthor);
    }

    public ArrayList<Author> getAuthors() {
        return connector.getAuthors();
    }

    public void update(String name, String newName) {
        String update = "UPDATE authors SET name = '" + newName + "' WHERE name = '" + name + "'";
        connector.execute(update);
    }
}
