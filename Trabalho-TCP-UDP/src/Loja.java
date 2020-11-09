package src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Loja {

    private Produto[] catalogo = new Produto[6];
    private static MulticastSocket multiSocket;
    private static DatagramSocket socketServidor;
    private static InetAddress grupo, enderecoServidor;

    public Loja() {
        popularCatalogo();
    }

    public static void main(String[] args) {
        try {
            conexaoUDPComGrupo();
            String dadosTexto = receberMsgDoGrupo();
            System.out.println("Mensagem recebida pela Loja: " + dadosTexto);
            conexaoUDPComServidor();
            enviarMsgParaServidor("retorno");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popularCatalogo() {
        for (int i = 0; i < catalogo.length; i++) {
            catalogo[i] = new Produto();
        }
    }

    private static void conexaoUDPComGrupo() throws IOException {
        grupo = InetAddress.getByName("224.0.0.1");
        multiSocket = new MulticastSocket(3000);
        multiSocket.joinGroup(grupo);
    }

    private static String receberMsgDoGrupo() throws IOException {
        byte[] mensagemBytes = new byte[1000];
        DatagramPacket mensagemPacote = new DatagramPacket(mensagemBytes, mensagemBytes.length);
        multiSocket.receive(mensagemPacote);
        return new String(mensagemPacote.getData(), mensagemPacote.getOffset(), mensagemPacote.getLength());

    }

    private static void conexaoUDPComServidor() throws IOException {
        enderecoServidor = InetAddress.getByName("127.0.0.1");
        socketServidor = new DatagramSocket();
    }

    private static void enviarMsgParaServidor(String msg) throws IOException {
        byte[] retornoBytes = msg.getBytes();
        DatagramPacket retornoPacote = new DatagramPacket(retornoBytes, retornoBytes.length, enderecoServidor, 4545);
        socketServidor.send(retornoPacote);

    }
}
