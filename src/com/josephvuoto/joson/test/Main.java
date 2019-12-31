package com.josephvuoto.joson.test;

import com.josephvuoto.joson.main.Joson;
import com.josephvuoto.joson.model.JsonObject;

/**
 * @author Yangzhe Xie
 * @date 30/12/19
 */
public class Main {
    public static void main(String[] args) {
        try {
            String test = "{\"name\":\"joseph\",\"age\":22.12,\"tel\":234234234,\"hobby\":[{\"a1\":\"k1\",\"a2\":\"k2\"},{\"a3\":\"k3\",\"a4\":\"k4\"}]}";
            JsonObject object = (JsonObject) Joson.parseJson(test);
            System.out.println(object.getString("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
