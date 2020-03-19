<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>智能寝室门禁系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">

<div class="login-box">
  <div class="login-logo">
    <a href="#"><b>智能寝室门禁</b>系统</a>
  </div>
  <script>
    layer.msg('123');
  </script>

    <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">登录</p>

    <form action="Login.do" method="post">
      <div class="form-group has-feedback">
        <input type="text" id="Id" name="Id" class="form-control" placeholder="账号">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" id="Password" name="Password" class="form-control" placeholder="密码">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <select id="Identity" name="Identity" class="form-control">
          <option>- 请选择登陆身份 -</option>
          <option id="1" name="1">宿管</option>
          <option id="2" name="2">系统管理员</option>
        </select>
      </div>
      <!--
      <div class="form-group">

      </div>
      -->
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"> 记住密码
            </label>
          </div>
        </div>

        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

    <a href="#" style="margin-left: 5px">忘记密码？</a>
    <a href="register.html" class="text-center" style="margin-left: 165px">注册新用户</a><br><br>

    <div class="social-auth-links text-center">
      <p>- 使用第三方登录 -</p>
      <a href="#" class="btn btn-block btn-primary" style="margin-right: 10px;"><i class="fa fa-qq"></i> QQ登录</a>
      <a href="#" class="btn btn-block btn-success"> <i class="fa fa-wechat"></i>微信登录</a>
    </div>

    <!-- /.social-auth-links -->





  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="plugins/iCheck/icheck.min.js"></script>

<script src="layer/layer.js"></script>

<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
</script>

<%
  String msg=(String)session.getAttribute("msg");
  if(msg!=null){
    if(msg.equals("请选择身份！")){
%>
<script>
  layer.tips('<%=msg%>','#Identity',{tips:[2,"#c20c05"]})
</script>
<%
  }else{
%>
<script>
  var index = layer.msg('<%=msg%>',{time:1500})
  layer.style(index, {
    background:'#c20c05',
    color:'#fff'
  });
</script>
<%
    }
  }
  session.setAttribute("msg",null);
%>
<%--<script src="layer/layer.js"></script>--%>
<%--<script>--%>
  <%--layer.open({--%>
    <%--type: 1,--%>
    <%--title:'访客二维码',--%>
    <%--content: '<img src="D:\work\extra\car\car4.jpg">'--%>
  <%--});--%>
<%--</script>--%>

</body>
</html>
