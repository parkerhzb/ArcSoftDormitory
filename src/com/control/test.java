package com.control;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageInfo;
import com.model.QRCodeUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;
import static com.model.Receivr.re;

public class test {
    public static void main(String[] args) throws IOException, InterruptedException {

        String appId = "C5NVXpX7DuGy7AY85kEyzwafTR679m8g4tLeuCUDBZuG";
        String sdkKey = "F3Dx79kGs9cTkMgpKGj4dAepqF64PtKFrFdgE2ukMXYM";

        FaceEngine faceEngine = new FaceEngine("D:\\work\\extra\\ArcSoft\\arcsoft_rar\\libs\\WIN64");
        //激活引擎
        int activeCode = faceEngine.activeOnline(appId, sdkKey);

        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);

        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        int initCode = faceEngine.init(engineConfiguration);

        if (initCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }

        ImageInfo imageInfo = getRGBData(new File("C:\\Users\\Parker\\Desktop\\gjy.jpg"));
        //ImageInfo imageInfo = getRGBData(new File("D:\\西13photo_save\\100002.png"));

        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        int detectCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        if(detectCode==0) {
            //System.out.println(faceInfoList);

            //特征提取
            FaceFeature faceFeature = new FaceFeature();
            byte[] Face = new byte[1032];

            //返回是否检测成功0
            int extractCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);
            //System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

            Face = faceFeature.getFeatureData();


            if (extractCode == 0) {
                BASE64Encoder encoder=new BASE64Encoder();
                String str=encoder.encode(Face);
                System.out.println(str);
            }
            else {
                System.out.println("检测shibai");
            }
        }
        else{
           System.out.println("wulain");
        }


////////        QRCodeUtils.qrCodeEncode("201706062323#西13",new File("D:\\vis_RQ.png"));
/*

        re[0]="a";

        System.out.println(re[0]);*/


//String s="西13";
//if(s.substring(0,1).equals("西"))
//s="W"+s.substring(1);
//System.out.println(s);
    }
}
