package org.example.controller;

import java.util.ArrayList;

import org.example.entity.Author;
import org.example.service.AuthorService;

public class AuthorController {
    private AuthorService authorService;
    private static AuthorController authorController;

    private AuthorController() {
        authorService = AuthorService.getInstance();
    }

    public static AuthorController getInstance() {
        if(authorController == null) {
            authorController = new AuthorController();
        }
        return authorController;
    }

    public ArrayList<Author> getAuthors() {
        return authorService.getAuthors();
    }

    public Author getAuthor(String name) {
        return authorService.get(name);
    }

    public void deleteAuthor(String name) {
        authorService.delete(name);
    }

    public void addAuthor(String name) {
        authorService.add(name);
    }

    public void updateAuthor(String name, String newName) {
        authorService.update(name, newName);
    }
}
