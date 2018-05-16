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
    private Communication chat = null;
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
            int choice = 0;
            do {
                receivePacket();
                String message = getMessage().trim();
                if (message.equals("bonjour serveur echo")) {
                    choice = 1;
                } else if (message.equals("bonjour chat")) {
                    choice = 2;
                }
                
            } while (choice == 0);
            System.out.println("Un client vient de se connecter ici : " + getHostName() + ":" + getPort());
            
            switch (choice) {
                case 1:
                    new Thread(new Communication(getAddressSender(), getPort())).start();
                    break;
                case 2:
                    if (chat == null) {
                        chat = new Communication();
                        new Thread(chat).start();
                    }
                    sendMessage(getAddressSender(),chat.port, Integer.toString(getPort()));
                    break;
            }
            
        }
    }
    
    public static void main(String[] args){
        ServeurConcurrent sc = new ServeurConcurrent(PORT);
        sc.lancerServeur();
        sc.closeSocket();
    }
        
}
    

