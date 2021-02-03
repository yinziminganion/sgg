package com.yinziming.part17.java9;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class DiamondOperatorTest {
    @Test
    public void test1() {
        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
    }
}
