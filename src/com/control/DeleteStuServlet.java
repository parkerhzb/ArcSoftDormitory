package com.control;

import com.dao.CustodianDao;
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
删除学生信息
 */
@WebServlet("/DeleteStu.do")
public class DeleteStuServlet extends HttpServlet{
    public DeleteStuServlet(){
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

        response.setCharacterEncoding("utf-8");

        //////判断权限
        StudentDao studentDao=new StudentDao();
        Student student=studentDao.SelectStu(Id);
        if(student==null){
            request.getSession().setAttribute("msg","该学生不存在本寝室楼！");
            response.sendRedirect("pages/forms2/student_delete.jsp");
            return;
        }
        String Room=student.getRoom();
        String Identity=(String)request.getSession().getAttribute("Identity");
        if(Identity.equals("1")){
            String power=(String)request.getSession().getAttribute("Building");
            if(Room.length()==7){
                if(!Room.substring(0,3).equals(power)){
                    request.getSession().setAttribute("msg","权限不足，只能删除本寝室楼学生信息！");
                    response.sendRedirect("pages/forms2/student_delete.jsp");
                    return;
                }
            }
            else{
                if(!Room.substring(0,2).equals(power)){
                    request.getSession().setAttribute("msg","权限不足，只能删除本寝室楼学生信息！");
                    response.sendRedirect("pages/forms2/student_delete.jsp");
                    return;
                }
            }
        }

        CustodianDao custodianDao=new CustodianDao();
        if(custodianDao.DelStu(Id)){
            request.getSession().setAttribute("msg","删除成功！");

        }
        else{
            String mess="该学生信息不存在！";
            request.getSession().setAttribute("msg",mess);
        }
        if(Identity.equals("1"))
            response.sendRedirect("pages/forms2/student_delete.jsp");
        else
            response.sendRedirect("pages/forms/student_delete.jsp");
    }
}
