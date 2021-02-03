package com.yinziming.part14.net;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPTest {
    @Test
    public void sender() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String s = "message by udp";
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        InetAddress host = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, host, 65500);
        socket.send(packet);
        socket.close();
    }

    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(65500);
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
        socket.receive(packet);
        String s = new String(packet.getData(), 0, packet.getLength());
        System.out.println(s);
        socket.close();
    }
}
