package sample.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Allen on 11/7/2016.
 * stores reports
 */
public class WaterSourceReportList implements Serializable {
    private ArrayList<WaterSourceReport> backingArray = new ArrayList<>();

    /**
     * /**
     * constructor
     */
    public WaterSourceReportList() {
        backingArray = new ArrayList<>();
    }
    /**
     * add report
     * @param input input report
     */
    public void addReport(WaterSourceReport input) {
        if (input == null) {
            throw new IllegalArgumentException("input is null");
        }
        else{
        backingArray.add(input);}
    }
    /**
     * return backingArray
     * @return backingArray
     */
    public ArrayList<WaterSourceReport> getBackingArray() {
        return backingArray;
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        String str = "";
        for (WaterSourceReport x : backingArray) {
            str = str + "\n" + x.toString();
        }
        return str;
    }
    /**
     * serialize object
     */
    public void save() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("ReportBase.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            //out.writeObject(b);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

}
