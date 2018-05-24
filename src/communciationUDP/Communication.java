package communciationUDP;

import java.net.InetAddress;

/**
 *
 * @author EpulInfo
 * permet la communication entre un client et le serveur
 */
public class Communication extends ClientServer implements Runnable {

    /**
     * Constructeur de la classe communication
     * @param iaClient adresse du client
     * @param portClient port du client
     * @param message premier message reçu
     */
    public Communication(InetAddress iaClient,int portClient,String message){
        //on récupère le premier port disponible entre 2500 et 10000
        super(catchPortAvailable(2500, 10000));
        //sur le serveur on affiche le premier message du client nouvellement connecté
        showFirstMessage(iaClient,portClient,message);
        //on lui répond ici pour que le client connaisse le port de communication avec le serveur
        sendMessage(iaClient,portClient,message);
    }
   /**
    * redéfinition de la méthode run : on se met en attente d'un paquet, on l'affiche et on répond au client
    */
    @Override
    public void run() {
        String ch = "";
        while(!ch.equals("ciao")){
            receivePacket();
            System.out.println("Message du client " + getAddressSender() + ":" + getPort() + ", il dit : " + getMessage());
            ch = getMessage();
            sendMessage(getAddressSender(), getPort(),ch);
        }
    }
    
    
}
