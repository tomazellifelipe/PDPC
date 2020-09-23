import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barbearia {

    private boolean barbeiro;
    private boolean cadeira;
    private boolean aberta;

    private final Lock mutex = new ReentrantLock();
    private final Condition barbeiro_disponivel = mutex.newCondition();
    private final Condition cadeira_ocupada = mutex.newCondition();
    private final Condition porta_aberta = mutex.newCondition();
    private final Condition cliente_saiu = mutex.newCondition();

    public Barbearia() {
        this.barbeiro = false;
        this.cadeira = false;
        this.aberta = false;

    }

    public void corteCabelo() throws InterruptedException {
        mutex.lock();
        try {
            while (barbeiro == false)
                barbeiro_disponivel.await();
            barbeiro = false;
            cadeira = true;
            cadeira_ocupada.signal();
            while (aberta == false)
                porta_aberta.await();
            aberta = false;
            cliente_saiu.signal();

        } finally {
            mutex.unlock();
        }
    }

    public void pegueProximoCliente() throws InterruptedException {
        mutex.lock();
        try {
            barbeiro = true;
            barbeiro_disponivel.signal();
            while (cadeira == false)
                cadeira_ocupada.await();
            cadeira = false;

        } finally {
            mutex.unlock();
        }
    }

    public void termineCorte() throws InterruptedException {
        mutex.lock();
        try {
            aberta = true;
            porta_aberta.signal();
            while (aberta == true)
                cliente_saiu.await();

        } finally {
            mutex.unlock();
        }
    }

}
