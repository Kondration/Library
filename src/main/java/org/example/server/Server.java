package org.example.server;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;

import com.sun.net.httpserver.*;

public class Server {
    @SuppressWarnings("restriction")
    private HttpServer server;
    private static final String PATH = "/library";

    @SuppressWarnings("restriction")
    public Server() {
        try {
            initRepository();
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext(PATH, new LibraryHttpHandler(PATH));
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRepository() {
        AuthorRepository authorRepository = AuthorRepository.getInstance();
        authorRepository.createAndFillTable();
        BookRepository booksRepository = BookRepository.getInstance();
        booksRepository.createAndFillTable();
    }
}
