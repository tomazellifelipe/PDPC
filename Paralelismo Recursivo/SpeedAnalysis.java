import java.util.concurrent.Semaphore;

public class SpeedAnalysis {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1);
        
        Sequence sequence = new Sequence((int) Math.pow(2, 15));
        int[] arrSequencial = sequence.getSequence();
        int[] arrMultiThread = arrSequencial.clone();
        int n = arrSequencial.length;
        
        QuickSort sequencial = new QuickSort();
        long sequencialTimeStart = System.currentTimeMillis();
        sequencial.sort(arrSequencial, 0, n - 1);
        long sequencialTimeEnd = System.currentTimeMillis();
        System.out.println("Sequencial Time: " + (sequencialTimeEnd - sequencialTimeStart));

        QuickSortMultiThread multiThread = new QuickSortMultiThread(arrMultiThread, 0, n - 1, mutex);
        long multiThreadTimeStart = System.currentTimeMillis();
        multiThread.start();
        try {
            multiThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long multiThreadTimeEnd = System.currentTimeMillis();
        System.out.println("MultiThread Time: " + (multiThreadTimeEnd - multiThreadTimeStart));
            
        
    }
    
}
