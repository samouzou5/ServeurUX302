/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import communciationUDP.ClientServer;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author EpulInfo
 */
public class ClientUDP extends ClientServer {
    /**
     * constructeur de la classe Client UDP qui appelle le constructeur par défaut de ClientServer
     */
    public ClientUDP(){
        super();
    }

    //méthode qui permet de lancer un client
    public void lancerClient(){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez votre choix : ");
        String message = "";
        
        message = sc.nextLine();
        
        sendMessage(iadd_server,PORT,message);
        
        receivePacket();
        System.out.println("Le serveur est lancé, vous pouvez communiquer : " + this.getHostName() + ":" + ds.getLocalPort());
        while(!message.equals("ciao")) {
            System.out.println("Entrez votre message :");
            message = sc.nextLine();
            sendMessage(getAddressSender(), getPort(), message);
            System.out.println("Message envoyé");
            receivePacket();
            System.out.println("Message du serveur : " + this.getMessage());
            System.out.println("Depuis : " + this.getAddressSender() + ":" + this.getPort());
                
        }
    }

    public static void main(String argv[]) throws Exception {
        ClientUDP c = new ClientUDP();
        c.lancerClient();
    }
}
