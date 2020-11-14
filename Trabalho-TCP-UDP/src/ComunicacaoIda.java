package src;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ComunicacaoIda extends Thread {

    private Socket socket;
    private MulticastSocket multiSocket;
    private InetAddress grupo;
    private int portaGrupo;
    private ArrayList<String> logDePesquisas;

    public ComunicacaoIda(Socket socket, ArrayList<String> logDePesquisas) {
        this.socket = socket;
        this.logDePesquisas = logDePesquisas;
    }

    public void run() {
        try {
            String pesquisa = receberMsgDoCliente();
            String hashId = receberMsgDoCliente();
            logDePesquisas.add(pesquisa + hashId);
            conexaoUDPComLoja("224.0.0.1", 3000);
            enviarMsgParaGrupo(pesquisa);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void conexaoUDPComLoja(String host, int port) throws IOException {
        grupo = InetAddress.getByName(host);
        multiSocket = new MulticastSocket();
        portaGrupo = port;
    }

    private String receberMsgDoCliente() throws IOException {
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        return entrada.readUTF();
    }

    private void enviarMsgParaGrupo(String msg) throws IOException {
        DatagramPacket pesquisar = new DatagramPacket(msg.getBytes(), msg.length(), grupo, portaGrupo);
        multiSocket.send(pesquisar);
    }

}
