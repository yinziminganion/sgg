package com.yinziming.part15.reflect;

public class ClassLoadingTest {
    public static void main(String[] args) {
        System.out.println(A.m);//100
    }
}

class A {
    //先赋值了300后赋值了100
    static {
        m = 300;
    }

    static int m = 100;

    /*
    这种情况A.m是300，也就是按照顺序进行赋值，先赋值100再赋值300
    static int m = 100;

    static {
        m = 300;
    }
    */
}