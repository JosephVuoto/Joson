package com.josephvuoto.joson.model;

import com.josephvuoto.joson.exception.JsonTypeException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yangzhe Xie
 * @date 30/12/19
 */
public class JsonObject implements Json {
    private Map<String, Object> map;

    public JsonObject() {
        map = new LinkedHashMap<>();
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object value(String key) {
        return map.get(key);
    }

    public int getInteger(String key) throws JsonTypeException {
        if (map.get(key) instanceof Integer) {
            return (int) map.get(key);
        } else {
            throw new JsonTypeException("The value is not an integer");
        }
    }

    public String getString(String key) throws JsonTypeException {
        if (map.get(key) instanceof String) {
            return (String) map.get(key);
        } else {
            throw new JsonTypeException("The value is not a string");
        }
    }

    public double getDouble(String key) throws JsonTypeException {
        if (map.get(key) instanceof Double) {
            return (double) map.get(key);
        } else {
            throw new JsonTypeException("The value is not a double");
        }
    }

    public JsonObject getJsonObject(String key) throws JsonTypeException {
        if (map.get(key) instanceof JsonObject) {
            return (JsonObject) map.get(key);
        } else {
            throw new JsonTypeException("The value is not a JsonObject");
        }
    }

    public JsonArray getJsonArray(String key) throws JsonTypeException {
        if (map.get(key) instanceof JsonArray) {
            return (JsonArray) map.get(key);
        } else {
            throw new JsonTypeException("The value is not a JsonArray");
        }
    }

    public void addEntry(String key, Object value) {
        map.put(key, value);
    }

    public String buildJson() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = map.size();
        for (String key : map.keySet()) {
            sb.append("\"")
                    .append(key)
                    .append("\"")
                    .append(":");
            if (map.get(key) instanceof String) {
                sb.append("\"")
                        .append(map.get(key))
                        .append("\"");
            } else {
                sb.append(map.get(key));
            }
            if (--size != 0) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
