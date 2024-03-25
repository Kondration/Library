package org.example.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.example.controller.BookController;
import org.example.entity.Book;

public class BookMapper extends LibraryMapper {
    private BookController controller;
    private static BookMapper mapper;
    private static final String NAME_JSON_KEY = "name";
    private static final String NEW_NAME_JSON_KEY = "newName";
    private static final String AUTHOR_JSON_KEY = "author";

    private BookMapper() {
        controller = BookController.getInstance();
    }

    public static BookMapper getInstance() {
        if (mapper == null) {
            mapper = new BookMapper();
        }
        return mapper;
    }
    
    public String getName(InputStream stream) {
        return getValue(stream, NAME_JSON_KEY);
    }

    public String getBooks() {
        return mapToJson();
    }

    public String[] getNameAndAuthorName(InputStream stream) {
        HashMap<String, String> map = getValues(stream);
        String[] array = new String[2];
        array[0] = map.get(NAME_JSON_KEY);
        array[1] = map.get(AUTHOR_JSON_KEY);
        return array;
    }

    public String[] getNameAndNewName(InputStream stream) {
        HashMap<String, String> map = getValues(stream);
        String[] array = new String[2];
        array[0] = map.get(NAME_JSON_KEY);
        array[1] = map.get(NEW_NAME_JSON_KEY);
        return array;
    }

    @Override
    protected String mapToJson() {
        ArrayList<Book> books = controller.getBooks();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if(i == 0) {
                builder.append(openJsonArray());
            }
            builder.append(openJson());
            builder.append(getWithQuotes("id") + ":" + getWithQuotes(book.getId()) + ",");
            builder.append(getWithQuotes("name") + ":" + getWithQuotes(book.getName()) + ",");
            builder.append(getWithQuotes("author_id") + ":" + getWithQuotes(book.getAutor_id()));
            builder.append(closeJson());
            if(i != books.size() - 1) {
                builder.append(",");
            } else {
                builder.append(closeJsonArray());
            }
        }
        return builder.toString();
    }
}
