package sample.model;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by Allen on 11/7/2016.
 */
public class WaterSourceReportList implements Serializable {
    private static ArrayList<WaterSourceReport> backingArray = new ArrayList<>();
    public WaterSourceReportList(){
        backingArray = new ArrayList<>();
    }
    public static void addReport(WaterSourceReport input){
        if(input == null){
            System.out.print("input null");
        }
        backingArray.add(input);
    }
    public static ArrayList<WaterSourceReport> getBackingArray(){
        return backingArray;
    }
    public static WaterSourceReport getReport(int reportNumer){
        return backingArray.get(reportNumer);
    }
    public String toString(){
        String str = "";
        for(WaterSourceReport x : backingArray){
            str = str+ "/n" + x.toString();
        }
        return str;
    }
    public void save() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("ReportBase.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            //out.writeObject(b);
            out.close();
            fileOut.close();
            //System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }

    }

}
