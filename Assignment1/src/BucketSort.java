import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
    public static int[] bucketSort(int[] flowDuration){

        int numberOfBuckets = (int) Math.sqrt(flowDuration.length);
        ArrayList[] buckets = new ArrayList[numberOfBuckets];

        int max = flowDuration[0];
        for (int i = 0; i < flowDuration.length; i++) {
            if (flowDuration[i]>max){
                max = flowDuration[i];
            }
        }

        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList();
        }

        for(int i : flowDuration){
            buckets[hash(i,max,numberOfBuckets)].add(i);
        }

        for (ArrayList bucket : buckets){
            Collections.sort(bucket);
        }

        ArrayList<Integer> sortedArrayList = new ArrayList<>();
        for(ArrayList bucket : buckets){
            for (int i = 0; i < bucket.size(); i++) {
                sortedArrayList.add((Integer) bucket.get(i));
            }
        }

        for (int i = 0; i < flowDuration.length; i++) {
            flowDuration[i] = sortedArrayList.get(i);
        }


        return flowDuration;
    }

    public static int hash(int i,int max,int numberOfBuckets){
        return (int) Math.ceil(i/(max*(numberOfBuckets-1)));
    }

    public static double[] measureBucketSort(int[] inputAxis,String mode,String filename){
        double total = 0;
        double[] Time = new double[inputAxis.length];
        for (int i = 0; i < inputAxis.length; i++) {
            total=0;
            int size = inputAxis[i];
            int [] flowDuration2 = new int[size];
            for (int j = 0; j < 10; j++) {
                int flowDuration[] = ReadCSV.Read(filename);
                if (mode=="s"){
                    // make array sorted
                    QuickSort.quickSort(flowDuration,0,size);
                }
                else if(mode=="rs"){
                    // sort and reverse
                    QuickSort.quickSort(flowDuration,0,size);
                    int[] b = new int[size];
                    int x = size;
                    for (int k = 0; k < size; k++) {
                        b[x - 1] = flowDuration[k];
                        x = x - 1;
                    }
                    for (int a = 0; a < size; a++) {
                        flowDuration[a]=b[a];
                    }

                }

                for (int k = 0; k < size; k++) {
                    flowDuration2[k]=flowDuration[k];
                }

                long start = System.nanoTime();

                BucketSort.bucketSort(flowDuration2);
                long duration = (System.nanoTime()-start);


                total+=duration;
            }
            total=total/10000000;
            Time[i]=total;
        }
        return Time;
    }
}
