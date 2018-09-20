/**
 * Created by 小奇冰 on 2018/9/7.
 */
$(function(){
    $('#register').click(function(){
        alert($("#telephone").val())
        alert($("#email").val())
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
                if(data.code==0)
                console.log("注册成功")
                else
                    console.log("注册失败")
                console.log(JSON.stringify(data))
            }
        });
    });
});
