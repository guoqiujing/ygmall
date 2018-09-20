//创建Vue实例
var page = new Vue({
    el: '#app',
    data: {
        all: 8, //总页数
        cur: 1,//当前页码
        tab:-1,//选项卡切换标志
        orders:[
	      { id: '2018090288111' , time:'2018-09-03' , money:'78.00' ,status:'待付款',
	      	href:'index.html' 
	      },
	      { id: '2018090288585' , time:'2018-09-02' , money:'144.00' ,status:'待付款',
	      	href:'index.html' 
	      },
	      { id: '2018090288987' , time:'2018-09-01' , money:'255.00' ,status:'待付款',
	      	href:'index.html' 
	      },
	      { id: '2018090288123' , time:'2018-09-01' , money:'366.00' ,status:'待付款',
	      	href:'index.html' 
	      },
	      { id: '2018090285674' , time:'2018-09-01' , money:'55.00' ,status:'待付款',
	      	href:'index.html' 
	      },
	      { id: '2018090285674' , time:'2018-09-01' , money:'55.00' ,status:'待付款',
	      	href:'index.html' 
	      },
	      { id: '2018090285674' , time:'2018-09-01' , money:'55.00' ,status:'待付款',
	      	href:'index.html' 
	      },
	      { id: '2018090285674' , time:'2018-09-01' , money:'55.00' ,status:'待付款',
	      	href:'index.html' 
	      }
    	],//订单list
        user:{},//用户信息
        oldPwd:'',//原密码
        newPwd:'',//新密码
        reNewPwd:''//重复新密码
    },
    //监听每次修改变化的新值
    watch: {
        cur: function(oldValue , newValue){
            console.log(arguments);
        }
    },
    //创建vue实例之后的事件
	created: function (){
		console.log("vue:"+GetQueryString("tab"));
		this.tab=GetQueryString("tab");
        if(this.tab==="4"){
            console.log("个人资料");
            this.getUserInfo();
        }
	},

    //方法
    methods: {
        btnClick: function(data){//页码点击事件
            var that=this;
            $.ajax({
                type: "POST",
                url: "/brand/list",
                data: {
                    pageSize:8,
                    pageIndex:data,
                    brandName: "",
                    brandStatus:null
                },
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code==0){
                        console.log("成功")
                        // if(msg.data.total/8==0)
                        //     that.all=1;
                        // else
                        //     that.all=msg.data.total/8+1;
                        console.log(msg.data.rows)
                    }
                    else {
                        alert("查找失败");
                    }
                },
                error: function () {
                    alert("错误");
                }
            });
            if(data != this.cur){
                this.cur = data
            }
        },

        pageClick: function(){
            //console.log('现在在'+this.cur+'页');
        },

        //获取用户信息
        getUserInfo: function() {
            var that=this;
            $.ajax({
                type: "POST",
                url: "/customer/info",
                data: {
                    id:"2d69b37b3ba649479a56a3133552d247"
                },
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code==0){
                        console.log("成功");
                        console.log(msg.data);
                        that.user=msg.data;
                    }
                    else {
                        alert("查找失败");
                    }
                },
                error: function () {
                    alert("错误");
                }
            });
        },

        //更新用户信息
        updateUserInfo:function () {
            $.ajax({
                type: "POST",
                url: "/customer/update",
                data: {
                    id:this.user.id,
                    nickname:this.user.nickName,
                    icon:this.user.icon,
                    sex:this.user.sex,
                    telephone:this.user.telephone,
                    email:this.user.email
                },
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code==0){
                        console.log("成功");
                        layer.msg('修改成功', {
                            time: 900
                        }, function(){
                        });
                    }
                    else {
                        alert("查找失败");
                    }
                },
                error: function () {
                    alert("错误");
                }
            });
        },

        //判断密码是否合法
        judgePwd:function () {
            var re =  /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/;  //判断字符串是否为数字和字母组合
            if(this.oldPwd===""){
                layer.msg('请输入原密码', {time: 2000});
            }
            else if(this.newPwd===""){
                layer.msg('请输入新密码', {time: 2000});
            }
            else if(this.newPwd.length<6||this.newPwd.length>16){
                layer.msg('密码必须为6位以上16位以下', {time: 2000});
            }
            else if (!re.test(this.newPwd)){
                layer.msg('密码必须为数字和字母组合', {time: 2000});
            }
            else if(this.reNewPwd !== this.newPwd){
                layer.msg('重复密码和新密码不一致', {time: 2000});
            }
            else{
                this.updatePwd();
            }
        },

        //修改密码
        updatePwd:function () {
            var that=this;
            $.ajax({
                type: "POST",
                url: "/account/updatePwd",
                data: {
                    id:"2d69b37b3ba649479a56a3133552d247",
                    oldPwd:this.oldPwd,
                    newPwd:this.newPwd
                },
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code==0){
                        console.log("成功");
                        layer.msg('修改成功', {time: 2000});
                        that.oldPwd="";
                        that.newPwd="";
                        that.reNewPwd="";
                    }
                    else if(msg.code==1){
                        layer.msg('原密码输入错误', {time: 1500});
                    }
                },
                error: function () {
                    alert("错误");
                }
            });
        }
},

    //计算属性，当对象的某个值改变的时候，同时会触发实时计算
    computed: {
        indexs: function(){
            var left = 1;
            var right = this.all;
            var ar = [];
            //总页数大于5时
            if(this.all>= 5){
                if(this.cur > 3 && this.cur < this.all-2){
                    left = this.cur - 2
                    right = this.cur + 2
                }else{
                    if(this.cur<=3){
                        left = 1
                        right = 5
                    }else{
                        right = this.all
                        left = this.all -4
                    }
                }
            }
            while (left <= right){
                ar.push(left)
                left ++
            }
            return ar
        }

    }
});

$(document).ready(function(){

});

//取出url?后某个参数的值
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if(r!=null)return  decodeURI(r[2]); return null;
}
