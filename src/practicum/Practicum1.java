/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicum;

import java.net.Socket;

/**
 *
 * @author ASUS
 */
public class Practicum1 {
    public static void main(String[] args) {
        GreetClient client = new GreetClient();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("hello server");
        System.out.println("" + response);
    }
}
