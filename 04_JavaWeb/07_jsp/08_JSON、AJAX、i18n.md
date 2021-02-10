# JSON

## 概念

JavaScript Object Notation（JSON）是一种轻量级的数据交换格式

## JavaScript中的使用

json是由键值对组成，并由大括号包围，每个键由引号引起来，键和值之间使用冒号进行分隔，多组键值对之间用逗号分隔

```javascript
var jsonObj = {
    "key1": 12,
    "key2": "abc",
    "key3": true,
    "key4": [11, "arr", false],
    "key5": {
        "key51": 51,
        "key52": "key52value"
    },
    "key6": [{
        "key611": 611,
        "key612": "key612value"
    }, {
        "key621": 621,
        "key622": "key622value"
    }]
};
alert(typeof jsonObj);//object
alert(jsonObj.key1);//12
alert(jsonObj.key2);//abc
alert(jsonObj.key3);//true
alert(jsonObj.key4);//11,arr,false
for (var i = 0; i < jsonObj.key4.length; i++) {
    alert(jsonObj.key4[i]);//11                 arr               false
}
alert(jsonObj.key5.key51);//51
```

## JavaScript中两个常用方法

因为json的存在有两种形式，对象形式和字符串形式，两种形式可以互相转换

JSON.stringify(): object -> string

JSON.parse(): string -> object

## Java中的使用

```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    private Integer id;
    private String name;

    Person() {
    }

    Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class JsonTest1 {
    public void test1() {// JavaBean和json的互转
        Person person = new Person(1, "name1");
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person1);//java bean -> json string

        Person p = gson.fromJson(personJsonString, Person.class);//json string -> java bean
    }

    public void test2() {// List和json的互转
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(1, "name1"));
        list.add(new Person(2, "name2"));
        Gson gson = new Gson();

        String personListJsonString = gson.toJson(list);//List -> json string

        ArrayList<Person> list1 = gson.fromJson(personListJsonString, new PersonListType().getType());//json string -> List
    }

    private class PersonListType extends TypeToken<ArrayList<Person>> {
    }

    public void test3() {//map和json的互转
        HashMap<Integer, Person> map = new HashMap<>();
        map.put(1, new Person(1, "name1"));
        map.put(1, new Person(2, "name2"));
        Gson gson = new Gson();

        String personMapJsonString = gson.toJson(map);//Map -> json string

        HashMap<Integer, Person> map1 = gson.fromJson(personMapJsonString, new TypeToken<ArrayList<HashMap<Integer, Person>>>() {
        }.getType());//json string -> Map
    }
}
```

# AJAX请求

## 概念

Asynchronous JavaScript And XML，是一种创建交互式网页应用的网页开发技术，一种浏览器通过js异步发起请求，局部更新页面的技术。

Ajax请求的局部更新，浏览器地址栏不会发生变化，局部更新不会舍弃原来页面的内容。

```java
import com.google.gson.Gson;

public class AjaxServlet extends HttpServlet {
    protected void javaScriptAjax(HttpServletRequest req, HttpServletResponse resp) {
        Person person = new Person(1, "name1");
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);
    }
}
```

```javascript
function ajaxRequest() {
    var xmlHttpRequest = new XMLHttpRequest();//1. 创建XMLHttpRequest
    xmlHttpRequest.open("GET", "http://ip:port/project/ajaxServlet?action=javaScriptAjax", true);//2. 调用open方法设置请求参数
    xmlHttpRequest.onreadystatechange = function () {
        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
            var jsonObj = JSON.parse(xmlHttpRequest.responseText);
            document.getElementById("div1").innerHTML = "编号：" + jsonObj.id + "，姓名：" + jsonObj.name;//4. 在send方法之前绑定onreadystatechange事件，处理请求完成后的操作
        }
    }
    xmlHttpRequest.send();//3. 调用send方法发送请求
}
```

## jQuery中的Ajax请求

### $.ajax方法

- url 表示请求的地址
- type 表示请求的类型GET或POST
- data 表示发送给服务器的数据，格式有两种①name=value&name=value②{key:value}
- success 请求响应，响应的回调函数
- dataType 响应的数据类型，常用的有text，xml，json

```javascript
$(function () {
    $("#ajaxButton").click(
        function () {
            $.ajax({
                url: "http://ip:port/project/ajaxServlet",
                data: "action=jQueryAjax",
                type: "GET",
                success: function (data) {
                    $("#msg").html("编号：" + data.id + "，姓名：" + data.name);
                },
                dataType: "json"
            })
        }
    )
})
```

### $.get和$.post

- url
- data
- callback
- type

### $.getJSON方法

- url
- data
- callback

## jQuery中的serialize方法

serialize()可以把表单中所有表单项的内容都获取到，并以name=value&name=value的形式进行拼接

```javascript
$("#submit").click(function () {
    $.getJSON("http://ip:port/project/ajaxServlet", "action=jQuerySerialize&" + $("#from1").serialize(), function (data) {
        $("#msg").html("收到序列化后的信息");
    })
})
```