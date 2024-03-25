package org.example.server;

import java.io.IOException;
import org.example.servlet.AuthorServlet;
import org.example.servlet.BookServlet;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class LibraryHttpHandler implements HttpHandler {
    private BookServlet bookServlet;
    private AuthorServlet authorServlet;

    public LibraryHttpHandler(String serverPath) {
        bookServlet = BookServlet.getInstance();
        authorServlet = AuthorServlet.getInstance();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        bookServlet.listen(exchange);
        authorServlet.listen(exchange);
    }
}
