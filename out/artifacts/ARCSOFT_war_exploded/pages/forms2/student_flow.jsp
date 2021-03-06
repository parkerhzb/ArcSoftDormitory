<%@ page import="com.dao.CustodianDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dao.StudentDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>查看所有学生出入寝室记录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="../../bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../../dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<%
    String building=(String)session.getAttribute("Building");
%>
<div class="wrapper">

    <header class="main-header">

        <!-- Logo -->
        <a href="../../main2.jspw" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>后台</b>管理</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>智能寝室门禁</b>后台系统</span>
        </a>

        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <li class="dropdown messages-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-envelope-o"></i>
                            <span class="label label-success">4</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">你有4条未读信息</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <li><!-- start message -->
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="../../dist/img/user2-160x160.jpg" class="img-circle"
                                                     alt="User Image">
                                            </div>
                                            <h4>
                                                西十三宿管
                                                <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                            </h4>
                                            <p></p>
                                        </a>
                                    </li>
                                    <!-- end message -->
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="../../dist/img/user3-128x128.jpg" class="img-circle"
                                                     alt="User Image">
                                            </div>
                                            <h4>
                                                西十宿管
                                                <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                            </h4>
                                            <p></p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="../../dist/img/user4-128x128.jpg" class="img-circle"
                                                     alt="User Image">
                                            </div>
                                            <h4>
                                                总管理员
                                                <small><i class="fa fa-clock-o"></i> Today</small>
                                            </h4>
                                            <p></p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="../../dist/img/user3-128x128.jpg" class="img-circle"
                                                     alt="User Image">
                                            </div>
                                            <h4>
                                                西十宿管
                                                <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                            </h4>
                                            <p></p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="../../dist/img/user4-128x128.jpg" class="img-circle"
                                                     alt="User Image">
                                            </div>
                                            <h4>
                                                总管理员
                                                <small><i class="fa fa-clock-o"></i> 2 days</small>
                                            </h4>
                                            <p></p>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="footer"><a href="#">查看所有信息</a></li>
                        </ul>
                    </li>
                    <!-- Notifications: style can be found in dropdown.less -->
                    <li class="dropdown notifications-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-bell-o"></i>
                            <span class="label label-warning">10</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">你有10条未读消息</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-users text-aqua"></i> 有五位学生新加入寝室
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-warning text-yellow"></i> 设备问题反馈
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-users text-red"></i> 一位宿管加入
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-shopping-cart text-green"></i> 25 条寝室出入记录
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-user text-red"></i> 你刚刚修改了你的用户名
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="footer"><a href="#">查看全部信息</a></li>
                        </ul>
                    </li>

                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="../../dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs"><%=building%>宿管</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                <p>
                                    <%=building%>宿管 - 007
                                    <small>注册日期 Nov. 2019</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="row">
                                    <div class="col-xs-4 text-center">
                                        <a href="#">个人中心</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">我的好友</a>
                                    </div>
                                </div>
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-right">
                                    <a href="../../" class="btn btn-default btn-flat">退出登录</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>

        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p><%=building%>宿管</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                </div>
            </div>
            <!-- search form -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="查找学生信息...">
                    <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                  <i class="fa fa-search"></i>
                </button>
              </span>
                </div>
            </form>
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">主菜单</li>


                <li>
                    <a href="../charts/flot2.jsp">
                        <i class="fa fa-bar-chart"></i> <span>图表统计</span>
                        <span class="pull-right-container">
              <!--<small class="label pull-right bg-green">学生进出寝室记录</small>-->
            </span>
                    </a>
                </li>

                <li>
                    <a href="../../TodayIO.do">
                        <i class="fa fa-table"></i> <span>学生进出寝室记录</span>
                        <span class="pull-right-container">
              <!--<small class="label pull-right bg-green">学生进出寝室记录</small>-->
            </span>
                    </a>
                </li>

                <li>
                    <a href="visitor_add.jsp">
                        <i class="fa fa-edit"></i> <span>访客登记</span>
                        <span class="pull-right-container">
            </span>
                    </a>
                </li>

                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>管理学生信息</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="../../SelStudent.do"><i class="fa fa-circle-o"></i> 查看所有学生信息</a></li>
                        <li><a href="student_add.jsp"><i class="fa fa-circle-o"></i> 增加学生信息</a></li>
                        <li><a href="student_update.jsp"><i class="fa fa-circle-o"></i> 修改学生信息</a></li>
                        <li><a href="student_delete.jsp"><i class="fa fa-circle-o"></i> 删除学生信息</a></li>
                    </ul>
                </li>

                <li class="header">代办</li>
                <li><a href="../../AllDev.do"><i class="fa fa-circle-o text-red"></i> <span>设备维护</span></a></li>
                <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> 其他人员出入记录<span>

                </span></a></li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                查看学生出入寝室信息
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li><a href="#">管理学生出入寝室信息</a></li>
                <li class="active">查看学生出入寝室信息</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">查看学生出入寝室信息</h3>


                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                                <div id="toolbar">
                                    <div>
                                    <%--<select class="form-control" id="selector2" style="float: left;width: auto; margin-bottom: 20px;margin-bottom: 5px;">--%>
                                        <%--<%--%>
                                            <%--String sel2=(String) session.getAttribute("path");--%>
                                            <%--if(sel2.equals("weekIO.do")){--%>
                                        <%--%>--%>
                                        <%--<option value="0" >当天</option>--%>
                                        <%--<option value="1" selected="selected">近七天</option>--%>
                                        <%--<option value="2" >全部</option>--%>
                                        <%--<%--%>
                                        <%--}--%>
                                        <%--else if(sel2.equals("allIO.do")){--%>
                                        <%--%>--%>
                                        <%--<option value="0" >当天</option>--%>
                                        <%--<option value="1" >近七天</option>--%>
                                        <%--<option value="2" selected="selected">全部</option>--%>
                                        <%--<%--%>
                                        <%--}--%>
                                        <%--else{--%>
                                        <%--%>--%>
                                        <%--<option value="0" >当天</option>--%>
                                        <%--<option value="1" >近七天</option>--%>
                                        <%--<option value="2">全部</option>--%>
                                        <%--<%}%>--%>
                                    <%--</select>--%>
                                    </div>
                                    <div>
                                    <select class="form-control" id="selector" style="width: auto; margin-bottom: 20px;">
                                        <%
                                            String sel=(String) session.getAttribute("sel");
                                            if(sel.equals("1")){
                                        %>
                                        <option value="0" >全部进出记录</option>
                                        <option value="1" selected="selected">仅晚归记录</option>
                                        <%
                                            }
                                            else{
                                                %>
                                        <option value="0" >全部进出记录</option>
                                        <option value="1" >仅晚归记录</option>
                                        <%}%>
                                    </select>
                                    </div>
                                </div>

                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>时间</th>
                                    <th>进/出</th>
                                    <th>联系电话</th>
                                    <th>寝室号</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    ArrayList<StudentDao.DailyStudent> dailyStudents=(ArrayList<StudentDao.DailyStudent>)session.getAttribute("List");
                                    for(StudentDao.DailyStudent dailyStudent:dailyStudents){
                                %>
                                <tr>
                                    <td><%=dailyStudent.getId()%></td>
                                    <td><%=dailyStudent.getName()%></td>
                                    <td><%=dailyStudent.getTime()%></td>
                                    <td><%if(dailyStudent.getFlag().equals("0")){%>进<%}else{%>出<%}%></td>
                                    <td><%=dailyStudent.getTel()%></td>
                                    <td><%=dailyStudent.getRoom()%></td>
                                </tr>
                                <%}%>
                                </tbody>
                                <!--<tfoot>
                                <tr>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>联系电话</th>
                                    <th>寝室号</th>
                                </tr>
                                </tfoot>-->
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->


    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.4.0
        </div>
        <strong>Copyright &copy; 2019-2021 <a href="https://adminlte.io">ZJUT</a>.</strong> All rights
        reserved.
    </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="../../bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../../bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../dist/js/demo.js"></script>

