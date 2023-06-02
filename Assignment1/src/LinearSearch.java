import java.util.Random;

public class LinearSearch {
    public static int linearSearch(int[] flowDuration,int x){
        int size = flowDuration.length;
        for (int i = 0; i < size; i++) {
            if (flowDuration[i]==x){
                return i;
            }
        }
        return -1;
    }

    public static double[] measureLinearSearch(int[] inputAxis,String mode,String filename) {

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

            if (mode=="s") {
                flowDuration=BucketSort.bucketSort(flowDuration);
            }


            for (int j = 0; j < 1000; j++) {
                int randomIndex = new Random().nextInt(flowDuration.length);
                long start = System.nanoTime();

                LinearSearch.linearSearch(flowDuration,flowDuration[randomIndex]);

                long duration = (System.nanoTime()-start);


                total+=duration;
            }
            total=total/1000;
            Time[i]=total;
        }
        return Time;
    }
}
