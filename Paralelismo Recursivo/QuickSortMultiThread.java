import java.util.concurrent.Semaphore;

class QuickSortMultiThread extends Thread implements IQuickSort {

    final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    static int runningThreads = 0;

    private int low, high;
    private int[] arr;
    private Semaphore mutex;

    public QuickSortMultiThread(int[] arr, int low, int high, Semaphore mutex) {
        this.arr = arr;
        this.low = low;
        this.high = high;
        this.mutex = mutex;
    }

    public void run() {
        runningThreads++;
        sortMultiThread(arr, low, high);
        runningThreads--;
    }

    public void sortMultiThread(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            if (runningThreads < AVAILABLE_PROCESSORS - 1) {
                QuickSortMultiThread left = new QuickSortMultiThread(arr, low, pi - 1, mutex);
                QuickSortMultiThread right = new QuickSortMultiThread(arr, pi + 1, high, mutex);

                left.start();
                right.start();
                try {
                    left.join();
                    right.join();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sort(arr, low, pi - 1);
                sort(arr, pi + 1, high);
            }

        }
    }

    @Override
    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {

            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                
                arr[i] = arr[j];
                arr[j] = temp;
                    
            }
        }

        int temp = arr[i + 1];
        
        arr[i + 1] = arr[high];
        arr[high] = temp;
            
        return i + 1;
    }

    @Override
    public void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }
} 