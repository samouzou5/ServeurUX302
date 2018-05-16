package communciationUDP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EpulInfo
 */
public class ClientServer {

    public DatagramSocket ds;
    public DatagramPacket dp;
    public InetAddress iadd_server;
    public int port;
    public byte[] buffer = new byte[512];
    public final static int PORT = 8532;
    List<Integer> listeClients = new ArrayList<>();

    /**
     * Récupère la liste des ports disponibles entre deb et fin
     * @param deb //port de début
     * @param fin //port de fin
     * @return 
     */
    public static List<Integer> scan(int deb, int fin) {
        List<Integer> listePorts = new ArrayList<>();
        for (int i = deb; i <= fin; i++) {
            try {
                DatagramSocket data = new DatagramSocket(i);
                data.close();
                listePorts.add(i);
            } catch (SocketException ex) {
                
            }
        }
        return listePorts;
    }
    
    /**
     * 
     * @return Retourne le Datagram Socket intialisé sur un port
     */
    public DatagramSocket getDatagramSocket(){
        return ds;
    }
    
    /**
     * Constructeur par défaut, le datagram socket choisira un port lui même
     */
    public ClientServer(){
        try {
            ds = new DatagramSocket();
        } catch (SocketException ex) {
            ex.getMessage();
        }
        try {
            //pour changer la destination (ip) changer la valeur de la ligne ci-dessous
            iadd_server = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            ex.getMessage();
        }
        
    }

    /**
     * Constructeur avec un port en paramètre
     * @param port port sur lequel se déroule la communiction
     */
    public ClientServer(int port){
        try {
            this.ds = new DatagramSocket(port);
        } catch (SocketException ex) {
            ex.getMessage();
        }
        this.port = port;
        try {
            //pour changer la destination (ip) changer la valeur de la ligne ci-dessous
            iadd_server = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            ex.getMessage();
        }
    }
    
    /**
     * permet d'envoyer un message
     * @param i adresse ip de destination
     * @param port port de destination
     * @param message message à envoyer
     */
    public void sendMessage(InetAddress i, int port,String message){
        buffer = new byte[512];
        buffer = message.getBytes();
        dp = new DatagramPacket(buffer,buffer.length,i,port);
        try {
            ds.send(dp);
            Arrays.fill(buffer, (byte)0); //vider le tableau
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    /**
     * 
     * @return récupérer le message reçu par le serveur ou le client
     */
    public String getMessage(){
        return new String(buffer,StandardCharsets.UTF_8);
    }
    
    /**
     * permet de récupérer le paquet envoyé
     */
    public void receivePacket(){
        buffer = new byte[512];
        dp = new DatagramPacket(buffer, buffer.length);
        try {
            ds.receive(dp);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    /**
     * Retourne l'adresse de l'émetteur
     * @return 
     */
    public InetAddress getAddressSender() {
        return dp.getAddress();
    }

    /**
     * Retourne le nom d'hôte 
     * @return 
     */
    public String getHostName() {
        return dp.getAddress().getHostAddress();
    }

    /**
     * permet de fermer la socket
     */
    public void closeSocket() {
        ds.close();
    }

    /**
     * 
     * @return permet de récupérer le port utilisé par le client ou le serveur 
     */
    public int getPort() {
        return dp.getPort();
    }
    
    /**
     * 
     * @param portDeb port de départ
     * @param portFin port fin
     * @return récupère un port dispo pour une communication
     */
    public static int catchPortAvailable(int portDeb, int portFin){
        List<Integer> listePortUtil = scan(portDeb, portFin);
        return listePortUtil.get(0);
    }
}
