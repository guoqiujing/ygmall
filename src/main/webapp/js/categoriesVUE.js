/**
 * Created by Simon on 2018/9/22.
 */
var page = new Vue({
    // el为vue作用范围
    el: '#app',
    data: {
        categoriesL1_2: [],
        categoriesL2_3:[],
        categoriesid_name:[],
    },
    //在vue对象创建完成时自动触发的事件
    created: function (data) {
        var that = this;
        $.ajax({
            url:'/categories/getAllCompleteCategories',
            type: 'post',
            dataType:'json',
            success:function(data){
                if(data.code==0){
                    that.categoriesL1_2=data.data.l12_map;
                    that.categoriesL2_3=data.data.l23_map;
                    that.categoriesid_name=data.data.name_map;
                }
            },
            error:function(data){
                alert("系统错误，请联系管理员！");
            }
        })
    },
})
