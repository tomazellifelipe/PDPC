import java.util.concurrent.Semaphore;

public class SpeedAnalysis {
    public static void main(String[] args) throws InterruptedException {
        Semaphore mutex = new Semaphore(1);

        Sequence sequence = new Sequence(50);
        int[] arrSequencial = sequence.getSequence();
        int[] arrMultiThread = sequence.getSequence();
        int n = arrSequencial.length;
        
        QuickSort sequencial = new QuickSort(arrSequencial, 0, n - 1);
        sequencial.sort(arrSequencial, 0, n - 1);

        QuickSortMultiThread multiThread = new QuickSortMultiThread(arrMultiThread, 0, n - 1, mutex);
            
        multiThread.start();
        multiThread.join();
            
        // Print shorted elements 
        for (int i = 0; i < n; i++) 
            System.out.print(arrSequencial[i] + " ");

        System.out.println();
        
        for (int i = 0; i < n; i++) 
            System.out.print(arrMultiThread[i] + " ");
    }
    
}
