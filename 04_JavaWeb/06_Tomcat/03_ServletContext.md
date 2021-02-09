# ServletContext

## 什么是ServletContext
1. ServletContext是一个接口，它表示Servlet上下文对象
2. 一个web工程，只有一个ServletContext对象实例
3. ServletContext对象是一个域对象

域对象：是可以像map一样存取数据的对象

域：存储数据的操作范围

| | 存数据 | 取数据 | 删除数据 |
| :---: | :---: | :---: | :---: |
| Map | put() | get() | remove() |
| 域对象 | setAttribute() | getAttribute() | removeAttribute() |

## ServletContext类的四个作用
1. 获取web.xml中配置的上下文参数context-param
2. 获取当前的工程路径
3. 获取工程部署后在硬盘上的绝对路径
4. 像Map一样存取数据