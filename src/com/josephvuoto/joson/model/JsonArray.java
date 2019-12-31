package com.josephvuoto.joson.model;

import com.josephvuoto.joson.exception.JsonTypeException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangzhe Xie
 * @date 30/12/19
 */
public class JsonArray implements Json {
    private List<Object> list;

    public JsonArray() {
        list = new ArrayList<>();
    }

    public void add(Object obj) {
        list.add(obj);
    }

    public Object get(int index) {
        return list.get(index);
    }

    public int getInteger(int index) throws JsonTypeException {
        if (list.get(index) instanceof Integer) {
            return (int) list.get(index);
        } else {
            throw new JsonTypeException("The value is not an integer");
        }
    }

    public String getString(int index) throws JsonTypeException {
        if (list.get(index) instanceof String) {
            return (String) list.get(index);
        } else {
            throw new JsonTypeException("The value is not a string");
        }
    }

    public double getDouble(int index) throws JsonTypeException {
        if (list.get(index) instanceof Double) {
            return (double) list.get(index);
        } else {
            throw new JsonTypeException("The value is not a double");
        }
    }

    public JsonObject getJsonObject(int index) throws JsonTypeException {
        if (list.get(index) instanceof JsonObject) {
            return (JsonObject) list.get(index);
        } else {
            throw new JsonTypeException("The value is not a JsonObject");
        }
    }

    public JsonArray getJsonArray(int index) throws JsonTypeException {
        if (list.get(index) instanceof JsonArray) {
            return (JsonArray) list.get(index);
        } else {
            throw new JsonTypeException("The value is not a JsonArray");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof String) {
                sb.append("\"")
                        .append(list.get(i))
                        .append("\"");
            } else {
                sb.append(list.get(i).toString());
            }
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
