package com.control;

import com.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/weekIO.do")
public class weekIOServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Identity=(String)request.getSession().getAttribute("Identity");
        String Building=(String)request.getSession().getAttribute("Building");
        String sel=request.getParameter("sel");
        if(sel==null)
            sel="0";
        BuildingIO buildingIO=new BuildingIO();
        ArrayList<StudentDao.DailyStudent> List=new ArrayList<>();
        List = buildingIO.DailyBuilding(Building);
        if(sel.equals("1")){
            try {
                ArrayList<StudentDao.DailyStudent> dailyStudents = new ArrayList<StudentDao.DailyStudent>();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String today = df.format(new Date());
                today = today.substring(0, 11);
                today = today + "23:00:00";
                Date time = df.parse(today);
                for (StudentDao.DailyStudent dailyStudent : List) {
                    String IOtimeStr = dailyStudent.getTime();
                    Date IOtime = df.parse(IOtimeStr);
                    if (IOtime.after(time)) {
                        dailyStudents.add(dailyStudent);
                    }
                }
                List = dailyStudents;
            }catch (Exception e){e.printStackTrace();}
        }
        request.getSession().setAttribute("List",List);
        request.getSession().setAttribute("sel",sel);
        request.getSession().setAttribute("path","weekIO.do");
        response.setCharacterEncoding("utf-8");
        if(Identity.equals("1"))
            response.sendRedirect("pages/forms2/student_flow.jsp");
        else
            response.sendRedirect("pages/forms/student_flow.jsp");
    }
}
