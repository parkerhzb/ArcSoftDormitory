package com.control;

import com.dao.DeviceDao;
import com.model.Device;

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
查询某幢楼设备信息
in:building
 */
@WebServlet("/SelDevice.do")
public class SelDeviceServlet extends HttpServlet{
    public SelDeviceServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Building=request.getParameter("Building");
        DeviceDao deviceDao=new DeviceDao();
        ArrayList<Device>DeviceList=new ArrayList<Device>();
        DeviceList=deviceDao.GetBuildDev(Building);
        request.getSession().setAttribute("DeviceList",DeviceList);
        response.sendRedirect("pages/forms2/checkDevice.jsp");

    }

}
