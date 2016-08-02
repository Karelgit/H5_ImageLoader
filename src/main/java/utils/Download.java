package utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * <类详细说明>
 *
 * @Author： Huanghai
 * @Version: 2016-07-31
 **/
public class Download {

    public void download(String sUrl, String directory, String fileName) {
        // 下载网络文件
        try {
            int bytesum = 0;
            int byteread = 0;
            URL url = new URL(sUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            File fileD =new File(directory);
            //如果文件夹不存在则创建
            if  (!fileD .exists()  && !fileD .isDirectory()) {
                System.out.println("正在新建目录");
                fileD .mkdirs();
            } else {
                System.out.println("目录存在");
            }

            FileOutputStream fs = new FileOutputStream(directory+fileName);
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println("downloaded ok");
    }

}
