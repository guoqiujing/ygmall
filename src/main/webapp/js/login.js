/**
 * Created by 小奇冰 on 2018/9/6.
 */
function fun2(){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/app/login",
        data: {
            code:$("#username").val(),
            password:$("#password").val()
        },
        dataType: "json",
        success: function(data){

            alert("登录成功！欢迎您")
            console.log("成功")
            console.log(data)
        }
    });
}

