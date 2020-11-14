package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor extends Thread {

    private int portaGrupo, timeout;
    private DatagramSocket socketLoja;
    private InetAddress grupo;
    private MulticastSocket multiSocket;
    private ServerSocket socketServidor;
    private Socket socketCliente;

    private ArrayList<String> logDePesquisas = new ArrayList<>();

    public void run() {
        timeout = 10000;
        try {
            socketServidor = new ServerSocket(5050);
            socketLoja = new DatagramSocket(4545);
            
            while (true) {
                socketLoja.setSoTimeout(timeout);
                socketCliente = socketServidor.accept();
                if (ehAdmin()) {
                    logDePesquisas.add("admin");
                    enviarMsgParaAdmin(concatenarLog());
                    enviarTimeoutParaAdmin();
                    timeout = atualizarTimeout();

                } else {
                    String pesquisa = receberMsgDoCliente();
                    String hashId = receberMsgDoCliente();
                    logDePesquisas.add(pesquisa + hashId);
                    conexaoUDPComLoja("224.0.0.1", 3000);
                    enviarMsgParaGrupo(pesquisa);
                    ComunicacaoVolta comsVolta = new ComunicacaoVolta(socketLoja, socketCliente);
                    comsVolta.start();
                }
                

            }
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
        DataInputStream entrada = new DataInputStream(socketCliente.getInputStream());
        return entrada.readUTF();
    }

    private void enviarMsgParaGrupo(String msg) throws IOException {
        DatagramPacket pesquisar = new DatagramPacket(msg.getBytes(), msg.length(), grupo, portaGrupo);
        multiSocket.send(pesquisar);
    }

    private boolean ehAdmin() throws IOException {
        DataInputStream entrada = new DataInputStream(socketCliente.getInputStream());
        return entrada.readBoolean();
    }

    private void enviarMsgParaAdmin(String msg) throws IOException {
        DataOutputStream saida = new DataOutputStream(socketCliente.getOutputStream());
        saida.writeUTF(msg);
    }

    private String concatenarLog() {
        String logCompleto = "";
        for (String log : logDePesquisas) {
            logCompleto += log + "\n";
        }
        return logCompleto;
    }

    private void enviarTimeoutParaAdmin() throws IOException {
        DataOutputStream saida = new DataOutputStream(socketCliente.getOutputStream());
        saida.writeInt(timeout);
    }

    private int atualizarTimeout() throws IOException {
        DataInputStream entrada = new DataInputStream(socketCliente.getInputStream());
        return entrada.readInt();
    }

}
