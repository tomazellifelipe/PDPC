import java.io.*;

public class ManipularArquivo implements Serializable {

    public static void salvar(String nome, ListaDeInteiros objeto) throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nome);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(objeto);
        gravador.close();
        arquivo.close();
    }

    public static ListaDeInteiros abrir(String nomeDoArquivo) throws IOException, ClassNotFoundException {
        ListaDeInteiros output = null;
        FileInputStream arquivo = new FileInputStream(nomeDoArquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);
        output = (ListaDeInteiros) restaurador.readObject();
        restaurador.close();
        arquivo.close();
        return output;
    }

}
