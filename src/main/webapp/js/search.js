/**
 * Created by Administrator on 2018/10/20.
 */

//创建订单部分的Vue实例
var search = new Vue({
    el: '#search1',
    data: {
        goodsList:[],
        search:'',
        all: 1, //总页数
        cur: 1//当前页码
    },
    //监听每次数据的修改，并执行修改后的方法
    watch: {

    },
    //创建vue实例之后的事件
    created: function (){
        var that = this;
        //截取连接后面的参数
        var url = decodeURI(location.search); //?id="123456"&Name="bicycle";
        var object = {};
        if(url.indexOf("?") != -1)//url中存在问号，也就说有参数。
        {
            var str = url.substr(1);  //得到?后面的字符串
            var arr = str.split("&");  //将得到的参数分隔成数组[id="123456",Name="bicycle"];
            for(var i = 0; i < arr.length; i ++){
                object[arr[i].split("=")[0]]=arr[i].split("=")[1]
            }
            that.search = object.content;
        }
        this.searchGoodsList();
    },
    //方法
    methods: {
        //获取下单列表
        searchGoodsList: function () {
            var that = this;
            var search = that.search;
            console.log("test")
            $.ajax({
                type: "GET",
                url: "/search/"+ search,
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code==0){
                        console.log("成功");
                        that.goodsList = msg.data.rows;
                        that.all=msg.data.pages;
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