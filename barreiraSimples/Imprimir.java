import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Imprimir {

    public static void escrever(Funcionario[] funcionarios, int ini, String fileString) throws IOException {
        FileWriter fw = new FileWriter(fileString + ".txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        for (int i = ini; i < ini + (funcionarios.length) / 4; i++) {
            pw.println("ID: " + funcionarios[i].getCodigo() + "\t\t\t"
                    + (funcionarios[i].getSalario() - funcionarios[i].getTotalImpostos()));
            pw.println("IR: " + funcionarios[i].getIR());
            pw.println("INSS: " + funcionarios[i].getINSS());
            pw.println("Prev. Privada: " + funcionarios[i].getPrevPrivada());
            pw.println("Plano de Saude: " + funcionarios[i].getPlanoDeSaude());
            pw.println();

        }
        pw.flush();
        pw.close();
        bw.close();
        fw.close();

    }

}
