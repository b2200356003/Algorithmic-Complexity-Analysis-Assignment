public class SelectionSort {
    public static void selectionSort(int[] flowDuration, int size) {
        for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (flowDuration[j] < flowDuration[index]) {
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = flowDuration[index];
            flowDuration[index] = flowDuration[i];
            flowDuration[i] = smallerNumber;
        }
    }

    public static double[] measureSelectionSort(int[] inputAxis,String mode,String filename){

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
                SelectionSort.selectionSort(flowDuration,size);
                long duration = (System.nanoTime()-start);

                total+=duration;
            }
            total=total/10000000;
            Time[i]=total;
        }
        return Time;
    }
}
