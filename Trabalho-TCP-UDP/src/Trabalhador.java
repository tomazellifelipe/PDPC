package src;

import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class Trabalhador extends Thread {

    private Socket socket;

    public Trabalhador(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String pesquisa = entrada.readUTF();
            socket.close();
            System.out.println("Conexao encerrada com cliente");

            InetAddress grupo = InetAddress.getByName("224.0.0.1");
            MulticastSocket multiSocket = new MulticastSocket();
            DatagramPacket pesquisar = new DatagramPacket(pesquisa.getBytes(), pesquisa.length(), grupo, 3000);
            multiSocket.send(pesquisar);
            System.out.println("Mensagem enviado servidor-loja");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
