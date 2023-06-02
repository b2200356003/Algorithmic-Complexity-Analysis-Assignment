public class QuickSort {
    public static void quickSort(int[] flowDuration,int low,int high){
        int stackSize = high-low+1;
        int[] stack = new int[stackSize];
        int top = -1;
        stack[++top]=low;
        stack[++top]=high;
        while (top>=0){
            high=stack[top--];
            low=stack[top--];
            int pivot = Partition(flowDuration,low,high);
            if (pivot-1>low){
                stack[++top]=low;
                stack[++top]=pivot-1;
            }
            if (pivot+1<high){
                stack[++top]=pivot+1;
                stack[++top]=high;
            }
        }
    }


    public static int Partition(int[] flowDuration,int low,int high){
        int pivot = flowDuration[high];
        int i = low-1;
        for (int j = low; j <high; j++) {
            if (flowDuration[j]<=pivot){
                i++;
                int temp = flowDuration[i];
                flowDuration[i] = flowDuration[j];
                flowDuration[j] = temp;
            }
        }
        int temp2 = flowDuration[i+1];
        flowDuration[i+1] = flowDuration[high];
        flowDuration[high] = temp2;
        return i+1;
    }

    public static double[] measureQuickSort(int[] inputAxis,String mode,String filename){

        double total = 0;
        double[] Time = new double[inputAxis.length];
        for (int i = 0; i < inputAxis.length; i++) {
            total=0;
            int size = inputAxis[i];
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

                long start = System.nanoTime();
                QuickSort.quickSort(flowDuration,0,size);
                long duration = (System.nanoTime()-start);

                total+=duration;
            }
            total=total/10000000;
            Time[i]=total;
        }
        return Time;
    }
}
