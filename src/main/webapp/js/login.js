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
            console.log("成功");
            window.location.href="/page/user/index.html";
        }
    });
}

