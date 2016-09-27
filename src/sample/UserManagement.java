package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Allen on 9/27/2016.
 */
public class UserManagement {
    public static File userInfor = new File( "userInfor.txt");
    public static User currentUser;
    public static void register(String userName, String password , String type)throws IOException {

        FileWriter writer = new FileWriter(userInfor,true);
        String tem = userName+","+password+","+type+"\n";
        writer.write(tem);
        writer.close();
    }
    public static boolean verify(String userName, String password )throws IOException{
        Scanner scan = new Scanner(userInfor);
        String tem = null;
        String[] array;
        while (scan.hasNextLine()) {
            tem = scan.nextLine();

            array = tem.split(",");


            if (array[0].equals(userName) && array[1].equals(password)){


                return true;
            }

        }
        return false;
    }
}
