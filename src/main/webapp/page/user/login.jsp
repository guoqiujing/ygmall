
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录我的艺格</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />

    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content="" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:url" content="" />
    <meta name="twitter:card" content="" />

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/animate.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/index.css">
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/login.js"></script>
</head>
<%
    String username=request.getParameter("username");
    session.setAttribute("username",username);
%>
<body class="style-2">
<div class="container-fluid head" style="border-bottom: 1px solid #DEDEDE; background-color: #F2F2F2;color: #484848;">
    <div class="container">
        <div class="row" style="font-size: 12px;padding:5px;">
            <div class="col-md-2">欢迎来到艺格艺术生用品商城</div>
            <div class="col-md-1">请登录<%=username%></div>
            <div class="col-md-1">免费注册</div>
            <!--<div class="col-md-2" style="float: right;">官方微信商城</div>-->
            <div class="col-md-1" style="float: right;">我的艺格</div>
            <div class="col-md-1" style="float: right;">我的收藏</div>
            <div class="col-md-1" style="float: right;">订单查询</div>
        </div>
    </div>
</div>

<div class="head" style="margin: 20px 0 20px 0;">
    <div class="head-center" style="justify-content: space-between;">

        <div style="margin-right: 0;">
            <img src="../../img/yigeshangchenglogo.png">
        </div>

        <div class="divcenter">
            <div class="input-group" style="width:400px;">
                <input type="text" class="form-control" style="border-color: #987E46;">
                <span class="input-group-btn">
		                        <button class="btn btn-default" type="button" style="color: #fff;background-color: #987E46;border-color: #987E46;">搜索</button>
		                    </span>
            </div>
        </div>

        <a href="#" class="divcenter" style="font-size:15px;color: #666;border: 1px solid #E6E6E6;width: 132px;height: 38px;background-color: #F2F2F2;">
            <img src="../../img/carts.png" style="margin-right: 10px;" /> 购物车
        </a>

    </div>
</div>

<div class="head" style="background-color: #181818;">
    <div class="head-center" style="padding: 0;background-color:#181818 ;height: 50px;">

        <ul class="nav navbar-nav">
            <li class="active" style="background-color: #484848;">
                <a href="#" style="padding: 15px 50px 15px 50px;" onclick="test()">热门商品分类</a>
            </li>
            <li>
                <a href="#" style="padding: 15px 30px ;">首页</a>
            </li>
            <li>
                <a href="#" style="padding: 15px 30px ;">美术生专区</a>
            </li>
            <li>
                <a href="#" style="padding: 15px 30px ;">音乐生专区</a>
            </li>
            <li>
                <a href="#" style="padding: 15px 30px ;">舞蹈生专区</a>
            </li>
        </ul>

    </div>
</div>
<div class="container">
    <div class="row">
        <!--<div class="col-md-12 text-center">
            <ul class="menu">
                <li><a href="index.html">Style 1</a></li>
                <li class="active"><a href="index2.html">Style 2</a></li>
                <li><a href="index3.html">Style 3</a></li>
            </ul>
        </div>-->
    </div>
    <div class="row">
        <div class="col-md-4">


            <!-- Start Sign In Form -->
            <form action="#" class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
                <h2 style="font-size:24px ;font-family:'幼圆';color:#985F0D">您好，欢迎登录 ！</h2>
                <div class="form-group">
                    <label for="username" class="sr-only">Username</label>
                    <input type="text" class="form-control" id="username" placeholder="艺格账号/手机号" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" class="form-control" id="password" placeholder="密码" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="remember"><input type="checkbox" id="remember"> 记住我</label>
                </div>
                <div class="form-group">
                    <p>Not registered? <a href="sign-up2.html" style="color:#987E46">注 册</a> | <a href="forgot2.html" style="color:#987E46">忘记密码?</a></p>
                </div>
                <div class="form-group">
                    <input type="bottom" value="登 录" class="btn" style="background-color: #987E46;color:white" id="login" onclick="fun2()">
                </div>
            </form>

        </div>
    </div>
    <div class="row" style="padding-top: 60px; clear: both;">
        <div class="col-md-12 text-center"><p><small>Copyright &copy; 2018 - 2032  艺格YG.com 版权所有|消费者维权热线：8008208820</small></p></div>
    </div>
</div>

<!-- jQuery -->
<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>

</body>
</html>
