package com.control;

import com.dao.CustodianDao;
import com.sun.org.apache.regexp.internal.RE;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.model.Receivr.re;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
           /* ArrayList<byte[]> list=new ArrayList<>();
            CustodianDao custodianDao=new CustodianDao();
            byte b[]=new byte[1032];
            String str="EpF1LTo09H70fLWb6DDxfVLaLQJT37OO68KZq2LZrpGz0tLRs9EjS8LFB/CT09DJI9ML8BDq6rI718PAI8Bby8DZYiOz3SBQQ+ACH2LwV1LLzCGho+O6EZDjCZGz2pEf28FVRoDMIdF72zIZQ9GKqDL4MfKr2HAZe9BVqwDRQ3JLzYB8G8PPG/LPUOBz0oLh69EfufLRhfBzzKN489NQSpDePZED1KBfc9C2aBLS5FMr2SNFw9GsPwDEfLK73tIHA9Ag+0LfElIbvxAVG9CNw3DGC/Ab1nCcI9C17uDIVQDj2mITM9OGG8LRCaL7xjCZO9J/b5L4gwCb2WHQu+PiK/LRHUPr3VMjE9IXSoDUQ7KD0+Oaa8JEaKDWQQF72QK5w9GZ+RLRtGLbzaAV+8C0UwLAj5BT4NH2y9CVCnDFQPJ73AB989My68LVPHFT34J/E9GyBbDe8DMTxxFDE9PaqKDfLjHrzfEs68ObQzDe2KNb3eMFW9DXS6LcNjKb2dLAG+KyEsDdKzEz2YDyO9H5bjD361Bj1MLQe7M9yUDeDtNL0FIGi9EogaDY+gPDwHJgO8KwQkLbwxAT10HZ67JCHLDAJAMTzpI3y9AhnMLo1EIL3VFWo8LNwQDfJIObz0MhE7BRTJDEsNKj09LFM9F3eoDb4QHL1vHzu8";

            BASE64Decoder decoder=new BASE64Decoder();
            b= decoder.decodeBuffer(str);
            custodianDao.AddStu("2","1","1",b,"17326031722","西13#219");*/

/******************
        FileImageInputStream input = new FileImageInputStream(new File("C:\\Users\\Parker\\Desktop\\1.png"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        byte[] restxt;
        int numBytesRead = 0;
        while ((numBytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, numBytesRead);
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String tmp;
        restxt = output.toByteArray();
        output.close();
        input.close();

        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        String s = encoder.encode(restxt);
        restxt = decoder.decodeBuffer(s);
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File("D:\\221.png"));
        imageOutput.write(restxt, 0, restxt.length);
        imageOutput.close();
        System.out.println(1);******************/


  //      test test=new test();
  //      test.ma();
        String Room="西13#239";
        String power="西1";
        if(Room.length()==7){
            if(!Room.substring(0,3).equals(power)){
                System.out.println("权限不足，只能添加本寝室楼学生信息！");
                return;
            }
            else
                System.out.println("ok");
        }
        else{
            if(!Room.substring(0,2).equals(power)){
                System.out.println("权限不足，只能添加本寝室楼学生信息！");
                return;
            }
            else
            System.out.println("ok");
        }

    }
}