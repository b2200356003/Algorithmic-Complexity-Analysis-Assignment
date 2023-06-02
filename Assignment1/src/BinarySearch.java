import java.util.Random;

public class BinarySearch {
    public static int binarySearch(int[] flowDuration,int x){
        int low = 0;
        int high = flowDuration.length-1;
        while ((high-low)>1){
            int mid = (high+low)/2;
            if (flowDuration[mid]<x){
                low = mid+1;
            }
            else {
                high=mid;
            }
        }
        if (flowDuration[low]==x){
            return low;
        }
        else if (flowDuration[high]==x){
            return high;
        }
        return -1;
    }

    public static double[] measureBinarySearch(int[] inputAxis,String filename) {
        double total = 0;
        double[] Time = new double[inputAxis.length];

        for (int i = 0; i < inputAxis.length; i++) {
            total=0;
            int size = inputAxis[i];
            int[] temp = ReadCSV.Read(filename);
            int[] flowDuration = new int[size];
            for (int k = 0; k < size; k++) {
                flowDuration[k]=temp[k];
            }

            flowDuration=BucketSort.bucketSort(flowDuration);

            for (int j = 0; j < 1000; j++) {
                int randomIndex = new Random().nextInt(flowDuration.length);
                long start = System.nanoTime();
                BinarySearch.binarySearch(flowDuration,flowDuration[randomIndex]);
                long duration = (System.nanoTime()-start);

                total+=duration;
            }
            total=total/1000;
            Time[i]=total;
        }

        return Time;
    }
}
