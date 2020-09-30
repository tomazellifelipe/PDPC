package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ManipularArquivo {

    public static void escrever(ArrayList<Integer> aIntegers, String fileName) 
        throws IOException {

        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        for (Integer integer : aIntegers) {
            pw.println(integer);
        }

        pw.flush();
        pw.close();
        bw.close();
        fw.close();

    }

    public static ArrayList<Integer> ler(String fileName) 
        throws IOException, FileNotFoundException {
            
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        
        ArrayList<Integer> output = new ArrayList<>();
        String line = null;
        while ((line = br.readLine()) != null) {
            output.add(Integer.parseInt(line));
         }       
        br.close();
        fr.close();
        return output;
    }


}
