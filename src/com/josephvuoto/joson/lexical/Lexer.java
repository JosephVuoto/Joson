package com.josephvuoto.joson.lexical;

import com.josephvuoto.joson.exception.JsonFormatException;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Yangzhe Xie
 * @date 30/12/19
 */
public class Lexer {
    private Deque<Token> tokenList;
    private String rawString;
    private int index;

    public Lexer(String rawString) {
        this.rawString = rawString;
        index = 0;
        tokenList = new LinkedList<>();
    }

    public void analyze() throws JsonFormatException {
        Token token;
        while ((token = process()) != null) {
            tokenList.add(token);
        }
    }

    private Token process() throws JsonFormatException {
        while (index < rawString.length() && isEmptyChar()) {
            index++;
        }
        if (index >= rawString.length()) {
            return null;
        }
        if (isString()) {
            return getStringToken();
        } else if (isNumber()) {
            return getNumberToken();
        } else if (isTrue()) {
            return new Token(TokenType.BOOLEAN, "true");
        } else if (isFalse()) {
            return new Token(TokenType.BOOLEAN, "false");
        } else if (isNull()) {
            return new Token(TokenType.NULL, null);
        } else if (isObjStart()) {
            index++;
            return new Token(TokenType.OBJ_START, "{");
        } else if (isObjEnd()) {
            index++;
            return new Token(TokenType.OBJ_END, "}");
        } else if (isArrayStart()) {
            index++;
            return new Token(TokenType.ARR_START, "[");
        } else if (isArrayEnd()) {
            index++;
            return new Token(TokenType.ARR_END, "}");
        } else if (isColon()) {
            index++;
            return new Token(TokenType.COLON, ":");
        } else if (isComma()) {
            index++;
            return new Token(TokenType.COMMA, ",");
        }
        return null;
    }

    private boolean isNull() throws JsonFormatException {
        if (rawString.charAt(index) == 'n') {
            if (rawString.length() < index + 4) {
                throw new JsonFormatException("Invalid json format.");
            }
            if (rawString.substring(index, index + 4).equals("null")) {
                index += 4;
                return true;
            } else {
                throw new JsonFormatException("Invalid json format.");
            }
        } else {
            return false;
        }
    }

    private boolean isTrue() throws JsonFormatException {
        if (rawString.charAt(index) == 't') {
            if (rawString.length() < index + 4) {
                throw new JsonFormatException("Invalid json format.");
            }
            if (rawString.substring(index, index + 4).equals("true")) {
                index += 4;
                return true;
            } else {
                throw new JsonFormatException("Invalid json format.");
            }
        } else {
            return false;
        }
    }

    private boolean isFalse() throws JsonFormatException {
        if (rawString.charAt(index) == 'f') {
            if (rawString.length() < index + 5) {
                throw new JsonFormatException("Invalid json format.");
            }
            if (rawString.substring(index, index + 5).equals("false")) {
                index += 5;
                return true;
            } else {
                throw new JsonFormatException("Invalid json format.");
            }
        } else {
            return false;
        }
    }

    private Token getNumberToken() throws JsonFormatException {
        StringBuilder sb = new StringBuilder();
        sb.append(rawString.charAt(index++));
        boolean hasFraction = false;
        while (isDigit(rawString.charAt(index)) || rawString.charAt(index) == '.') {
            if (rawString.charAt(index) == '.') {
                if (hasFraction) {
                    throw new JsonFormatException("Invalid json format.");
                }
                hasFraction = true;
                sb.append(rawString.charAt(index));
                index++;
                continue;
            }
            sb.append(rawString.charAt(index));
            index++;
            if (index == rawString.length()) {
                throw new JsonFormatException("Invalid json format.");
            }
        }
        if (sb.length() == 1 && sb.charAt(0) == '-') {
            throw new JsonFormatException("Invalid json format.");
        }
        try {
            if (hasFraction) {
                return new Token(TokenType.FLOAT, Double.parseDouble(sb.toString()));
            } else {
                return new Token(TokenType.INTEGER, Integer.parseInt(sb.toString()));
            }
        } catch (NumberFormatException e) {
            throw new JsonFormatException("Invalid json format.");
        }
    }

    private Token getStringToken() throws JsonFormatException {
        index++;
        StringBuilder sb = new StringBuilder();
        while (true) {
            char c = rawString.charAt(index++);
            if (index > rawString.length()) {
                throw new JsonFormatException("Invalid json format.");
            }
            if (c == '"') {
                return new Token(TokenType.STRING, sb.toString());
            } else if (c == '\n') {
                throw new JsonFormatException("Invalid json format.");
            } else {
                sb.append(c);
            }
        }
    }

    private boolean isObjStart() {
        return rawString.charAt(index) == '{';
    }

    private boolean isObjEnd() {
        return rawString.charAt(index) == '}';
    }

    private boolean isArrayStart() {
        return rawString.charAt(index) == '[';
    }

    private boolean isArrayEnd() {
        return rawString.charAt(index) == ']';
    }

    private boolean isComma() {
        return rawString.charAt(index) == ',';
    }

    private boolean isColon() {
        return rawString.charAt(index) == ':';
    }

    private boolean isString() {
        return rawString.charAt(index) == '"';
    }

    private boolean isNumber() {
        return isDigit(rawString.charAt(index))
                || rawString.charAt(index) == '-';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isEmptyChar() {
        char c = rawString.charAt(index);
        return c == ' ' || c == '\t' || c == '\n';
    }

    public Deque<Token> getTokenList() {
        return tokenList;
    }
}
