import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    private static final double MIN = 1000d;
    private static final double MAX = 5000d;
    private static final int MAXFUNC = 100;

    public static int counter = 0;

    public static void main(String[] args) {
        /*
         * Uma lista de funcionario (obj funcionario) divide em 4 cada thread realiza
         * uma parte da lista apenas depois que terminar usa mutex pra acessar a parte
         * seguinte apos terminar as 4 partes usa barreira pra imprimir os cheques
         */
        Semaphore sem1 = new Semaphore(0);
        Semaphore sem2 = new Semaphore(0);
        Semaphore sem3 = new Semaphore(0);
        Semaphore sem4 = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);
        Semaphore barreira = new Semaphore(0);

        Funcionario[] funcionarios = new Funcionario[MAXFUNC];
        for (int i = 0; i < funcionarios.length; i++) {
            funcionarios[i] = new Funcionario(i, salario(MIN, MAX));

        }

        ImpRenda ir = new ImpRenda(funcionarios, sem1, sem2, sem3, sem4, mutex, barreira);
        PrevObrigatoria inss = new PrevObrigatoria(funcionarios, sem1, sem2, sem3, sem4, mutex, barreira);
        PrevPrivada prevPrivada = new PrevPrivada(funcionarios, sem1, sem2, sem3, sem4, mutex, barreira);
        PlanoDeSaude planoDeSaude = new PlanoDeSaude(funcionarios, sem1, sem2, sem3, sem4, mutex, barreira);

        ir.start();
        inss.start();
        prevPrivada.start();
        planoDeSaude.start();

    }

    private static double salario(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

}