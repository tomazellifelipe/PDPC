package src;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            DatagramSocket socketLoja = new DatagramSocket(4545);
    
            while (true) {
                System.out.println("Aguardando conexao");
                Socket socketCliente = serverSocket.accept();
                System.out.println("Conexao estabelecida");
                
                Trabalhador trab = new Trabalhador(socketCliente);
                trab.start();
                TrabalhadorSer trabSer = new TrabalhadorSer(socketLoja, socketCliente);
                trabSer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
