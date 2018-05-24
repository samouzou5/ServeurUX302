/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import communciationUDP.ClientServer;
import communciationUDP.Communication;

/**
 *
 * @author EpulInfo
 */
public class ServeurConcurrent extends ClientServer {
    /**
     * constructeur du serveur concurrent
     * @param port port d'écoute
     */
    public ServeurConcurrent(int port){
        super(port);
        System.out.println("Serveur concurrent initialisé sur le port : " + PORT);
    }
    /**
     * méthode qui va lancer le serveur 
     */
    public void lancerServeur(){
        while(true){
            receivePacket();
            System.out.println("Un client vient de se connecter ici : " + getHostName() + ":" + getPort());
            new Thread(new Communication(getAddressSender(), getPort(),getMessage())).start();
        }
    }
    
    public static void main(String[] args){
        ServeurConcurrent sc = new ServeurConcurrent(PORT);
        sc.lancerServeur();
        sc.closeSocket();
    }
        
}
    

