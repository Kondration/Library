package org.example.servlet;

import static org.example.constant.HttpConstants.DELETE;
import static org.example.constant.HttpConstants.GET;
import static org.example.constant.HttpConstants.POST;
import static org.example.constant.HttpConstants.PUT;
import java.io.IOException;
import java.io.InputStream;
import org.example.controller.AuthorController;
import org.example.mapper.AuthorMapper;
import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public class AuthorServlet extends LibraryServlet {
    private AuthorMapper mapper;
    private AuthorController controller;
    private static AuthorServlet servlet;
    private final String path = "/authors";

    private AuthorServlet() {
        mapper = AuthorMapper.getInstance();
        controller = AuthorController.getInstance();
    }

    public static AuthorServlet getInstance() {
        if(servlet == null) {
            servlet = new AuthorServlet();
        }
        return servlet;
    }

    @Override
    public void listen(HttpExchange exchange) throws IOException {
        super.listen(exchange);
        check(path, exchange);
    }

    @Override
    public void add(InputStream stream) {
        String authorName = mapper.getName(stream);
        controller.addAuthor(authorName);
    }

    @Override
    public void delete(InputStream stream) {
        String authorName = mapper.getName(stream);
        controller.deleteAuthor(authorName);
    }

    @Override
    public void get() {
        response = mapper.getAuthors();
    }

    @Override
    public void update(InputStream stream) {
        String[] values = mapper.getNameAndNewName(stream);
        String authorName = values[0];
        String newAuthorName = values[1];
        controller.updateAuthor(authorName, newAuthorName);
    }

    @Override
    protected void sendResponse(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    protected void check(String path, HttpExchange exchange) throws IOException{
        if(requestPath.contains(path)) {
            switch (requestMethood) {
                case GET: get(); break;
                case POST: add(requestBody); sendResponseMessage(POST); break;
                case PUT: update(requestBody); sendResponseMessage(PUT); break;
                case DELETE: delete(requestBody); sendResponseMessage(DELETE); break;
            }
            sendResponse(exchange);
        }
    }
}
