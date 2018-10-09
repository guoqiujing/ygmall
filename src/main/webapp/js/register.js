//检查注册信息合法性
function check(){
    var re= /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
    var re2=/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
    var re3 =  /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/;  //判断字符串是否为数字和字母组合
    if($("#telephone").val()===""){
        layer.msg('请输入手机号', {time: 2000});
        return 1;
    }
    else if($("#email").val()===""){
        layer.msg('请输入邮箱地址', {time: 2000});
        return 2;
    }
    else if(!re.test($("#telephone").val())){
        layer.msg('请输入正确的手机号', {time: 2000});
        return 3;
    }
    else if(!re2.test($("#email").val())){
        layer.msg('请输入正确的邮箱地址', {time: 2000});
        return 4;
    }
    else if($("#password").val()===""){
        layer.msg('请输入密码', {time: 2000});
        return 5;
    }
    else if($("#password").val().length<6||$("#password").val().length>16){
        layer.msg('密码必须为6位以上16位以下', {time: 2000});
        return 6;
    }
    else if (!re3.test($("#password").val())){
        layer.msg('密码必须为数字和字母组合', {time: 2000});
        return 7;
    }
    else if($("#re-password").val()===""){
        layer.msg('请输入确认密码', {time: 2000});
        return 8;
    }
    else if($("#re-password").val() !== $("#password").val()){
        layer.msg('确认密码和密码不一致', {time: 2000});
        return 9;
    }
    else{
        return 0;
    }

}

var countdown=60;

//设置点击后60秒才能重新获取验证码
function setTime(val) {
    if(countdown===60){
        $.ajax({
            type: "POST",
            url: "/customer/info",
            data: {
                id:'f81986cd58214b2b9cc2815ecdd95d3e'
            },
            dataType: "json",
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (msg) {
                if(msg.code===0){
                    console.log("获取用户信息成功");
                }
                else {
                    console.log("查找用户信息失败");
                }
            },
            error: function () {
                layer.msg('发生错误', {time: 900});
            }
        });
    }
    if (countdown === 0) {
        val.removeAttribute("disabled");
        val.value="获取验证码";
        countdown = 60;
    }
    else {
        val.setAttribute("disabled", true);
        val.value="重新发送(" + countdown + ")";
        countdown--;
        setTimeout(function() {
            setTime(val);
        },1000)
    }
}

$(function(){
    //点击注册按钮
    $('#register').click(function(){
        if(check()===0){
            console.log("通过验证");
            //注册
            $.ajax({
                type: "POST",
                url: "/account/register",
                data: {
                    telephone:$("#telephone").val(),
                    email:$("#email").val(),
                    password:$("#password").val()
                },
                dataType: "json",
                success: function(data){
                    console.log(data);
                    if(data.code===0){
                        console.log("注册成功");
                        layer.msg('注册成功', {time: 2000});
                        fun2();
                    }
                    else{
                        console.log("注册失败");
                        layer.msg(data.msg, {time: 2000});
                    }
                }
            });
        }
    });
});

//注册成功后执行登录
function fun2(){
    $.ajax({
        type: "POST",
        url: "/app/login",
        data: {
            code:$("#telephone").val(),
            password:$("#password").val()
        },
        dataType: "json",
        success: function(data){
            console.log("登录成功");
            window.location.href="/page/user/index.html";
        }
    });
}
