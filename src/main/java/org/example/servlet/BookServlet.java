package org.example.servlet;

import static org.example.constant.HttpConstants.DELETE;
import static org.example.constant.HttpConstants.GET;
import static org.example.constant.HttpConstants.POST;
import static org.example.constant.HttpConstants.PUT;
import java.io.IOException;
import java.io.InputStream;
import com.sun.net.httpserver.HttpExchange;
import org.example.controller.BookController;
import org.example.mapper.BookMapper;

@SuppressWarnings("restriction")
public class BookServlet extends LibraryServlet {
    private BookMapper mapper;
    private BookController controller;
    private static BookServlet servlet;
    private final String path = "/books";

    private BookServlet() {
        mapper = BookMapper.getInstance();
        controller = BookController.getInstance();
    }

    public static BookServlet getInstance() {
        if(servlet == null) {
            servlet = new BookServlet();
        }
        return servlet;
    }

    @Override
    public void listen(HttpExchange exchange) throws IOException {
        super.listen(exchange);
        check(path, exchange);
    }

    @Override
    protected void add(InputStream stream) {
        String[] values = mapper.getNameAndAuthorName(stream);
        String bookName = values[0];
        String authorName = values[1];
        controller.addBook(bookName, authorName);
    }

    @Override
    protected void delete(InputStream stream) {
        String bookName = mapper.getName(stream);
        controller.deleteBook(bookName);
    }

    @Override
    protected void get() {
        response = mapper.getBooks();
    }

    @Override
    protected void update(InputStream stream) {
        String[] values = mapper.getNameAndNewName(stream);
        String bookName = values[0];
        String newBookName = values[1];
        controller.updateBookName(bookName, newBookName);
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
