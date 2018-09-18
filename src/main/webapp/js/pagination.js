var page = new Vue({
    el: '#app',
    data: {
        all: 8, //总页数
        cur: 1,//当前页码
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
    	]
    },
    //监听每次修改变化的新值
    watch: {
        cur: function(oldValue , newValue){
            console.log(arguments);
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
                        if(msg.data.total/8==0)
                            that.all=1;
                        else
                            that.all=msg.data.total/8+1;
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
            console.log('现在在'+this.cur+'页');
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
})