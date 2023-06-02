import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.IOException;
import java.util.Arrays;

class Main {
    public static void main(String args[]) throws IOException {
        // X axis data
        int[] inputAxis = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
        String filename = args[0];

        // Create sample data for linear runtime
        double[][] yAxis = new double[3][10];
        double[][] yAxis2 = new double[3][10];
        double[][] yAxis3 = new double[3][10];
        double[][] yAxis4 = new double[3][10];

        yAxis[0] = SelectionSort.measureSelectionSort(inputAxis,"r",filename); // selection sort random input
        yAxis[1] = QuickSort.measureQuickSort(inputAxis,"r",filename); // quicksort random input
        yAxis[2] = BucketSort.measureBucketSort(inputAxis,"r",filename); // bucketsort random input

        yAxis2[0] = SelectionSort.measureSelectionSort(inputAxis,"s",filename); // selection sort sorted input
        yAxis2[1] = QuickSort.measureQuickSort(inputAxis,"s",filename); // quicksort sorted input
        yAxis2[2] = BucketSort.measureBucketSort(inputAxis,"s",filename); // bucketsort sorted input

        yAxis3[0] = SelectionSort.measureSelectionSort(inputAxis,"rs",filename); // selection sort reversed input
        yAxis3[1] = QuickSort.measureQuickSort(inputAxis,"rs",filename); // quicksort reversed input
        yAxis3[2] = BucketSort.measureBucketSort(inputAxis,"rs",filename);// bucketsort reversed input

        yAxis4[0] = LinearSearch.measureLinearSearch(inputAxis,"r",filename);
        yAxis4[1] = LinearSearch.measureLinearSearch(inputAxis,"s",filename);
        yAxis4[2] = BinarySearch.measureBinarySearch(inputAxis,filename);




        // Save the char as .png and show it


        showAndSaveChart("Random Inputs", inputAxis, yAxis,"sort");
        showAndSaveChart("Sorted Inputs",inputAxis,yAxis2,"sort");
        showAndSaveChart("Reverse Sorted Inputs",inputAxis,yAxis3,"sort");
        showAndSaveChart("Search",inputAxis,yAxis4,"search");


    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis,String mode) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        if (mode=="sort") {
            chart.addSeries("Selection Sort", doubleX, yAxis[0]);
            chart.addSeries("Quick Sort", doubleX, yAxis[1]);
            chart.addSeries("Bucket Sort", doubleX, yAxis[2]);
        }

        else if(mode == "search"){
            chart.addSeries("Linear search(random data)", doubleX, yAxis[0]);
            chart.addSeries("Linear search(sorted data)", doubleX, yAxis[1]);
            chart.addSeries("Binary search(sorted data)", doubleX, yAxis[2]);
        }

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
}
