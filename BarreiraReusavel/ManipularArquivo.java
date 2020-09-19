import java.io.*;
import java.util.ArrayList;

public class ManipularArquivo implements Serializable {

    public static void salvar(String nomeDoArquivo, ArrayList<Integer> dados) throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nomeDoArquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(dados);
        gravador.close();
        arquivo.close();
    }

    public static ArrayList<Integer> abrir(String nomeDoArquivo) throws IOException, ClassNotFoundException {
        ArrayList<Integer> listaDeInts = null;
        FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);
        listaDeInts = (ArrayList<Integer>) restaurador.readObject();
        restaurador.close();
        arquivo.close();
        return listaDeInts;
    }

}