<script>
    $(function () {
        $('#example1').DataTable()
        $('#example2').DataTable({
            'paging'      : true,
            'lengthChange': false,
            'searching'   : true,
            'ordering'    : true,
            'info'        : true,
            'autoWidth'   : false
        })
    })
</script>

<script>

    <%--$("#selector2").on("change",function(){--%>
        <%--if ($("option:selected",this).val() == '0') {--%>
            <%--window.location.href="../../TodayIO.do?sel="<%=(String)session.getAttribute("sel")%>;--%>
        <%--}--%>
        <%--else if ($("option:selected",this).val() == '1') {--%>
            <%--window.location.href="../../weekIO.do?sel="<%=(String)session.getAttribute("sel")%>;--%>
        <%--}--%>
        <%--else if ($("option:selected",this).val() == '2') {--%>
            <%--window.location.href="../../allIO.do?sel="<%=(String)session.getAttribute("sel")%>;--%>
        <%--}--%>
    <%--});--%>


    $("#selector").on("change",function(){
        if ($("option:selected",this).val() == '0') {
            window.location.href="../../<%=(String)session.getAttribute("path")%>?sel=0";
        }
        else if ($("option:selected",this).val() == '1') {
            window.location.href="../../<%=(String)session.getAttribute("path")%>?sel=1";
        }
    });

</script>
</body>
</html>
