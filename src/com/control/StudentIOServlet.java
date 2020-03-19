package com.control;

import com.dao.StudentDao;

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
查询某学生所有出入记录
输入参数：学生Id,楼号Building
 */
@WebServlet("/StudentIO.do")
public class StudentIOServlet extends HttpServlet{
    public  StudentIOServlet(){
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

        StudentDao studentDao=new StudentDao();
        ArrayList<String>StudentIOList=new ArrayList<String>();

        StudentIOList=studentDao.GetStudnetIO(Id,tab_name);
        request.getSession().setAttribute("StudentIOLIst",StudentIOList);
    }
}
