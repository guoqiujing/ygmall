// function getSPUCategories(categoryId) {
//     console.log(categoryId);
//     var that = this;
//     $.ajax({
//         type: "POST",
//         url: "/categories/getCompleteCategoriesById",
//         data: {
//             id: categoryId
//         },
//         dataType: "json",
//         contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
//         success: function (data) {
//             if (data.code == 0) {
//                 console.log("成功");
//                 that.categories=data.data[0];
//             }
//             else {
//                 alert("查找失败");
//             }
//         },
//         error: function () {
//             alert("错误");
//         }
//     });
// }
var page = new Vue({
    // el为vue作用范围
    el: '.app',
    data: {
        orders: [
        ],
        categories:[
        ],
    },
    //在vue对象创建完成时自动触发的事件
    created: function (data) {
            var url = decodeURI(location.search); //?id="123456"&Name="bicycle";
            var object = {};
            if(url.indexOf("?") != -1)//url中存在问号，也就说有参数。
            {
                var str = url.substr(1);  //得到?后面的字符串
                var strs = str.split("&");  //将得到的参数分隔成数组[id="123456",Name="bicycle"];
                for(var i = 0; i < strs.length; i ++){
                    object[strs[i].split("=")[0]]=strs[i].split("=")[1]
                }
            }
            var that = this;
            $.ajax({
                url:'/goods/getCompleteGoodsById',
                type: 'post',
                data:{"id":object.id},
                dataType:'json',
                success:function(data){
                    if(data.code==0){
                        that.orders=data.data[0];
                        that.getSPUCategories(data.data[0].spu.categoryId);
                        that.getSPUFormat(data.data[0].spu.attributesName);
                        that.orders.spu.params=JSON.parse(that.orders.spu.params);
                        // var asdf=data.data[0].spu.attributesName;
                        // for (var qaz in asdf)
                        // {
                        //     console.log("---------------------------------------------");
                        //     console.log(asdf[qaz]);
                        // }
                    }
                },
                error:function(data){
                    alert("找不到该商品！");
                }
            })
    },
    // 方法
    methods: {
        getSPUFormat:function(attributesName){//对保存SPU规格的json进行处理
            var that = this;
            var attributesName =JSON.parse(attributesName);//{"规格":"单本;整套","款式":"礼盒版;精装版"};// {"55":"1a","70":"0","80":"2","60":"2"};
            var strlll="";
            var symbol1=1;
            var symbol2=1;
            for (var prop in attributesName)
            {
                var temp=attributesName[prop];//将SPU的规格json的每个键对应的值
                temp=temp.split(";");//进行字符串分割
                var formatDivHTML="<div style=\"margin-bottom: 1em;\">" +
                "<div style=\"float:left;width:15%;padding:0.5em 1em;\"><label>"+prop+"</label></div>" +
                "<div style=\"margin-left:15%;\">" ;
                var date1=(new Date()).getTime()+symbol1;
                console.log("date1"+date1);
                for (var i in temp)
                {
                    var date2=(new Date()).getTime()+symbol2;
                    console.log("date2"+date2);
                    formatDivHTML+= "<label for=\""+date2+"\" class=\"style-radio-label\">" +
                        "<input type=\"radio\" value=\""+temp[i]+"\" id=\""+date2+"\" name=\""+date1+"\" onclick=\"clickRadio(this)\"/>"+temp[i]+"</label>";
                    symbol2++;
                }
                symbol1++;
                formatDivHTML+="</div><div class=\"clear\"></div></div>";
                attributesName[prop]=formatDivHTML;//并将分割结果（数组）赋值回相应的key
                strlll+=formatDivHTML;
            }
            that.orders.spu.attributesName=strlll;
        },
        getSPUCategories: function (categoryId) {//获取指定id的类别的id、name，以及其所属父级、祖级的id、name
            var that = this;
            $.ajax({
                type: "POST",
                url: "/categories/getCompleteCategoriesById",
                data: {
                    id: categoryId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (data) {
                    if (data.code == 0) {
                        that.categories=data.data;
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





        getGoodsImg: function (goodsId) {//获取指定id的类别的id、name，以及其所属父级、祖级的id、name
            var that = this;
            $.ajax({
                type: "POST",
                url: "/categories/getCompleteCategoriesById",
                data: {
                    id: categoryId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (data) {
                    if (data.code == 0) {
                        that.categories=data.data;
                    }
                    else {
                        alert("查找失败");
                    }
                },
                error: function () {
                    alert("错误");
                }
            });
        }
    }
})