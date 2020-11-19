package src;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ComunicacaoVolta extends Thread {

    private DatagramSocket socketLoja;
    private Socket socketCliente;
    private int timeout;

    public ComunicacaoVolta(DatagramSocket socketLoja, Socket socketCliente, int timeout) {
        this.socketCliente = socketCliente;
        this.socketLoja = socketLoja;
        this.timeout = timeout;
    }

    public void run() {
        try {
            socketLoja.setSoTimeout(timeout);
            String msgDeRetorno = "";
            for (int i = 0; i < Loja.qtdLojas; i++) {
                msgDeRetorno += receberMsgDaLoja() + "\n"; 
            }
            enviarMsgParaCliente(msgDeRetorno);

        } catch (Exception e) {
            enviarMsgParaCliente("Timout");
            e.printStackTrace();
        }
    }

    private String receberMsgDaLoja() throws IOException {
        DatagramPacket retornoPacote = new DatagramPacket(new byte[1024], 1024);
        socketLoja.receive(retornoPacote);
        return new String(retornoPacote.getData(), retornoPacote.getOffset(), retornoPacote.getLength());
    }

    private void enviarMsgParaCliente(String msg) {
        try {
            DataOutputStream saida = new DataOutputStream(socketCliente.getOutputStream());
            saida.writeUTF(msg);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TIMEOUT");
        }
    }
}
