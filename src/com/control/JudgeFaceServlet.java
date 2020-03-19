package com.control;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageInfo;
import com.dao.ConnectionDao;
import com.dao.CustodianDao;
import com.dao.CustodianDao.Stu;
import com.dao.StudentDao;
import com.model.Custodian;
//import net.sf.json.JSONObject;
import com.model.Student;
import com.model.Visitor;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.awt.image.BufferedImage;
import java.io.*;
//import java.text.SimpleDateFormat;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.model.Receivr.re;
import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

/*根据楼号Building与返回的特征值与数据库提取的特征值进行对比
同时生成每日人流表
输入参数：Building  FaceFeature
 */
@WebServlet("/JudgeFace.do")
public class JudgeFaceServlet extends HttpServlet{

    private FaceEngine faceEngine;//处理引擎

    public JudgeFaceServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=utf-8");
        try {

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
	System.out.println(initCode);
                System.out.println("初始化引擎失败");
                response.getWriter().write("false");
                return;
            }




            /////图片数据接收
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
                String imgFilePath = "d://judge.png";////////新生成的图片
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
/////】】】】】】】】】】】】】】】】】】】】】】】】】取图片  取特征值】】】】】】】】】】】】】】】】】】】】】】】】】】】】】////////////////////
            //楼号
            String Building=request.getParameter("Building");

            //转换Building to Building_Daily
            BuildingToBuilding_Daily buildingToBuilding_daily = new BuildingToBuilding_Daily();
            String tab_name = buildingToBuilding_daily.B_transform(Building);

            //Flag 进出标记
             String Flag=request.getParameter("Flag");
            faceEngine = new FaceEngine("D:\\work\\extra\\ArcSoft\\arcsoft_rar\\libs\\WIN64");
            //激活引擎
            activeCode = faceEngine.activeOnline(appId, sdkKey);
            if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
                System.out.println("引擎激活失败");
            }

            //引擎配置
            engineConfiguration = new EngineConfiguration();
            engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
            engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);

            //功能配置
            functionConfiguration = new FunctionConfiguration();
            functionConfiguration.setSupportAge(true);
            functionConfiguration.setSupportFace3dAngle(true);
            functionConfiguration.setSupportFaceDetect(true);
            functionConfiguration.setSupportFaceRecognition(true);
            functionConfiguration.setSupportGender(true);
            functionConfiguration.setSupportLiveness(true);
            functionConfiguration.setSupportIRLiveness(true);
            engineConfiguration.setFunctionConfiguration(functionConfiguration);


            //初始化引擎
            initCode = faceEngine.init(engineConfiguration);

            if (initCode != ErrorInfo.MOK.getValue()) {
	System.out.println(initCode);
                System.out.println("初始化引擎失败");
                response.getWriter().write("false");
                return;
            }

            //读取图片，提取特征值
            //人脸检测
            File file=new File("d://judge.png");
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

            FaceFeature OriginFeature = new FaceFeature(Face);
/////////////////】】】】】】】】】】】】】提取楼中所有id:face】】】】】】】】】】】】】】】】】//////////////////////////////////
            ArrayList<StudentDao.id_fa> FeatureList = new ArrayList<StudentDao.id_fa>();
            StudentDao studentDao = new StudentDao();
            CustodianDao custodianDao=new CustodianDao();
            FeatureList = studentDao.GetFeature(tab_name);//获取该楼的所有特征值 id:face
//////////////////】】】】】】】】】】】特征值对比】】】】】】】】】】】】】】】】】】/////////////////////////////
            //FeatureList与接收的特征值对比操作
            FaceSimilar faceSimilar = new FaceSimilar();//相似度

            CustodianDao.Stu stu = custodianDao.new Stu();
            Student student=new Student();

            boolean flag = false;
            for (int i = 0; i < FeatureList.size(); ++i) {
                FaceFeature feature = new FaceFeature(FeatureList.get(i).getFa());
                faceEngine.compareFaceFeature(feature, OriginFeature, faceSimilar);
                float Similar = faceSimilar.getScore();//获取相似度 界限--》0.8//
                if (Similar >= 0.7) {
                    flag=true;
                    //获取当时开门时间
                    LocalDateTime dateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String time = dateTime.format(formatter);

                    student=studentDao.SelectStu(FeatureList.get(i).getId());

                    String Dailytab=buildingToBuilding_daily.transform(Building);
                    response.getWriter().write(student.getName());//student.getName()
                    if(!custodianDao.CreateIO(Dailytab,student.getId(),student.getName(),time,Flag,student.getTel(),student.getRoom()))//添加人员出行记录
                    {
                        ConnectionDao connectionDao=new ConnectionDao();
                        connectionDao.addIOTable(Dailytab);
                        if(custodianDao.CreateIO(Dailytab,student.getId(),student.getName(),time,Flag,student.getTel(),student.getRoom()))//添加人员出行记录
                        {
                            //成功
                        }
                    }
                    break;
                }
            }
            if(flag==false){//查询访客人脸的匹配】】】】】】】】】】】】】】】】】】
                ArrayList<Visitor>FeatureList2=new ArrayList<>();
                FeatureList2=custodianDao.GetVisFace();//获取访客的信息 name:face
                for (int i = 0; i < FeatureList2.size(); ++i) {
                    String bu=FeatureList2.get(i).getBuilding();
                    if(bu.substring(0,1).equals("E"))
                        bu="东"+bu.substring(1);
                    else
                        bu="西"+bu.substring(1);
                    if(!bu.equals(Building)){
                        response.getWriter().write("您不属于该楼访客");
                        return;
                    }
                    FaceFeature feature = new FaceFeature(FeatureList2.get(i).getFace());
                    //faceSimilar=new FaceSimilar();
                    faceEngine.compareFaceFeature(feature, OriginFeature, faceSimilar);
                    float Similar = faceSimilar.getScore();//获取相似度 界限--》0.8//
                    if (Similar >= 0.7) {
                        flag=true;
                        //获取当时开门时间
                        LocalDateTime dateTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String time = dateTime.format(formatter);

                        String Dailytab=buildingToBuilding_daily.transform(Building);
                        response.getWriter().write(FeatureList2.get(i).getName());//FeatureList2.get(i).getName()
                        if(!custodianDao.CreateIO(Dailytab,FeatureList2.get(i).getId(),FeatureList2.get(i).getName(),time,Flag,"访客","访客"))//添加人员出行记录
                        {
                            ConnectionDao connectionDao=new ConnectionDao();
                            connectionDao.addIOTable(Dailytab);
                            if(custodianDao.CreateIO(Dailytab,FeatureList2.get(i).getId(),FeatureList2.get(i).getName(),time,Flag,"访客","访客"))//添加人员出行记录
                            {
                                //成功
                            }
                        }
                        break;
                    }
                }
            }
            if(flag==false)
                response.getWriter().write("您不属于该楼学生");
            int unInitCode = faceEngine.unInit();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(1);
        }

    }

}
