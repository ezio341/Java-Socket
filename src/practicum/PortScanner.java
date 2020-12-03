/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicum;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arga
 */
public class PortScanner {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //host
        String ip = "localhost";
        //time out ben gak buang waktu
        int timeout = 1;
        ExecutorService ex = Executors.newFixedThreadPool(10);
        List<Integer> list = new ArrayList<>();
        
        for(int i = 1; i <= 65535 ; i++){
            final int port = i;
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    Socket socket = new Socket();
                try {
                    socket.connect(new InetSocketAddress(ip, port), timeout);
                    if(socket.isConnected()){
                        list.add(port);
                        System.out.println(port);
                    }
                        socket.close();
                    } catch (IOException ex1) {
                    }
                }
            }).get();
        }
        
        ex.shutdown();
        System.out.println("Scan port on host : "+ip);
        System.out.println("total port : "+list.size());
        System.out.println("port list : ");
        for(int i : list){
            System.out.println(i);
        }
    }
}
