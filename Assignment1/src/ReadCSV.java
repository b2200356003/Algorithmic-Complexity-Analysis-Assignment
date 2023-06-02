import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {
    public static int[] Read(String filename){
        BufferedReader reader = null;
        String line = "";
        int row = 0;
        ArrayList<Integer> FlowDuration = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                if (row!=0) {
                    String[] lineList = line.split(",");
                    FlowDuration.add(Integer.parseInt(lineList[6]));
                }
                row++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int[] flowDuration = new int[FlowDuration.size()];
        for (int i = 0; i < FlowDuration.size(); i++) {
            flowDuration[i]=FlowDuration.get(i);
        }
        return flowDuration;
    }
}
