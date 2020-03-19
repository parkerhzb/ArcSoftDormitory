package com.control;

import com.dao.DeviceDao;
import com.model.Device;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import java.text.SimpleDateFormat;
//import java.util.Date;

/*
报修设备，改变设备状态
获取参数：设备Id，状态status
 */
@WebServlet("/RepairDevice.do")
public class RepairDeviceServlet extends HttpServlet {
    public RepairDeviceServlet(){
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
        String Identity=(String)request.getSession().getAttribute("Identity");
        DeviceDao deviceDao=new DeviceDao();
        Device device=deviceDao.SelDevice(Id);
        if(device.getStatus().equals("1"))
            device.setStatus("2");
        else
            device.setStatus("1");
        deviceDao.Repair(Id,device.getStatus());
        response.setCharacterEncoding("utf-8");
       // if(Identity.equals("1"))
         //   response.sendRedirect("pages/forms2/checkDevice.jsp");
        //else
            response.sendRedirect("AllDev.do");
    }

}
