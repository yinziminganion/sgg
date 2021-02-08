# DOM模型 Document Object Model 文档对象模型

就是把文档中的标签、属性和文本转化成对象来管理

## Document对象

- Document对象
    - Document管理了所有的html文档内容
    - document是一种树结构的文档，有层级关系
    - 它让我们把所有的标签都对象化
    - 我们可以通过document访问所有的标签对象
- 主要方法
    - document.getElementById(elementId)通过标签的id属性查找标签dom对象，elementId是标签的id属性值，返回对拥有指定id的第一个对象的引用
    - document.getElementsByName(elementName)通过标签的name属性查找标签dom对象，elementName标签的name属性值
    - document.getElementsByTagName(tagName)通过标签名查找标签dom对象，tagName是标签名
    - document.createElement(tagName)通过给定的标签名创建一个标签对象，tagName是要创建的标签名
- 对于document的三个查询方法，如果有id属性，优先使用getElementById
- 三个查询方法一定要在页面加载完毕之后才可以查到

## example

- document.getElementById(elementId)

```html

<html>
<head>
    <script type="text/javascript">
        //需求：当用户点击了校验按钮，要获取输入框的内容，验证其是否合法
        //规则：必须由字母、数字、下划线组成，长度5-12位
        function check() {
            //1.先获取这个标签对象
            var usernameObj = document.getElementById("user");
            var usernameValue = usernameObj.value;
            //2.验证
            var pattern = /^\w{5,12}$/;
            var usernameSpanObj = document.getElementById('usernameSpan');
            if (pattern.test(usernameValue)) {
                usernameSpanObj.innerHTML = "合法";
            } else {
                usernameSpanObj.innerHTML = '不合法';
            }
        }
    </script>
</head>
<body>
username: <input type="text" id="user"><span id="usernameSpan" style="color:red;">用户名合法</span>
<button onclick="check()">校验</button>
</body>
</html>
```

- document.getElementsByName(elementName)

```html

<html>
<head>
    <script type="text/javascript">
        function chooseAll() {
            var hobbies = document.getElementsByName("hobby");
            for (var i = 0; i < hobbies.length; i++) {
                hobbies[i].checked = true;
            }
        }

        function chooseNone() {
            var hobbies = document.getElementsByName("hobby");
            for (var i = 0; i < hobbies.length; i++) {
                hobbies[i].checked = false;
            }
        }

        function chooseReverse() {
            var hobbies = document.getElementsByName("hobby");
            for (var i = 0; i < hobbies.length; i++) {
                hobbies[i].checked = !hobbies[i].checked;
            }
        }
    </script>
</head>
<body>
hobbies:
<input type="checkbox" name="hobby" value="cpp">C++
<input type="checkbox" name="hobby" value="java">Java
<input type="checkbox" name="hobby" value="js">JavaScript
<br/>
<button onclick="chooseAll()">全选</button>
<button onclick="chooseNone()">全不选</button>
<button onclick="chooseReverse()">反选</button>
</body>
</html>
```

- document.getElementsByTagName(tagName)

```html

<html>
<head>
    <script type="text/javascript">
        function chooseAll() {
            var inputs = document.getElementsByTagName("input");
            for (var i = 0; i < inputs.length; i++) {
                inputs[i].checked = true;
            }
        }
    </script>
</head>
<body>
hobbies:
<input type="checkbox" value="cpp">C++
<input type="checkbox" value="java">Java
<input type="checkbox" value="js">JavaScript
<br/>
<button onclick="chooseAll()">全选</button>
</body>
</html>
```

- document.createElement(tagName)

```html

<html>
<head>
    <script type="text/javascript">
        window.onload = function () {
            var obj = document.createElement("div");
            obj.innerHTML = "插入的内容";
            document.body.appendChild(obj);
        }
    </script>
</head>
<body>

</body>
</html>
```

## 节点的常用属性和方法

节点就是标签对象

- 方法
    - getElementsByTagName()获取当前节点的指定标签名孩子节点
    - appendChild(oChildNode)可以添加一个子结点，oChildNode是要添加的孩子节点
- 属性
    - childNodes获取当前节点的所有子节点
    - firstChild获取当前节点的第一个子节点
    - lastChild获取当前节点的最后一个子节点
    - parentNode获取当前节点的父节点
    - nextSibling获取当前节点的下一个节点
    - previousSibling获取当前节点的上一个节点
    - className获取或设置标签的class属性值
    - innerHTML获取或设置起始标签和结束标签中的内容
    - innerText获取或设置起始标签和结束标签中的文本

# 正则表达式

```javascript
//元字符
var pattern = /e/;//表示字符串中是否包含字母e
var pattern = /[abc]/;//表示字符串中是否包含字母a或b或c
var pattern = /[a-z]/;//表示是否包含小写字母
var pattern = /[A-Z]/;//表示是否包含大写字母
var pattern = /[0-9]/;//表示是否包含数字
var pattern = /\w/;//表示是否包含字母、数字、下划线
var pattern = /\W/;//与\w相反
var pattern = /\d/;//数字
var pattern = /\D/;//非数字
var pattern = /\s/;//空白字符
var pattern = /\S/;//非空白字符
//量词
var pattern = /a+/;//是否包含至少一个a
var pattern = /a*/;//是否包含0个或多个a
var pattern = /a?/;//是否包含0个或1个a
var pattern = /a(X)/;//是否包含连续X个a
var pattern = /a(X,Y)/;//是否包含至少X个连续的a，最多Y个连续的a
var pattern = /a(X,)/;//是否包含至少X个连续的a
var pattern = /a$/;//字符串是否以a结尾
var pattern = /^a/;//是否以a开头
```