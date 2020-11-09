package src;

import java.io.DataInput;
import java.io.DataInputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
    
            while (true) {
                System.out.println("Aguardando conexao");
                Socket socketCliente = serverSocket.accept();
                System.out.println("Conexao estabelecida");
                
                Trabalhador trab = new Trabalhador(socketCliente);
                trab.start();
                int portaLoja = 4545;
                DatagramSocket socketLoja = new DatagramSocket(portaLoja);
                TrabalhadorSer trabSer = new TrabalhadorSer(socketLoja, socketCliente);
                trabSer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
