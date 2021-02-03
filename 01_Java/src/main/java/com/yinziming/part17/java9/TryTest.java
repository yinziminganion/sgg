package com.yinziming.part17.java9;

import java.io.IOException;
import java.io.InputStreamReader;

public class TryTest {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        try (is) {
            char[] chars = new char[16];
            int len;
            while ((len = is.read(chars)) != -1) {
                System.out.print(new String(chars, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
