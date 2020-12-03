/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class EchoMultiServer {

    private ServerSocket serverSocket;
    private ExecutorService exec;

    public void start(int port) {
        exec = Executors.newFixedThreadPool(10);
        try {
            System.out.println("Starting server on port "+port);
            serverSocket = new ServerSocket(port);
            while (true) {
                exec.submit(new EchoClientHandler(serverSocket.accept()));
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop() {
        try {
            exec.shutdownNow();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class EchoClientHandler implements Runnable{

        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    String in = inputLine.replace("->", "<-");
                    inputLine = in;
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(6666);
    }
}
