/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communciationUDP;

import java.net.InetAddress;

/**
 *
 * @author EpulInfo
 * permet la communication entre un client et le serveur
 */
public class Communication extends ClientServer implements Runnable {

    public Communication() {
        super(catchPortAvailable(2500, 10000));
    }
    
    public Communication(InetAddress iaClient,int portClient){
        super(catchPortAvailable(2500, 10000));
        sendMessage(iaClient,portClient,"serveur echo ready");
    }
    
    public void addChatClient(int port) {
        if (!listeClients.contains(port)) {
            this.listeClients.add(port);
        }
       
    }
   
    @Override
    public void run() {
        String ch = "";
        while(!ch.equals("ciao")){
            receivePacket();
            if (getPort() == PORT) {
                int clientPort = Integer.parseInt(getMessage().trim());
                addChatClient(clientPort);
                sendMessage(getAddressSender(), clientPort, "ok");
            } else {
                System.out.println("Message du client " + getAddressSender() + ":" + getPort() + ", il dit : " + getMessage());
                System.out.println("Transmission...");
                addChatClient(getPort());
                ch = getMessage();
                for(int clientPort : listeClients){
                  sendMessage(getAddressSender(),clientPort,ch);
                }  
            }
        }
    }
    
    
}
