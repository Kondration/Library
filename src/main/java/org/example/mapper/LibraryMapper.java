package org.example.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public abstract class LibraryMapper {

    private HashMap<String, String> mapFromJson(String json) {
        String[] jsonArray = json.split("\":\"");
        String[] key = jsonArray[0].split("\"");
        String[] value = jsonArray[1].split("\"");

        HashMap<String, String> map = new HashMap<>();
        map.put(key[1], value[0]);
        return map;
    }

    private HashMap<String, String> slice(String json) {
        String[] slicedJson = json.split("},");
        HashMap<String, String> map = new HashMap<>();
        map.putAll(mapFromJson(slicedJson[0]));
        map.putAll(mapFromJson(slicedJson[1]));
        return map;
    }

    private HashMap<String, String> fromInputStreamToMap(InputStream stream) throws IOException {
        String json = initString(stream);
        if(json.contains("},{")) {
            return slice(json);
        } else {
            return mapFromJson(json.toString());
        }
    }
    
    private String initString(InputStream stream) {
        InputStreamReader reader = new InputStreamReader(stream);
        StringBuilder jsonBuilder = new StringBuilder();
        int i;
        try {
            while ((i = reader.read()) != -1) {
               jsonBuilder.append((char) i);
            }
            return jsonBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    protected String getValue(InputStream stream, String key) {
        try {
            return fromInputStreamToMap(stream).get(key);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    protected HashMap<String, String> getValues(InputStream stream) {
        try {
            return fromInputStreamToMap(stream);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract String mapToJson();

    protected String getWithQuotes(String value) {
        return "\"" + value + "\"";
    }

    protected String getWithQuotes(int value) {
        return "\"" + value + "\"";
    }

    protected String openJson() {
        return "{";
    }

    protected String closeJson() {
        return "}";
    }

    protected String openJsonArray() {
        return "[";
    }

    protected String closeJsonArray() {
        return "]";
    }
}
