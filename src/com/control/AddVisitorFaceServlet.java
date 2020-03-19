package com.control;


import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageInfo;
import com.dao.ConnectionDao;
import com.dao.CustodianDao;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

@WebServlet("/AddVisitorFace.do")
public class AddVisitorFaceServlet extends HttpServlet{
    public AddVisitorFaceServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String QR_str=request.getParameter("QR_str");
        String Flag=request.getParameter("Flag");//摄像头标记
        String Building=request.getParameter("Building");//摄像头楼号
        String QR_id=QR_str.substring(0,12);
        String QR_building=QR_str.substring(QR_str.indexOf("#")+1);
        if(QR_building.substring(0,1).equals("W"))
            QR_building="西"+QR_building.substring(1);
        else
            QR_building="东"+QR_building.substring(1);
        if(!QR_building.equals(Building)){
            response.getWriter().write("您不属于该楼的访客");
            return;
        }
////////////】】】】】】】】】】】】】接收二维码【【【【【【【【【【【【【【/////////////
        String StrFace=request.getParameter("Face");////接收图片信息
        StrFace=StrFace.replace(' ','+');
        //StrFace= URLDecoder.decode(StrFace,"UTF-8");
        BASE64Decoder decoder=new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(StrFace);
        // Base64解码
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }
        // 生成jpeg图片
        String imgFilePath = "d://visitor.png";////////新生成的图片
        OutputStream out = new FileOutputStream(imgFilePath);
        out.write(b);
        out.flush();
        out.close();

        try {
///////【【【【【【【【【【【【【【识别脸】】】】】】】】】】/////////////////
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
                response.getWriter().write("false");
                return;
            }

            //读取图片，提取特征值
            //人脸检测
            File file=new File("d://visitor.png");
            ImageInfo imageInfo = getRGBData(file);
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
            int detectCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
            FaceFeature faceFeature = new FaceFeature();
            byte[]Face=new byte[1032];
            //进行检测
            int extractCode ;
            extractCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);
            if(extractCode!=0)
                return;
            Face=faceFeature.getFeatureData();//提取保存特征值
            CustodianDao custodianDao=new CustodianDao();
            if(custodianDao.AddVisitor2(QR_id,Face)) {
                //获取当时开门时间
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String time = dateTime.format(formatter);

                BuildingToBuilding_Daily buildingToBuilding_daily = new BuildingToBuilding_Daily();
                String Dailytab = buildingToBuilding_daily.transform(Building);
                response.getWriter().write("访客" + QR_id);//FeatureList2.get(i).getName()
                if (!custodianDao.CreateIO(Dailytab, QR_id, custodianDao.GetvisName(QR_id), time, Flag, "访客", "访客"))//添加人员出行记录
                {
                    ConnectionDao connectionDao = new ConnectionDao();
                    connectionDao.addIOTable(Dailytab);
                    if (custodianDao.CreateIO(Dailytab, QR_id, custodianDao.GetvisName(QR_id), time, Flag, "访客", "访客"))//添加人员出行记录
                    {//成功}
                    }
                }
            }
            else
                response.getWriter().write("false");

        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(1);
        }
    }
}
