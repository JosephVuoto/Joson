package com.josephvuoto.joson.syntax;

import com.josephvuoto.joson.exception.JsonFormatException;
import com.josephvuoto.joson.lexical.Token;
import com.josephvuoto.joson.lexical.TokenType;
import com.josephvuoto.joson.model.Json;
import com.josephvuoto.joson.model.JsonArray;
import com.josephvuoto.joson.model.JsonObject;

import java.util.Deque;

/**
 * @author Yangzhe Xie
 * @date 30/12/19
 */
public class Parser {
    private Deque<Token> tokenList;

    public Parser(Deque<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public Json parse() throws JsonFormatException {
        if (tokenList == null
                || tokenList.isEmpty()) {
            throw new JsonFormatException("Invalid json format.");
        }
        switch (tokenList.pollFirst().getType()) {
            case ARR_START:
                return parseJsonArray();
            case OBJ_START:
                return parseJsonObject();
            default:
                throw new JsonFormatException("Invalid json format.");
        }
    }

    private JsonObject parseJsonObject() throws JsonFormatException {
        JsonObject jsonObject = new JsonObject();
        Token token;
        Token pre = null;
        String key = null;
        while (!tokenList.isEmpty()) {
            token = tokenList.pollFirst();
            switch (token.getType()) {
                case STRING:
                    if (pre == null) {
                        key = (String) token.getValue();
                    } else if (pre.getType() == TokenType.COLON) {
                        jsonObject.put(key, token.getValue());
                    } else if (pre.getType() == TokenType.COMMA) {
                        key = (String) token.getValue();
                    } else {
                        throw new JsonFormatException("Invalid json format.");
                    }
                    break;
                case INTEGER:
                case FLOAT:
                    if (pre == null || pre.getType() != TokenType.COLON) {
                        throw new JsonFormatException("Invalid json format.");
                    } else {
                        jsonObject.put(key, token.getValue());
                    }
                    break;
                case ARR_START:
                    jsonObject.put(key, parseJsonArray());
                    break;
                case OBJ_START:
                    jsonObject.put(key, parseJsonObject());
                    break;
                case OBJ_END:
                    return jsonObject;
            }
            pre = token;
        }
        return jsonObject;
    }

    private JsonArray parseJsonArray() throws JsonFormatException {
        JsonArray jsonArray = new JsonArray();
        Token token;
        while (!tokenList.isEmpty()) {
            token = tokenList.pollFirst();
            switch (token.getType()) {
                case OBJ_START:
                    JsonObject object = parseJsonObject();
                    jsonArray.add(object);
                    break;
                case STRING:
                case INTEGER:
                case FLOAT:
                    jsonArray.add(token.getValue());
                    break;
                case ARR_START:
                    jsonArray.add(parseJsonArray());
                    break;
                case ARR_END:
                    return jsonArray;
            }
        }
        return jsonArray;
    }
}
