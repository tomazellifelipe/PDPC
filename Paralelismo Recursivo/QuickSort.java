// Java program for implementation of QuickSort 
class QuickSort extends Thread {

    int low, high;
    int[] arr;

    public QuickSort(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    int partition(int[] arr, int low, int high) { 
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
  
    void sort(int arr[], int low, int high) throws InterruptedException {
        if (low < high) { 
            int pi = partition(arr, low, high); 
  
            QuickSort left = new QuickSort(arr, low, pi - 1); 
            QuickSort right = new QuickSort(arr, pi + 1, high);
            
            left.start();
            right.start();

            left.join();
            right.join();
            
        } 
    }

    public void run() {
        try {
            sort(arr, low, high);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int[] arr = { 54, 64, 95, 82, 12, 32, 63 };
        int n = arr.length;

        QuickSort qs = new QuickSort(arr, 0, n - 1);
        qs.start();
        qs.join();

        // Print shorted elements 
        for (int i = 0; i < n; i++) 
            System.out.print(arr[i] + " ");
    }
} 