package com.yinziming.part02.knowledge;

public class StringTest {
    public static void main(String[] args) {
//        String s1 = "abc";
//        String s2 = "abc";
//        String s3 = new String("abc");
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println("********************************************");

        String s1 = "javaee";
        String s2 = "hadoop";
        String s3 = "javaeehadoop";
        String s4 = "javaee" + "hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "javaee" + s2;
        String s7 = s1 + s2;
        System.out.println(s3 == s4);//true, 地址相同
        System.out.println(s3 == s5);//false, 因为涉及变量所以结果在堆中
        System.out.println(s3 == s6);
        System.out.println(s3 == s7);
        System.out.println(s5 == s6);
        System.out.println(s5 == s7);
        System.out.println(s6 == s7);
        System.out.println(s6.intern() == s7.intern());//true, 如果拼接的结果调用intern()方法则返回值就在常量池中

    }


}
