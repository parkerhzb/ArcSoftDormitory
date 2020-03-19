/*package com.control;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageInfo;
import com.dao.CustodianDao;
import com.dao.StudentDao;
import com.model.Student;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;
import static com.model.Receivr.re;

@WebServlet("/SendStr.do")
public class sendServlet extends HttpServlet{
    sendServlet(){
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

        String skip=request.getParameter("Building");
        switch (skip){
            case "东1":response.getWriter().write(re[0]);break;
            case "东2":break;
            case "东3":break;
            case "东4":break;
            case "东5":break;
            case "东6":break;
            case "东7":break;
            case "东8":break;
            case "东9":break;
            case "东10":break;
            case "东11":break;
            case "东12":break;
            case "东13":response.getWriter().write(re[12]);break;
            case "东14":break;
            case "东15":break;
            case "西1":response.getWriter().write(re[15]);break;
            case "西2":break;
            case "西3":break;
            case "西4":break;
            case "西5":break;
            case "西6":break;
            case "西7":break;
            case "西8":break;
            case "西9":break;
            case "西10":break;
            case "西11":break;
            case "西12":break;
            case "西13":break;
            case "西14":break;
            case "西15":break;
            default:break;

        }
    }
}*/
package com.control;

        import com.arcsoft.face.*;

        import java.io.*;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;


        import static com.model.Receivr.re;

@WebServlet("/send.do")
public class sendServlet extends HttpServlet{

    public sendServlet(){
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

            String skip=request.getParameter("Building");
            switch (skip){
                case "东1":response.getWriter().write(re[0]);break;
                case "东2":response.getWriter().write(re[1]);break;
                case "东3":response.getWriter().write(re[2]);break;
                case "东4":response.getWriter().write(re[3]);break;
                case "东5":response.getWriter().write(re[4]);break;
                case "东6":response.getWriter().write(re[5]);break;
                case "东7":response.getWriter().write(re[6]);break;
                case "东8":response.getWriter().write(re[7]);break;
                case "东9":response.getWriter().write(re[8]);break;
                case "东10":response.getWriter().write(re[9]);break;
                case "东11":response.getWriter().write(re[10]);break;
                case "东12":response.getWriter().write(re[11]);break;
                case "东13":response.getWriter().write(re[12]);break;
                case "东14":response.getWriter().write(re[13]);break;
                case "东15":response.getWriter().write(re[14]);break;
                case "西1":response.getWriter().write(re[15]);break;
                case "西2":response.getWriter().write(re[16]);break;
                case "西3":response.getWriter().write(re[17]);break;
                case "西4":response.getWriter().write(re[18]);break;
                case "西5":response.getWriter().write(re[19]);break;
                case "西6":response.getWriter().write(re[20]);break;
                case "西7":response.getWriter().write(re[21]);break;
                case "西8":response.getWriter().write(re[22]);break;
                case "西9":response.getWriter().write(re[23]);break;
                case "西10":response.getWriter().write(re[24]);break;
                case "西11":response.getWriter().write(re[25]);break;
                case "西12":response.getWriter().write(re[26]);break;
                case "西13":response.getWriter().write(re[27]);
                break;
                case "西14":response.getWriter().write(re[28]);break;
                case "西15":response.getWriter().write(re[29]);break;
                default:break;

            }
        }

        catch(Exception e){
            e.printStackTrace();
            System.out.println(1);
        }

    }

}
