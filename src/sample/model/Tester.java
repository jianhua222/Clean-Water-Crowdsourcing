package sample.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Allen on 11/8/2016.
 */
public class Tester {
    public static void main(String[] args){
        WaterSourceReportList temp = null;
        try {
            FileInputStream fileIn = new FileInputStream("ReportBase.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (WaterSourceReportList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();

            //System.out.printf("Serialized data is saved in /tmp/employee.ser");


        } catch (ClassNotFoundException c) {
            System.out.println();
            c.printStackTrace();
            //return;
        }
        System.out.print(temp.getBackingArray().size());
        System.out.print(temp);
        User tem = null;
        try {
            FileInputStream fileIn = new FileInputStream("a.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tem = ( User) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            //return;
        }catch(ClassNotFoundException c) {
            System.out.println();
            c.printStackTrace();
            //return;
        }
        System.out.print(tem);
    }
}
