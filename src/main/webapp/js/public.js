/**
 * Created by 小奇冰 on 2018/9/24.
 */
//获取存在session的用户信息
var userId = $.myPlugin.getUserId();
//创建Vue实例
var top=new Vue({
    el: '#top',
    data:{
        userId:userId,//从session获取的用户id
        user:{}
    },
    //创建vue实例之后的事件
    created: function (){
        if(this.userId!==null)
            this.getUserInfo();
        else{

        }
    },
    methods: {
        //获取用户信息
        getUserInfo: function() {
            var that=this;
            $.ajax({
                type: "POST",
                url: "/customer/info",
                data: {
                    id:that.userId
                },
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code==0){
                        that.user=msg.data;
                    }
                    else {
                        console.log("查找失败");
                    }
                },
                error: function () {
                    alert("错误");
                }
            });
        },

        //注销
        logout:function () {
            $.ajax({
                type: "POST",
                url: "/app/logout",
                dataType: "json",
                success: function(data){
                    if(data.code==0){
                        console.log("注销成功")
                        window.location.href="/page/user/login.html";
                    }
                }
            });
        }
    }
});