/**
 * Created by Administrator on 2018/10/18.
 */
//获取存在session的用户信息
var userId = $.myPlugin.getUserId();
//创建地址部分的Vue实例
var addressFrom = new Vue({
    el: '#addressFrom',
    data: {
        address:{},
        addressList:[],
        isHas:false
    },
    //创建vue实例之后的事件
    created: function (){
        this.getAddress();
    },
    //方法
    methods: {
        //获取该用户的收货地址
        getAddress:function () {
            console.log("用户id："+userId);
            var that = this;
            $.ajax({
                type: "POST",
                url: "/customerAddress/findByUserId",
                data: {
                    userId:userId
                },
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code===0){
                        console.log("成功");
                        that.isHas = true;
                        that.addressList=msg.data;
                        that.address = that.addressList[0]
                    }else{
                        console.log(msg.code)
                    }
                },
                error: function () {
                    // layer.msg('发生错误', {time: 2000});
                }
            });
        },
    },
    //计算属性，当对象的某个值改变的时候，同时会触发实时计算
    computed: {
    }
})
//创建订单部分的Vue实例
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
        getOrderFrom: function () {
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
        orderSum: function () {
            var sum = 0;
            console.log(this.list)
            for(var i = 0; i <this.list.length; i++){
                sum += this.list[i].sum;
            }
            return sum;
        }
    }
})
// 提交订单
function createOrder(){
    //商品信息
    var cart = orderFrom.list;
    //地址信息
    var address = addressFrom.address;
    if(address==null){

    }
    $("#cart").val(JSON.stringify(cart));
    $("#address").val(JSON.stringify(address));
    //请求的url
    var url = "/buyer/order/create" ;
    $("#cartFrom").attr("action",url);
    $("#cartFrom").submit();
}
