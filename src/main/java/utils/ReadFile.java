package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Huanghai on 2016/7/29.
 */
public class ReadFile {
    public static String readByLine(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        String lineString = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.trim() != null) {
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

/*    public static void main(String[] args) throws IOException {

        String pro_path = System.getProperty("user.dir");
        String html_path = pro_path + "\\data\\1.html";
        String download_path = pro_path + "\\data\\download\\";
        List<String> imgList = new ArrayList<String>();
        String src = readByLine(html_path);
        Pattern p = Pattern.compile("(http://)[^(http)]*?(png|gif|src|jpg|jpeg)");
        Matcher m = p.matcher(src);
        while (m.find()) {
            imgList.add(m.group());
        }
        for (String s : imgList) {
            String final_fileName = s.replace("/", "_").replace(":","_");
            new Download().download(s, download_path, final_fileName);
        }
    }*/

    public int downloading(String path, String iframeName) throws IOException {

        try {
            String download_path = path + "download_" + iframeName + "/";
            List<String> imgList = new ArrayList<>();
            String src = readByLine(path + iframeName);
            //Pattern p = Pattern.compile("(http://)[^(http)]*?(png|gif|src|jpg|jpeg)");
            Pattern p = Pattern.compile("(http://)\\S+?(png|gif|jpg|jpeg|src)");
            Matcher m = p.matcher(src);
            while (m.find()) {
                imgList.add(m.group());
            }
            for (String s : imgList) {
                String final_fileName = s.replace("/", "_").replace(":", "_");
                new Download().download(s, download_path, final_fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

}
