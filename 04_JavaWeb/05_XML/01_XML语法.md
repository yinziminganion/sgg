XML是可扩展的标记性语言

XML作用：①用来保存数据②作为项目或者模块的配置文件③作为网络传输数据的格式

XML语法：①文档声明②元素（标签）③xml属性④xml注释⑤文本区域（CDATA区）

CDATA可以告诉xml解析器，我CDATA里的内容只是纯文本，不需要xml语法解析

```xml
<?xml version="1.0" encoding="utf-8" ?>
<!--
    以上内容就是xml文件的声明
    version="1.0"       version表示xml的版本
    encoding="utf-8"    encoding表示xml文件本身的编码
-->
<books>
    <book sn="1">
        <name>&lt;名字1&gt;</name>
        <author>作者1</author>
        <price>10</price>
    </book>
    <book sn="2">
        <name><![CDATA[<名字2>]]></name>
        <author>作者2</author>
        <price>20</price>
    </book>
    <book sn="3" name="名字3" author="作者3" price="30"/>
</books>
```

XML文档必须有根元素，也就是顶级元素。没有父元素的元素是顶级元素，而且根元素必须是唯一的