package org.example.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public abstract class LibraryServlet {
    protected InputStream requestBody;
    protected OutputStream outputStream;
    protected String requestMethood;
    protected String requestPath;
    protected String response = "";

    protected abstract void add(InputStream stream);
    protected abstract void delete(InputStream stream);
    protected abstract void get();
    protected abstract void update(InputStream stream);
    protected abstract void sendResponse(HttpExchange exchange) throws IOException;
    protected abstract void check(String pathm, HttpExchange exchange) throws IOException;
    
    public void listen(HttpExchange exchange) throws IOException {
        init(exchange);
    };

    private void init(HttpExchange exchange) {
        requestBody = exchange.getRequestBody();
        outputStream = exchange.getResponseBody();
        requestMethood = exchange.getRequestMethod();
        requestPath = exchange.getRequestURI().getPath();
    }

    protected void sendResponseMessage(String method) {
        response = "Method " + method + " completed successfully";
    }
}
