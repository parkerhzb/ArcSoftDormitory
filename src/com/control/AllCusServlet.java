package com.control;


import com.dao.CustodianDao;
import com.dao.StudentDao;
import com.model.Custodian;
import com.model.Student;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
返回所有学生信息
 */
@WebServlet("/AllCus.do")
public class AllCusServlet extends HttpServlet{
    public AllCusServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        CustodianDao custodianDao=new CustodianDao();
        ArrayList<Custodian> list=custodianDao.Sel_AllCus();
        request.getSession().setAttribute("custodians",list);
        response.setCharacterEncoding("utf-8");
        response.sendRedirect("pages/forms/custodian_select.jsp");
    }
}
