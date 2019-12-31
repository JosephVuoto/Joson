package com.josephvuoto.joson.exception;

import java.io.IOException;

/**
 * @author Yangzhe Xie
 * @date 30/12/19
 */
public class JsonTypeException extends IOException {
    public JsonTypeException(String message) {
        super(message);
    }
}
