package com.yinziming;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public class Dom4jTest {
    @Test
    public void test1() throws Exception {
        InputStream is = Dom4jTest.class.getClassLoader().getResourceAsStream("books.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(is);
        System.out.println("document = " + document);
    }

    @Test
//    读取books.xml文件生成Book类
    public void test2() throws Exception {
        InputStream is = Dom4jTest.class.getClassLoader().getResourceAsStream("books.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(is);
        Element rootElement = document.getRootElement();
//        System.out.println("rootElement = " + rootElement);
        List<Element> books = rootElement.elements();
        for (var b : books) {
            String name = b.elementText("name");
            String price = b.elementText("price");
            String author = b.elementText("author");
            String sn = b.attributeValue("sn");
            Book book = new Book(sn, name, new BigDecimal(price), author);
            System.out.println("book = " + book);
        }
    }
}

class Book {
    private String sn;
    private String name;
    private BigDecimal price;
    private String author;

    public Book(String sn, String name, BigDecimal price, String author) {
        this.sn = sn;
        this.name = name;
        this.price = price;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}