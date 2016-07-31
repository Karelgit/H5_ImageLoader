package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Huanghai on 2016/7/29.
 */
public class ReadFile {
    public static String readByLine(String filePath)    {
        List<String> image_src_list = new ArrayList<String>();
        File file = new File(filePath);
        BufferedReader reader = null;
        String lineString = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if(tempString.trim() != null) {
                    lineString = lineString + tempString;
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
        return lineString;
    }

    /*public static void outputFile(List<String> list) {
        try {
            File file = new File(System.getProperty("user.dir")+"\\data\\imgSrc.txt");
            FileWriter fileWriter = new FileWriter(file);
            for (String s : list) {
                fileWriter.write(s);
                fileWriter.write("\n");
            }
            fileWriter.close(); // 关闭数据流
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args) throws IOException {
        String pro_path = System.getProperty("user.dir");
        String html_path = pro_path + "/data/1.html";
        String download_path = pro_path + "/data/";
        List<String> imgList = new ArrayList<String>();
        String src =  readByLine(html_path);
        Pattern p = Pattern.compile("(http://)[^(http)]*?(png|gif|src)");
        Matcher m = p.matcher(src);
        while (m.find())    {
            imgList.add(m.group());
        }
        for (String s : imgList) {
            String final_fileName = s.replace("/","*");
            new  Download().download(s,download_path,final_fileName);
        }
    }
}
