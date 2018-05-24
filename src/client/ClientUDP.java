package client;

import communciationUDP.ClientServer;
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

    /**
     * méthode qui permet de lancer un client
     */
    public void lancerClient(){
        
        Scanner sc = new Scanner(System.in);
        String message = "";
        while(!message.equals("ciao")) {
            System.out.println("Client " + getClientPort() +  ", entrez votre message :");
            message = sc.nextLine();
            if(getPort() == 0){
                sendMessage(iadd_server,PORT,message);
            }else{
                sendMessage(getAddressSender(), getPort(), message);
            }
            System.out.println("Message envoyé!");
            receivePacket();
            System.out.println("Réponse du serveur : " + this.getMessage());
            System.out.println("Depuis : " + this.getAddressSender() + ":" + this.getPort());
            }
                
        }
    
    //main de la classe où on va créer un client et le lancer
    public static void main(String argv[]) throws Exception {
        ClientUDP c = new ClientUDP();
        c.lancerClient();
    }
}
