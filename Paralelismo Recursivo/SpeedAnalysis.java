import java.util.concurrent.Semaphore;

public class SpeedAnalysis {
    public static void main(String[] args) throws InterruptedException {
        Semaphore mutex = new Semaphore(1);

        Sequence sequence = new Sequence(50);
        int[] arr = sequence.getSequence();
        int n = arr.length;
        
        QuickSort sequencial = new QuickSort(arr, 0, n - 1, mutex);
        sequencial.sort(arr, 0, n - 1);
        
        QuickSort multiThread = new QuickSort(arr, 0, n - 1, mutex);
            
        multiThread.start();
        multiThread.join();
            
        // Print shorted elements 
        for (int i = 0; i < n; i++) 
            System.out.print(arr[i] + " ");
    }
    
}
