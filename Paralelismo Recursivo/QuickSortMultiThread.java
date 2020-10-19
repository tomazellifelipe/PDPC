import java.util.concurrent.Semaphore;

// Java program for implementation of QuickSort 
class QuickSortMultiThread extends Thread implements IQuickSort {

    final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    static int runningThreads = 0;

    int low, high;
    int[] arr;
    Semaphore mutex;

    public QuickSortMultiThread(int[] arr, int low, int high, Semaphore mutex ) {
        this.arr = arr;
        this.low = low;
        this.high = high;
        this.mutex = mutex;
    }

    @Override
    public int partition(int[] arr, int low, int high) throws InterruptedException {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {

            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                mutex.acquire();
                    arr[i] = arr[j];
                    arr[j] = temp;
                mutex.release();
            }
        }

        int temp = arr[i + 1];
        mutex.acquire();
            arr[i + 1] = arr[high];
            arr[high] = temp;
        mutex.release();
        
        return i + 1;
    }

    @Override
    public void sort(int[] arr, int low, int high) throws InterruptedException {
        if (low < high) {
            int pi = partition(arr, low, high);

            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    public void sortMultiThread(int[] arr, int low, int high) throws InterruptedException {
        if (low < high) {
            int pi = partition(arr, low, high);

            if (runningThreads <= AVAILABLE_PROCESSORS) {
                QuickSortMultiThread left = new QuickSortMultiThread(arr, low, pi - 1, mutex);
                runningThreads++;

                QuickSortMultiThread right = new QuickSortMultiThread(arr, pi + 1, high, mutex);
                runningThreads++;

                left.start();
                right.start();

                left.join();
                right.join();
            } else {
                sort(arr, low, pi - 1);
                sort(arr, pi + 1, high);
            }

        }
    }

    public void run() {
        try {
            sortMultiThread(arr, low, high);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
} 