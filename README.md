# Joson
学习JSON解析原理时写的一个简单的小的Json解析框架

## 示例
### 构造Json字符串
```java
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
```
输出
```
{"book":[{"id":"01","language":"Java","edition":"third","author":"Herbert Schildt"},{"id":"07","language":"C++","edition":"second","author":"E.Balagurusamy"}]}
```
### 解析Json字符串
``` java
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
```
输出：
```
Herbert Schildt
```