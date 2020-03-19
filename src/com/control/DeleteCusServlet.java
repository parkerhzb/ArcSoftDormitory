package com.control;

import com.dao.AdminDao;
import com.dao.CustodianDao;

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
删除宿管信息
 */
@WebServlet("/DeleteCus.do")
public class DeleteCusServlet extends HttpServlet{
    public DeleteCusServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        AdminDao adminDao = new AdminDao();
        String Id = request.getParameter("Id");
        if (adminDao.DelCus(Id)) {
            request.getSession().setAttribute("msg", "该宿管相关信息已删除");//删除成功返回1
        } else {
            String mess = "该宿管信息不存在";
            request.getSession().setAttribute("msg", mess);
        }
        response.setCharacterEncoding("utf-8");
        response.sendRedirect("pages/forms/custodian_delete.jsp");
    }
}
