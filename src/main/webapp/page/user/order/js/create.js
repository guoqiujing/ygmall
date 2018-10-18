/**
 * Created by Administrator on 2018/10/18.
 */
//获取存在session的用户信息
// var userId = $.myPlugin.getUserId();
//创建Vue实例
var orderFrom = new Vue({
    el: '#orderFrom',
    data: {
        list:[]
    },
    //监听每次数据的修改，并执行修改后的方法
    watch: {

    },
    //创建vue实例之后的事件
    created: function (){
        this.getOrderFrom();
    },
    //方法
    methods: {
        //获取下单列表
        getOrderFrom:function () {
            var that=this;
            $.ajax({
                type: "GET",
                url: "/buyer/order/from",
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code===0){
                        console.log("成功");
                        that.list=msg.data;
                    }
                    else {
                        console.log("查找失败");
                    }
                },
                error: function () {
                    console.log("错误");
                }
            });
        }

    },
    //计算属性，当对象的某个值改变的时候，同时会触发实时计算
    computed: {

    }
})
