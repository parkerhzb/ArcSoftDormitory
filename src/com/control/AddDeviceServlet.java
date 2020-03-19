package com.control;

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

import com.model.Device;
import com.dao.DeviceDao;

/*
增加设备
参数：Id,Building,Status
 */
@WebServlet("/AddDeviceServlet.do")
public class AddDeviceServlet extends HttpServlet {

    public AddDeviceServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        DeviceDao deviceDao=new DeviceDao();
        String Id=request.getParameter("Id");
        String Building = request.getParameter("Building");
        String Flag=request.getParameter("Flag");
        String Status = "1";
        //调用AddDevice()添加设备
        if(deviceDao.AddDevice(Id, Building,Flag, Status)){
            request.getSession().setAttribute("msg", "新设备添加成功！");//1表示添加成功
        }
        else {
            String mess = "设备Id已存在！";
            request.getSession().setAttribute("msg",mess);//返回失败信息
        }
        response.setCharacterEncoding("utf-8");
        response.sendRedirect("pages/forms/device_add.jsp");

    }

}
