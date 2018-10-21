/**
 * Created by 小奇冰 on 2018/9/23.
 */
//全局函数
jQuery.myPlugin={
    //获取userId
    getUserId:function () {
        var userId = null;
        $.ajax({
            url: '/session/user',
            type: 'get',
            contentType: "application/json",
            async: false,
            success: function(res) {
                if(res.code == '0'){
                    userId = res.data.id;
                }
            }
        });
        return userId;
    },

    //获取用户类型
    getUserType:function () {
        var userType = null;
        $.ajax({
            url: '/session/user',
            type: 'get',
            contentType: "application/json",
            async: false,
            success: function(res) {
                if(res.code == '0'){
                    userType = res.data.type;
                }
            }
        });
        return userType;
    }
};
