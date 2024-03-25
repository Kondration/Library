package org.example.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.example.entity.Author;
import org.example.entity.Book;

public class Connector {

    private static Connector connector;
    private static Connection connection;
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String password = "postgres";
    private static final String user = "postgres";
    private static final String driver = "org.postgresql.Driver";

    private Connector() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connector getInstance() {
        if(connector == null) {
            connector = new Connector();
        }
        return connector;
    }

    public void execute(String sql) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeAuthor(String sql, String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeBook(String sql, String name, int authorId) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, authorId);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Author getAuthor(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors WHERE name = '" + name + "'");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            return Author.builder().id(id).name(name).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Author> getAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors");
            ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                authors.add(Author.builder().id(id).name(name).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 int id = resultSet.getInt("id");
                 String name = resultSet.getString("name").trim();
                 int authorId = resultSet.getInt("author_id");
                books.add(Book.builder().id(id).name(name).autor_id(authorId).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public ArrayList<Book> getBooks(int authorId) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE author_id = " + authorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                books.add(Book.builder().id(id).name(name).autor_id(authorId).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }
}
