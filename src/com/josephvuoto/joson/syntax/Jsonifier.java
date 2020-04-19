package com.josephvuoto.joson.syntax;

import java.lang.reflect.Field;

/**
 * @author Yangzhe Xie
 * @date 31/12/19
 */
@SuppressWarnings("rawtypes")
public class Jsonifier {
    public static String toJson(Object object) {
        try {
            Class cls = object.getClass();
            Field[] fields = cls.getFields();
            Field field;
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (int i = 0; i < fields.length; i++) {
                field = fields[i];
                field.setAccessible(true);
                sb.append("\"")
                        .append(field.getName())
                        .append("\"")
                        .append(":");
                if (field.getType() == String.class) {
                    if (field.get(object) == null) {
                        sb.append("null");
                    } else {
                        sb.append("\"")
                                .append(field.get(object))
                                .append("\"");
                    }
                } else if (field.getType() == Integer.class || field.getType() == Short.class
                    || field.getType() == Long.class || field.getType() == Float.class
                        || field.getType() == double.class || field.getType() == Boolean.class) {
                    sb.append(field.get(object));
                } else {
                    //TODO
                }
                if (i != fields.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
            return sb.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {
        System.out.println(toJson(new Test("asd", "dfg")));
    }

    public static class Test {
        public Test(String a, String b) {
            this.a = a;
            this.b = b;
        }

        public String a;
        public String b;
    }
}
