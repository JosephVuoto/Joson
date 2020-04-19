package com.josephvuoto.joson.test;

import com.josephvuoto.joson.main.Joson;
import com.josephvuoto.joson.model.JsonObject;

/**
 * @author Yangzhe Xie
 * @date 30/12/19
 */
public class ParseJson {

    public static void main(String[] args) {
//        Json实例
//        {
//            "book": [
//            {
//                "id":"01",
//                    "language": "Java",
//                    "edition": "third",
//                    "author": "Herbert Schildt"
//            },
//            {
//                "id":"07",
//                    "language": "C++",
//                    "edition": "second"
//                "author": "E.Balagurusamy"
//            }]
//        }
        try {
            String jsonStr = "{\"book\":[{\"id\":\"01\",\"language\":\"Java\"," +
                    "\"edition\":\"third\",\"author\":\"Herbert Schildt\"}," +
                    "{\"id\":\"07\",\"language\":\"C++\",\"edition\":\"second\"," +
                    "\"author\":\"E.Balagurusamy\"}]}";
            JsonObject object = (JsonObject) Joson.parseJson(jsonStr);
            System.out.println(((JsonObject) object.getJsonArray("book").get(0)).getString("author"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
