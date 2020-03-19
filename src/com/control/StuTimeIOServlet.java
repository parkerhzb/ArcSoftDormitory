package com.control;

import com.dao.StudentDao;
import com.model.Student;

import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
查询某学生某一时间段的出入记录
输入参数：学生Id,楼号Building，时间段Data1-Data2
 */
@WebServlet("/StuTimeIO.do")
public class StuTimeIOServlet extends HttpServlet{
    public StuTimeIOServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        String Id=request.getParameter("Id");
        String Building=request.getParameter("Building");
        //转换Building to Building_Daily
        BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
        String tab_name=buildingToBuilding_daily.transform(Building);

        String Data1=request.getParameter("Data1");//起始时间段
        String Data2=request.getParameter("Data2");//结束时间段
        StudentDao studentDao=new StudentDao();
        ArrayList<String>StuTimeIOList=new ArrayList<String>();

        StuTimeIOList=studentDao.GetStuTimeIO(Id,tab_name,Data1,Data2);
        request.getSession().setAttribute("StuTimeIOLIst",StuTimeIOList);
    }
}
