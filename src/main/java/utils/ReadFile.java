package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Huanghai on 2016/7/29.
 */
public class ReadFile {
    private static String PATTERN = "<";

    public static boolean readByLine(String filePath)    {
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
//                if()
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
return false;
    }

    public static void main(String[] args) {
        String html_path = System.getProperty("user.dir")+"\\data\\1.html";
        readByLine(html_path);
        /*String str = "<img src=\"http://img1.maka.im/user/2267368/images/85121897083ba8f6b7e4d4e85bc20106.png@0-0-1200-151a_90Q.src\"";
        boolean flag = str.matches("^(<img)(\\s+)src=\"http://\\S*(\\.src\")");
        System.out.println(flag);*/
    }
}
