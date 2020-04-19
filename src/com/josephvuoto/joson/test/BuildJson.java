package com.josephvuoto.joson.test;

import com.josephvuoto.joson.model.JsonArray;
import com.josephvuoto.joson.model.JsonObject;

/**
 * @author Yangzhe Xie
 * @date 19/4/20
 */
public class BuildJson {
    public static void main(String[] args) {
        JsonObject book1 = new JsonObject();
        book1.put("id", "01");
        book1.put("language", "Java");
        book1.put("edition", "third");
        book1.put("author", "Herbert Schildt");
        JsonObject book2 = new JsonObject();
        book2.put("id", "07");
        book2.put("language", "C++");
        book2.put("edition", "second");
        book2.put("author", "E.Balagurusamy");
        JsonArray bookArray = new JsonArray();
        bookArray.add(book1);
        bookArray.add(book2);
        JsonObject result = new JsonObject();
        result.put("book", bookArray);
        System.out.println(result.buildJson());
    }
}
