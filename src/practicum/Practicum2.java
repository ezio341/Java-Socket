/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicum;

/**
 *
 * @author ASUS
 */
public class Practicum2 {
    public static void main(String[] args) {
        GreetClient client1 = new GreetClient();
        GreetClient client2 = new GreetClient();
        
        client1.startConnection("127.0.0.1", 6666);
        client2.startConnection("127.0.0.1", 6666);
        
        String response = client1.sendMessage("holla server");
        String response1 = client1.sendMessage("hoe server");
        String response2 = client2.sendMessage("holla server2");
        String response3 = client2.sendMessage("hoe server2");
        String endMessage = client1.sendMessage(".");
        
        System.out.println("" + response);
        System.out.println("" + response1);
        System.out.println("" + response2);
        System.out.println("" + response3);
        System.out.format("%s", endMessage);
    }
}
