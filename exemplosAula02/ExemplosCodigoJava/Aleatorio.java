import java.util.Random;
//import java.util.concurrent.*;

public class Aleatorio
{
	static final private int TEMPO_MINIMO = 1000; // em milissegundos
	static final private int TEMPO_MAXIMO = 5000; // em milissegundos
	static final private int INTERVALO = TEMPO_MAXIMO - TEMPO_MINIMO;
	static private Random periodo;
	
	public static void main(String[] args)
	{
		periodo = new Random();
	
		try {
			int tempo = TEMPO_MINIMO + periodo.nextInt(INTERVALO); // em milissegundos
			System.out.println(" vai dormir " + tempo + " milissegundos ...");
			Thread.sleep( tempo );
			System.out.println(" acordou!");
			System.out.println(" comecando a trabalhar ..." );
			for (char letra = 'a'; letra < 'e'; letra++)
				System.out.println( " [" + letra + "]");
			System.out.println(" terminou de trabalhar." );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fim");		
	}
}
