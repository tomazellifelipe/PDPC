import java.util.concurrent.*;

public class Tarefa extends Thread {

	private final int ID;
	private final Semaphore x;

	public Tarefa(final int ID, final Semaphore x) {
		this.ID = ID;
		this.x = x;
	}

	public void run() {
		System.out.println("Inicio da tarefa " + ID);
		try {
			Thread.sleep(1000);
		} catch (final Exception e) {
		}
		System.out.println("Fim da tarefa " + ID);
	}

	/*
	 * public void run() // com exclusao mutua entre clientes { try { x.acquire();
	 * System.out.println("Inicio da tarefa " + ID); try { Thread.sleep(1000); }
	 * catch( Exception e ) {} System.out.println("Fim da tarefa " + ID);
	 * x.release(); } catch (InterruptedException e) { e.printStackTrace(); } }
	 */
}
