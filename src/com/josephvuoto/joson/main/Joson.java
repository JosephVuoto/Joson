package com.josephvuoto.joson.main;

import com.josephvuoto.joson.exception.JsonFormatException;
import com.josephvuoto.joson.lexical.Lexer;
import com.josephvuoto.joson.model.Json;
import com.josephvuoto.joson.syntax.Parser;

/**
 * @author Yangzhe Xie
 * @date 31/12/19
 */
public class Joson {
    public static Json parseJson(String jsonString) throws JsonFormatException {
        Lexer lexer = new Lexer(jsonString);
        lexer.analyze();
        Parser parser = new Parser(lexer.getTokenList());
        return parser.parse();
    }
}
