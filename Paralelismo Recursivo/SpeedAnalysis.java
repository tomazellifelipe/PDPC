import java.util.concurrent.Semaphore;

public class SpeedAnalysis {

    static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1);
        
        meanTimesForAllSequences(mutex);
        meanTimesForIncreasingThreads(mutex);
    }

    private static void meanTimesForAllSequences(Semaphore mutex) {
        for (int i = 15; i <= 26; i++) {
            System.out.println("Sequencia de comprimento 2^" + i);
            long tempoSequencial = 0; 
            long tempoMultiThread = 0;
            for (int j = 0; j < 50; j++) {
                
                Sequence sequence = new Sequence((int) Math.pow(2, i));
                int[] arrSequencial = sequence.getSequence();
                int[] arrMultiThread = arrSequencial.clone();
                int n = arrSequencial.length;
                
                QuickSort sequencial = new QuickSort();
                long sequencialTimeStart = System.currentTimeMillis();
                sequencial.sort(arrSequencial, 0, n - 1);
                long sequencialTimeEnd = System.currentTimeMillis();
                tempoSequencial = tempoSequencial + (sequencialTimeEnd - sequencialTimeStart);
        
                QuickSortMultiThread multiThread = new QuickSortMultiThread(arrMultiThread, 0, n - 1, mutex, AVAILABLE_PROCESSORS);
                long multiThreadTimeStart = System.currentTimeMillis();
                multiThread.start();
                try {
                    multiThread.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long multiThreadTimeEnd = System.currentTimeMillis();
                tempoMultiThread = tempoMultiThread + (multiThreadTimeEnd - multiThreadTimeStart);
            }
            System.out.println("\tSequencial: " + (tempoSequencial / 50));
            System.out.println("\tMultiThread: " + (tempoMultiThread / 50));
        }
    }

    private static void meanTimesForIncreasingThreads(Semaphore mutex) {
        for (int i = 1; i <= AVAILABLE_PROCESSORS; i++) {
            System.out.println("Available Processors: " + i);
            System.out.println("Sequencia de comprimento 2^" + 26);
            long tempoMultiThread = 0;

            for (int j = 0; j < 50; j++) {
                Sequence sequence = new Sequence((int) Math.pow(2, 26));
                int[] arrMultiThread = sequence.getSequence();
                int n = arrMultiThread.length;
        
                QuickSortMultiThread multiThread = new QuickSortMultiThread(arrMultiThread, 0, n - 1, mutex, i);
                long multiThreadTimeStart = System.currentTimeMillis();
                multiThread.start();
                try {
                    multiThread.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long multiThreadTimeEnd = System.currentTimeMillis();
                tempoMultiThread = tempoMultiThread + (multiThreadTimeEnd - multiThreadTimeStart);
            }

            System.out.println("\tMultiThread: " + (tempoMultiThread / 50));
        }

    }
    
}
