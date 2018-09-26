/**
 * Created by 小奇冰 on 2018/9/7.
 */
$(function(){
    $('#register').click(function(){
        // alert($("#telephone").val())
        // alert($("#email").val())
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
                if(data.code==0){
                    console.log("注册成功");
                    layer.msg('注册成功', {time: 2000});
                    fun2();
                }
                else
                    console.log("注册失败")
            }
        });
    });
});

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
