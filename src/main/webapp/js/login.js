/**
 * Created by 小奇冰 on 2018/9/6.
 */
function fun2(){
    $.ajax({
        type: "POST",
        url: "/app/login",
        data: {
            code:$("#username").val(),
            password:$("#password").val()
        },
        dataType: "json",
        success: function(data){
            console.log(data);
            if(data.code===0){
                console.log("登录成功");
                window.location.href="/page/user/index.html";
            }
            if(data.code===1){
                console.log("登录失败");
                layer.msg('账号或者密码错误', {time: 2000});
            }
        }
    });
}

