package src;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class ComunicacaoVolta extends Thread {

    private DatagramSocket socketLoja;
    private Socket socketCliente;

    public ComunicacaoVolta(DatagramSocket socketLoja, Socket socketCliente) {
        this.socketCliente = socketCliente;
        this.socketLoja = socketLoja;
    }

    public void run() {
        try {
            String msgDeRetorno = "";
            for (int i = 0; i < Loja.qtdLojas; i++) {
                msgDeRetorno += receberMsgDaLoja() + "\n"; 
            }
            enviarMsgParaCliente(msgDeRetorno);

        } catch (Exception e) {
            System.out.println("Timeout");
            e.printStackTrace();
        }
    }

    private String receberMsgDaLoja() throws IOException {
        DatagramPacket retornoPacote = new DatagramPacket(new byte[1024], 1024);
        socketLoja.receive(retornoPacote);
        return new String(retornoPacote.getData(), retornoPacote.getOffset(), retornoPacote.getLength());
    }

    private void enviarMsgParaCliente(String msg) throws IOException {
        DataOutputStream saida = new DataOutputStream(socketCliente.getOutputStream());
        saida.writeUTF(msg);
    }
}
