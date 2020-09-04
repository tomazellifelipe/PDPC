import java.util.concurrent.Semaphore;

public class PrevPrivada extends Thread {

    private Funcionario[] funcionarios;
    private Semaphore sem1, sem2, sem3, sem4, mutex, barreira;

    public PrevPrivada(Funcionario[] lista, Semaphore sem1, Semaphore sem2, Semaphore sem3, Semaphore sem4,
            Semaphore mutex, Semaphore barreira) {
        this.funcionarios = lista;
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.sem3 = sem3;
        this.sem4 = sem4;
        this.mutex = mutex;
        this.barreira = barreira;
    }

    public void run() {
        try {

            calcular(50, sem3);
            sem4.acquire();
            calcular(75, sem4);
            sem1.acquire();
            calcular(0, sem1);
            sem2.acquire();
            calcular(25, sem2);

            mutex.acquire();
            Main.counter++;
            if (Main.counter == 4) {
                barreira.release();
            }
            mutex.release();

            barreira.acquire();
            Imprimir.escrever(funcionarios, 50, "parte3");
            barreira.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calcular(int ini, Semaphore sem) {
        for (int i = ini; i < ini + (funcionarios.length) / 4; i++) {
            double desconto = calcularPrevP(funcionarios[i].getSalario());
            funcionarios[i].setPrevPrivada(desconto);
            funcionarios[i].atualizarImpostos(desconto);
            sem.release();
        }
    }

    private double calcularPrevP(double salario) {
        return salario * .04;
    }

}
