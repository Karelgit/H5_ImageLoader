package gui;


import utils.ReadFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/1.
 */
public class H5downloadGUI extends JFrame {

    public static void main(String[] args) {
        new H5downloadGUI();
    }


    private ReadFile imgDownloader;

    private JButton doDownloadBtn;
    private JButton getPathBtn;
    private JTextField iFrameFilePath;
    private JLabel label01;


    public H5downloadGUI() {
        super("MAKA H5 Images Downloader");
        super.setSize(400, 300);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        centered(this);

        doDownloadBtn = new JButton("开 始 下 载");
        doDownloadBtn.setBounds(new Rectangle(100, 180, 180, 30));//参数分别是坐标x，y，宽，高
        doDownloadBtn.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                iFrameFilePath.setEnabled(false);
                doDownloadBtn.setEnabled(false);

                downloading(iFrameFilePath.getText());

                iFrameFilePath.setEnabled(true);
                doDownloadBtn.setEnabled(true);
            }
        });
        this.setLayout(null);//设置布局管理器为空
        this.add(doDownloadBtn);


        label01 = new JLabel("iFrame 源码目录:");
        label01.setBounds(new Rectangle(100, 40, 150, 30));
        label01.setBackground(Color.gray);
        this.add(label01);

        iFrameFilePath = new JTextField();
        iFrameFilePath.setBounds(new Rectangle(100, 80, 180, 30));
        iFrameFilePath.setBackground(Color.BLACK);
        iFrameFilePath.setForeground(Color.green);
        iFrameFilePath.setAutoscrolls(true);
        this.add(iFrameFilePath);

        getPathBtn = new JButton("选 择 目 录");
        getPathBtn.setBounds(new Rectangle(100, 130, 180, 30));
        getPathBtn.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                iFrameFilePath.setText(getPath());
            }
        });
        this.add(getPathBtn);

        this.setVisible(true);

        imgDownloader = new ReadFile();

        this.repaint();
    }

    //布局居中方法
    public void centered(Container container) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int w = container.getWidth();
        int h = container.getHeight();
        container.setBounds((screenSize.width - w) / 2,
                (screenSize.height - h) / 2, w, h);
    }

    private String getPath() {

        FileDialog dialog = new FileDialog(this);
        dialog.setVisible(true);
        return dialog.getDirectory();
    }


    private void downloading(String path) {

        File files = new File(path);
        File[] fileSet = files.listFiles();
        int ret = 0;

        for (File f : fileSet) {
            try {

                ret += this.imgDownloader.downloading(path, f.getName());

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        String msg = "下载完成! 请查看目录:" + path + " 返回码:" + ret;

        JDialog dialog =  new  JDialog(this);
        dialog.setSize(450,100);
        dialog.setResizable(false);
        dialog.setTitle("下载提示:");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.add(new JLabel(msg));
        centered(dialog);
        dialog.show();

    }

}
