package src;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket socketServidor = new ServerSocket(5050);
            DatagramSocket socketLoja = new DatagramSocket(4545);
    
            while (true) {
                System.out.println("Aguardando conexao");
                Socket socketCliente = socketServidor.accept();
                System.out.println("Conexao estabelecida");
                
                ComunicacaoIda trab = new ComunicacaoIda(socketCliente);
                trab.start();
                ComunicacaoVolta trabSer = new ComunicacaoVolta(socketLoja, socketCliente);
                trabSer.start();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
