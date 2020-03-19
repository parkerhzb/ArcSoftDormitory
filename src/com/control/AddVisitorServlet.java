package com.control;

import com.dao.CustodianDao;
import com.dao.StudentDao;
import com.model.Custodian;
import com.model.QRCodeUtils;
import com.model.Student;

import java.io.*;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddVisitor.do")
public class AddVisitorServlet extends HttpServlet{
    public AddVisitorServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Id=request.getParameter("Id");
        String Name=request.getParameter("Name");
        String Building=request.getParameter("Building");
        String Intention=request.getParameter("Intention");
        String Main_id=request.getParameter("Main_id");//邀请者的学号或宿管号
        //权限判断。。。。
        String Identity=(String)request.getSession().getAttribute("Identity");
        String power = (String) request.getSession().getAttribute("Building");
        if(Identity.equals("1")) {
            System.out.println(Identity + "  " + power);
            if (!Building.equals(power)) {
                request.getSession().setAttribute("msg", "权限不足，只能添加本寝室楼访客！");
                response.sendRedirect("pages/forms2/visitor_add.jsp");
                return;
            }
        }
        if(Main_id.length()==12){
            //学生邀请
            StudentDao studentDao=new StudentDao();
            Student student=studentDao.SelectStu(Main_id);
            String pp=student.getRoom();
            if(pp.equals(null)){
                request.getSession().setAttribute("msg", "邀请者学号错误！");
                response.sendRedirect("pages/forms2/visitor_add.jsp");
                return;
            }
            if (!pp.substring(0,pp.indexOf("#")).equals(power)) {
                request.getSession().setAttribute("msg", "只能由该寝室楼学生或宿管邀请本寝室楼访客！");
                response.sendRedirect("pages/forms2/visitor_add.jsp");
                return;
            }
        }
        else {
            //宿管邀请
            CustodianDao custodianDao=new CustodianDao();
            Custodian custodian=custodianDao.SelectCus(Main_id);
            String pp=custodian.getBuilding();
            if(pp.equals(null)){
                request.getSession().setAttribute("msg", "邀请者工号错误！");
                response.sendRedirect("pages/forms2/visitor_add.jsp");
                return;
            }
            if (!pp.equals(power)) {
                request.getSession().setAttribute("msg", "只能由该寝室楼学生或宿管邀请本寝室楼访客！");
                response.sendRedirect("pages/forms2/visitor_add.jsp");
                return;
            }
        }

        CustodianDao custodianDao=new CustodianDao();
        ////转换building 西13--》w13
        if(Building.substring(0,1).equals("西"))
            Building="W"+Building.substring(1);
        else
            Building="E"+Building.substring(1);
        if(custodianDao.AddVisitor1(Id,Name,Building,Intention,Main_id)){
            //添加成功，返回访客二维码
            //QRCodeUtils qrCodeUtils=new QRCodeUtils();
            String encoder=Id+"#"+Building;
//            File file=new File("D:\\visitor_QR.png");
            try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
                /* 写入Txt文件 */
                File writename = new File("D:\\file.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
                writename.createNewFile(); // 创建新文件
                BufferedWriter out = new BufferedWriter(new FileWriter(writename));
                out.write(encoder); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
                out.close(); // 最后记得关闭文件

                Thread.sleep(1000);
                request.getSession().setAttribute("path",Id+"QR.png");
                request.getSession().setAttribute("msg","访客二维码生成成功！");

            } catch (Exception e) {
                e.printStackTrace();
            }


                //QRCodeUtils.qrCodeEncode(encoder,file);
                //QRCodeUtils.qrCodeEncode("201706062323#西13",new File("D:\\vis_RQ.png"));

//        try{
//            String Host = "10.80.160.163";
//            int Port = 8003;//检测是否有二维码
//            Socket socket = new Socket(Host, Port);
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            String data = encoder;
//            data = URLEncoder.encode(data, "GBK");
//
//            try {
//                out.write(data);
//                out.flush();
//                String inMsg = in.readLine();//得到服务器返回的数据
//                if (inMsg.equals("success")) {
//
//                }
//                //close connection
//                socket.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//        catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        }
        else
            request.getSession().setAttribute("msg","访客二维码生成失败！");

        response.sendRedirect("pages/forms2/visitor_add.jsp");

    }
}
