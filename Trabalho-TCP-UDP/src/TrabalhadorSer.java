package src;

import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class TrabalhadorSer extends Thread {

    private DatagramSocket socket;
    private Socket socketCli;

    public TrabalhadorSer(DatagramSocket socket, Socket socketCli) {
        this.socket = socket;
        this.socketCli = socketCli;
    }

    public void run() {
        try {
            DatagramPacket retornoPacote = new DatagramPacket(new byte[512], 512);
            socket.receive(retornoPacote);
            String retornoTexto = new String(retornoPacote.getData(), retornoPacote.getOffset(), retornoPacote.getLength());
            System.out.println("Recebido da loja: " + retornoTexto);

            DataOutputStream saida = new DataOutputStream(socketCli.getOutputStream());
            saida.writeUTF(retornoTexto);
            socketCli.close();
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
