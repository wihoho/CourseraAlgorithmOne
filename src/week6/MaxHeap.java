package week6;

/**
 * Created by GongLi on 15/6/14.
 */
public class MaxHeap {

    int totalArraySize;
    int[] arr;
    int size;

    public MaxHeap(int totalSize){
        totalArraySize = totalSize + 1;
        arr = new int[totalArraySize];
        size = 0;
    }


    public void insert(int x) throws Exception {
        if(size == totalArraySize - 1) throw new Exception("Heap is full");
        arr[++size] = x;
        swim(size);
    }

    public int deleteMax() throws Exception {
        if(size == 0) throw new Exception("Size is 0!");

        int ret = arr[1];
        swap(1, size);
        size --;
        sink(1);


        return ret;
    }

    public int getMax(){
        return arr[1];
    }

    private void sink(int k){

        while (2 * k <= size){
            int maxIndex = k;
            if(arr[2*k] > arr[k]) maxIndex = 2 * k;
            if(2 * k + 1 <= size && arr[2*k+1] > arr[maxIndex]) maxIndex = 2*k + 1;

            if(maxIndex == k) return;

            swap(maxIndex, k);
            k = maxIndex;
        }
    }

    private void swim(int k){

        while(k >= 2){

            if(arr[k / 2] < arr[k]){
                swap(k, k /2);
                k = k /2;
            }
            else break;
        }
    }

    public void swap(int a, int b){
        int tem= arr[a];
        arr[a] = arr[b];
        arr[b] = tem;
    }
}
