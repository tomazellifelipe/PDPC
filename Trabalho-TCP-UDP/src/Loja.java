package src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class Loja {

    private static int id;
    private static int porta, index;
    private static Produto[] catalogo = new Produto[6];
    private static MulticastSocket multiSocket;
    private static DatagramSocket socketServidor;
    private static InetAddress grupo, enderecoServidor;

    public Loja(int id) {
        popularCatalogo();
        this.id = id;
    }

    public static void main(String[] args) {
        Random r = new Random();
        new Loja(r.nextInt(5));
        try {
            conexaoUDPComGrupo("224.0.0.1", 3000);
            conexaoUDPComServidor("127.0.0.1", 4545);

            while (true) {
                String msgDoCliente = receberMsgDoGrupo();
                System.out.println("Mensagem recebida pela Loja: " + msgDoCliente);
                if(contemItem(msgDoCliente)) {
                    enviarMsgParaServidor(formatarMsgDeRetorno()); 
                } else {
                    enviarMsgParaServidor("Loja: " + id + "\n" + 
                                          "\tProduto em falta");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popularCatalogo() {
        for (int i = 0; i < catalogo.length; i++) {
            catalogo[i] = new Produto();
        }
    }

    private static void conexaoUDPComGrupo(String host, int port) throws IOException {
        grupo = InetAddress.getByName(host);
        multiSocket = new MulticastSocket(port);
        multiSocket.joinGroup(grupo);
    }

    private static String receberMsgDoGrupo() throws IOException {
        byte[] mensagemBytes = new byte[1000];
        DatagramPacket mensagemPacote = new DatagramPacket(mensagemBytes, mensagemBytes.length);
        multiSocket.receive(mensagemPacote);
        return new String(mensagemPacote.getData(), mensagemPacote.getOffset(), mensagemPacote.getLength());
    }

    private static void conexaoUDPComServidor(String host, int port) throws IOException {
        enderecoServidor = InetAddress.getByName(host);
        socketServidor = new DatagramSocket();
        porta = port;
    }

    private static void enviarMsgParaServidor(String msg) throws IOException {
        byte[] retornoBytes = msg.getBytes();
        DatagramPacket retornoPacote = new DatagramPacket(retornoBytes, retornoBytes.length, enderecoServidor, porta);
        socketServidor.send(retornoPacote);
    }

    private static boolean contemItem(String busca) {
        for (int i = 0; i < catalogo.length; i++) {
            if(catalogo[i].getNome().equals(busca)) {
                index = i;
                return true;
            }            
        }
        return false;
    }

    private static String formatarMsgDeRetorno() {
        String item = catalogo[index].getNome();
        float preco = catalogo[index].getPreco();
        return "Loja: " + id + "\n" +
               "\tProd: " + item + "\n" +
               "\tPreco: " + preco;
    }
}
