package src;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Loja {
    
    private Produto[] catalogo = new Produto[6];

    public Loja() {
        popularCatalogo();
    }

    public static void main(String[] args) {
        
        try {
            InetAddress grupo = InetAddress.getByName("224.0.0.1");
            MulticastSocket multiSocket = new MulticastSocket(3000);
            multiSocket.joinGroup(grupo);

            byte[] mensagemBytes = new byte[1000];
            DatagramPacket mensagemPacote = new DatagramPacket(mensagemBytes, mensagemBytes.length);
            multiSocket.receive(mensagemPacote);

            String dadosTexto = new String(mensagemPacote.getData(), mensagemPacote.getOffset(), mensagemPacote.getLength());
            System.out.println("Mensagem recebida pela Loja: " + dadosTexto);

            DatagramSocket socket_servidor = new DatagramSocket();
            InetAddress enderecoServidor = InetAddress.getByName("127.0.0.1");
            int portaServidor = 4545;
            String retorno = "retorno";
            byte[] retornoBytes = retorno.getBytes();
            DatagramPacket retornoPacote = new DatagramPacket(retornoBytes, retornoBytes.length, enderecoServidor, portaServidor);
            socket_servidor.send(retornoPacote);


        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private void popularCatalogo() {
        for(int i = 0; i < catalogo.length; i++) {
            catalogo[i] = new Produto();
        }
    }
}
