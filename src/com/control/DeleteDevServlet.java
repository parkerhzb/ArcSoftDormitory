package com.control;

import com.dao.DeviceDao;

import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;

import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
删除设备
 */
@WebServlet("/DeleteDev.do")
public class DeleteDevServlet extends HttpServlet{
    public DeleteDevServlet(){
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
        DeviceDao deviceDao=new DeviceDao();
        if(deviceDao.DelDev(Id)){
            request.getSession().setAttribute("msg","删除成功！");//删除成功返回1
        }
        else{
            String mess="该设备信息不存在！";
            request.getSession().setAttribute("msg",mess);
        }
        response.setCharacterEncoding("utf-8");
        response.sendRedirect("pages/forms/device_delete.jsp");

    }
}
