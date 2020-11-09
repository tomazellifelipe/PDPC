package src;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Trabalhador extends Thread {

    private Socket socket;
    private MulticastSocket multiSocket;
    private InetAddress grupo;

    public Trabalhador(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            String pesquisa = receberMsgDoCliente();
            conexaoUDPComLoja("224.0.0.1");
            enviarMsgParaGrupo(pesquisa);
            System.out.println("Mensagem enviado servidor-loja");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void conexaoUDPComLoja(String host) throws IOException {
        grupo = InetAddress.getByName(host);
        multiSocket = new MulticastSocket();
    }

    private String receberMsgDoCliente() throws IOException {
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        return entrada.readUTF();
    }

    private void enviarMsgParaGrupo(String msg) throws IOException {
        DatagramPacket pesquisar = new DatagramPacket(msg.getBytes(), msg.length(), grupo, 3000);
        multiSocket.send(pesquisar);
    }

}
