package org.example.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.example.controller.AuthorController;
import org.example.entity.Author;

public class AuthorMapper extends LibraryMapper {
    private AuthorController controller;
    private static AuthorMapper mapper;
    private static final String NAME_JSON_KEY = "name";
    private static final String NEW_NAME_JSON_KEY = "newName";

    private AuthorMapper() {
        controller = AuthorController.getInstance();
    }

    public static AuthorMapper getInstance() {
        if (mapper == null) {
            mapper = new AuthorMapper();
        }
        return mapper;
    }

    public String getName(InputStream stream) {
        return getValue(stream, NAME_JSON_KEY);
    }

    public String[] getNameAndNewName(InputStream stream) {
        HashMap<String, String> map = getValues(stream);
        String[] array = new String[2];
        array[0] = map.get(NAME_JSON_KEY);
        array[1] = map.get(NEW_NAME_JSON_KEY);
        return array;
    }

    public String getAuthors() {
        return mapToJson();
    }

    @Override
    protected String mapToJson() {
        ArrayList<Author> authors = controller.getAuthors();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            if(i == 0) {
                builder.append(openJsonArray());
            }
            builder.append(openJson());
            builder.append(getWithQuotes("id") + ":" + getWithQuotes(author.getId()) + ",");
            builder.append(getWithQuotes("name") + ":" + getWithQuotes(author.getName()));
            builder.append(closeJson());
            if(i != authors.size() - 1) {
                builder.append(",");
            } else {
                builder.append(closeJsonArray());
            }
        }
        return builder.toString();
    }
}
