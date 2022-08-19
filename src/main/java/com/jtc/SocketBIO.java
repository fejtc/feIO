package com.jtc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.ArrayList;

/**
 * 退到jdk1.4版本使用
 *     /usr/java/j2sdk1.4.2_19/bin/javac SocketBIO.java
 */
public class SocketBIO {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9090);

        System.out.println("step1:new ServerSocket(9090)");

        while(true){
            final Socket client = server.accept(); //阻塞1
            System.out.println("step2:client\t" + client.getPort());

            //ArrayList<String> strings = new ArrayList<String>();


            new Thread(new Runnable() {
                public void run() {
                    InputStream in = null;
                    try{
                       in = client.getInputStream();
                       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                       while(true){
                           String dataline = reader.readLine(); //阻塞2
                           if(null != dataline){
                               System.out.println(dataline);
                           }else{
                               client.close();
                           }
                       }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}
