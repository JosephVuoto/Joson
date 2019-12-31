package com.josephvuoto.joson.exception;

import java.io.IOException;

/**
 * @author Yangzhe Xie
 * @date 30/12/19
 */
public class JsonFormatException extends IOException {
    public JsonFormatException(String message) {
        super(message);
    }
}
