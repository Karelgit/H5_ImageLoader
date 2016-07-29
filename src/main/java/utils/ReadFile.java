package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Huanghai on 2016/7/29.
 */
public class ReadFile {
    private static String PATTERN = "^(<img)(\\s+)src=\"http://\\S*(\\.src\")$";

    public static List<String> readByLine(String filePath)    {
        List<String> image_src_list = new ArrayList<String>();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            Pattern p = Pattern.compile(PATTERN);

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if(tempString.trim() != null) {
                    Matcher m = p.matcher(tempString);
                    System.out.println(tempString);
                    while (m.find())    {
                        image_src_list.add(m.group());
                    }
                }
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
        return image_src_list;
    }

    public static void main(String[] args) {

        /*String str = "<img src=\"http://img1.maka.im/user/2267368/images/85121897083ba8f6b7e4d4e85bc20106.png@0-0-1200-151a_90Q.src\"";
        boolean flag = str.matches("^(<img)(\\s+)src=\"http://\\S*(\\.src\")$");
        System.out.println(flag);*/

        String html_path = System.getProperty("user.dir")+"\\data\\1.html";
        List<String> image_src_list =  readByLine(html_path);
        for (String src : image_src_list) {
            System.out.println(src);
        }
    }
}
