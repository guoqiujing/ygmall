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
    }
};
