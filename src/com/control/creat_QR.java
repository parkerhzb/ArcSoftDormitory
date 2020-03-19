package com.control;

import com.model.QRCodeUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class creat_QR {
    public static void main(String[] args) {
    String ss="";
        while(true){
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            /* 读入TXT文件 */
            String pathname = "D:\\file.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            String l = br.readLine();
            do {
                line+=l;
                l = br.readLine(); // 一次读入一行数据
            }while (l != null);
            if(!line.trim().equals(ss)) {
                ss=line.trim();
                if(!ss.equals("")){
                    /*生成二维码*/
                    File QR = new File("D:\\work\\extra\\ArcSoft\\ARCSOFT\\web\\QR\\visitor_QR.png");
                    QRCodeUtils.qrCodeEncode(line.trim(),QR);

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
          }
//        File QR = new File("D:\\visitor_QR.png");
//        QRCodeUtils.qrCodeDecode(QR);
    }
    }
}
