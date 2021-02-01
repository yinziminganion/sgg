package com.yinziming.part14.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class INetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress);
            InetAddress inetAddress1 = InetAddress.getByName("yinziming.com");
            System.out.println(inetAddress1);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
