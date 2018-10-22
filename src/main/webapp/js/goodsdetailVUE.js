var staticID="";
function addSelectedNum(){
    $(".selectNum").val(parseInt($('.selectNum').val())+parseInt(1));
}
function subtractSelectedNum(){
    $(".selectNum").val(parseInt($('.selectNum').val())-parseInt(1));
}
function changeRadio(partName,radioGroup){
    for (var i=0;i<radioGroup.size();i++) {//遍历第三组规格并组合成属性字符串，与该货品所有的商品的属性字符串对比，不存在的就将按钮设为灰色
        var tempRadio=$(radioGroup[i]).find("input");
        var partName2=tempRadio.val();
        for(var key in page.formatCombination) {
            if(key.indexOf(partName+"-"+partName2)==0){

                $(radioGroup[i]).find("input[value='"+partName2+"']").removeAttr("disabled");
                $(radioGroup[i]).find("input[value='"+partName2+"']").parent().removeClass("disclickRadio");
                // tempRadio.removeAttr("disabled");
                // tempRadio.parent().removeClass("disclickRadio");
            }
        }
    }
}
// 规格选框的选中逻辑及样式变化
function clickRadio(target){
    var partName1="",partName2="",partName3="";
    $("input[type='radio'][name='" + $(target).attr('name') + "']").parent().removeClass("checked");//移除所有规格选框的选中状态
    $(target).parent().addClass("checked");//给当前点击的选框所在的label添加选中样式
    var clickedRadio=$(target).attr('name');
    $(target).parents(".RadioGroupDIV").nextAll().find("input[type='radio']").parent().addClass("disclickRadio");//修改单选按钮样式为“不可点击
    $(target).parents(".RadioGroupDIV").nextAll().find("input[type='radio']").attr("disabled","disabled");
    if($("input[type='radio'][name='format2']").length!=0){//三种规格的情况
        if(clickedRadio!="format2"){//如果选中的不是第三种规格，则取消掉位于所选中的规格之后的所有规格的选中状态
            $(".formatDIV").css({"border":"red solid 2px","padding":"10px"});
            $(target).parents(".RadioGroupDIV").nextAll().find(".style-radio-label").removeClass("checked");
            $("#BtnArea button").attr("disabled","true");
            $("#BtnArea button").css({"background":"#f1f1f1","border":"#b5b3b3 2px dashed","color":"#9a9a9a"});
        }
        else{
            $(".formatDIV").css({"border":"red solid 0px","padding":"0px"});
            $("#BtnArea button").removeAttr("disabled");
            $("#BtnArea .btn-buy-now").css({"background":"#F4F0E7","border":"1px solid #B4995D","color":"#484848"});
            $("#BtnArea .bnt-add-cart").css({"background":"#987E46","border":"0px solid #B4995D","color":"#FFF"});
        }
        if($(target).attr("name")=="format0") {//如果点击的是第一级规格
            partName1 = $(target).val();
            var radioGroup2 = $(target).parents(".RadioGroupDIV").next().find(".style-radio-label");//取得第二组规格单选按钮
            changeRadio(partName1,radioGroup2);
        }
        if($(target).attr("name")=="format1") {//如果点击的是第二级规格
            partName1 = $("input[name='format0']:checked").val();
            partName2 = $(target).val();
            var radioGroup2 = $(target).parents(".RadioGroupDIV").next().find(".style-radio-label");//取得第二组规格单选按钮
            var radioGroup3 = $(target).parents(".RadioGroupDIV").next().find(".style-radio-label");//取得第三组规格单选按钮
            changeRadio(partName1,radioGroup2);
            changeRadio(partName1+"-"+partName2,radioGroup3);
        }
        if($(target).attr("name")=="format2") {//如果点击的是第三级规格，跳转到新的商品页面
            partName1 = $("input[name='format0']:checked").val();
            partName2 = $("input[name='format1']:checked").val();
            partName3 = $("input[name='format2']:checked").val();
            var newGoodsId=page.formatCombination[partName1 + "-" + partName2 + "-" + partName3];
            window.location.href='../user/goodsdetail.jsp?id='+newGoodsId;
        }
    }

    else if($("input[type='radio'][name='format1']").length!=0){//两种规格的情况
        if(clickedRadio!="format1"){//如果选中的不是第二种规格，则取消掉位于所选中的规格之后的所有规格的选中状态
            $(".formatDIV").css({"border":"red solid 2px","padding":"10px"});
            $(target).parents(".RadioGroupDIV").nextAll().find(".style-radio-label").removeClass("checked");
            $("#BtnArea button").attr("disabled","true");
            $("#BtnArea button").css({"background":"#f1f1f1","border":"#b5b3b3 2px dashed","color":"#9a9a9a"});
        }
        else{
            $(".formatDIV").css({"border":"red solid 0px","padding":"0px"});
            $("#BtnArea button").removeAttr("disabled");
            $("#BtnArea .btn-buy-now").css({"background":"#F4F0E7","border":"1px solid #B4995D","color":"#484848"});
            $("#BtnArea .bnt-add-cart").css({"background":"#987E46","border":"0px solid #B4995D","color":"#FFF"});
        }
        if($(target).attr("name")=="format0") {//如果点击的是第一级规格
            partName1 = $(target).val();
            var radioGroup2 = $(target).parents(".RadioGroupDIV").next().find(".style-radio-label");//取得第三组规格单选按钮
            for (var i=0;i<radioGroup2.size();i++) {
                var tempRadio=$(radioGroup2[i]).find("input");//单选按钮组里的第i个单选按钮
                partName2=tempRadio.val();
                for(var key in page.formatCombination) {
                    if(key.indexOf(partName1+"-"+partName2)==0){
                        tempRadio.removeAttr("disabled");
                        tempRadio.parent().removeClass("disclickRadio");
                    }
                }
            }
        }
        if($(target).attr("name")=="format1") {//如果点击的是第二级规格，跳转到新的商品页面
            partName1 = $("input[name='format0']:checked").val();
            partName2 = $("input[name='format1']:checked").val();
            var newGoodsId=page.formatCombination[partName1 + "-" + partName2 ];
            window.location.href='../user/goodsdetail.jsp?id='+newGoodsId;
        }
    }
    else{//一种规格的情况
        if($(target).attr("name")=="format0") {
            partName1 = $("input[name='format0']:checked").val();
            var newGoodsId=page.formatCombination[partName1];
            window.location.href='../user/goodsdetail.jsp?id='+newGoodsId;
        }
    }
}
/*
 * 鼠标移动到商品细节图小图时，将小图替换到大图位置
 */
function changeimg(target){
    var str=$(target).find("img").attr("src");
    $(".small-box").find("img").attr("src",str);
    $(".big-box").find("img").attr("src",str);
}
/*
 * 切换商品详情页的商品详情、评论、规格、服务
 */
function detailForward(target){
    var str=$(target).attr("href").substring(1);
    $(".goods-nav li a").attr("class","");
    $(target).attr("class","active");
    if(str=="goods-details"){
        $(".goods-details").css("display","block");
        $(".goods-format").css("display","none");
        $(".goods-comment").css("display","block");
    }
    if(str=="goods-format"){
        $(".goods-details").css("display","none");
        $(".goods-format").css("display","block");
        $(".goods-comment").css("display","block");
    }
    if(str=="goods-comment"){
        $(".goods-details").css("display","none");
        $(".goods-format").css("display","none");
        $(".goods-comment").css("display","block");
        page.getComments();
    }
    if(str=="service-detail"){
        $(".goods-details").css("display","none");
        $(".goods-format").css("display","none");
        $(".goods-comment").css("display","none");
    }
}

// 商品放大镜实现步骤：
// 第一步：当页面加载完后，获取所要操作的节点对象。
// 第二步：为smallBox添加一个鼠标浮动事件
// 当鼠标浮动到smallBox可视区域的时候，显示出小黄盒子tool
// 和右边的大盒子（小黄盒子的放大版）bigBox
// 添加active
//
// 为smallBox添加一个鼠标离开事件
// 隐藏小黄盒子和右边的大盒子
// 去掉active
//
// 第三步：为smallBox添加一个鼠标移动事件
// 小黄盒子tool要跟着鼠标的坐标移动
// 右边的大盒子里的图片也跟着指定的比例移动

// var smallBox = document.getElementById("smallBox");           //小盒子
// var tool = document.getElementById("tool");                         //小盒子中的黄色区域
// var bigBox = document.getElementById("bigBox");                //大盒子
// var bigImg = document.getElementById("bigImg");               //放大的图片

//鼠标进入小盒子区域内，显示黄色区域和大盒子
function mouseEnterSmallBox(){
    var tool = document.getElementById("tool");//小盒子中的黄色区域
    var bigBox = document.getElementById("bigBox");//大盒子
    $("#bigBox").css("height",$("#smallBox").width());
    tool.className = "tool active";
    bigBox.className = "big-box active";
}
//鼠标离开小盒子区域，不显示黄色区域和大盒子
function mouseLeaveSmallBox(){
    var tool = document.getElementById("tool");//小盒子中的黄色区域
    var bigBox = document.getElementById("bigBox");//大盒子
    tool.className = "tool";
    bigBox.className = "big-box";
}
//鼠标在小盒子内移动
function mouseMoveSmallBox(e){
    // console.log("page.formatCombination"+page.formatCombination['整套-一年保修']);
    // for(var key in page.formatCombination){
    //     if(key.indexOf('整套')==0)
    //        console.log(key +":"+page.formatCombination[key]);
    // }
    // var smallBox = document.getElementById("smallBox");//小盒子
    var tool = document.getElementById("tool");//小盒子中的黄色区域
    // var bigBox = document.getElementById("bigBox");//大盒子
    var bigImg = document.getElementById("bigImg");//放大的图片
    var _e = window.event||e;//事件对象
    //鼠标离页面左侧的距离-存放图片的框左侧离页面左侧的距离-小黄块的一半宽度
    var x = _e.pageX-$(".small-box").offset().left-tool.offsetWidth/2;//事件对象在小盒子内的横向偏移量
    var y = _e.pageY-$(".small-box").offset().top-tool.offsetHeight/2;//竖向偏移量
    if(x<0){
        x = 0;//当左偏移出小盒子时，设为0
    }
    if(y<0){
        y = 0;//当上偏移出小盒子时，设为0
    }
    if(x>$("#smallBox").width()-tool.offsetWidth){
        x = $("#smallBox").width()-tool.offsetWidth;//当右偏移出小盒子时，设为小盒子的宽度-黄色放大区域宽度
    }
    if(y>$("#smallBox").height()-tool.offsetHeight){
        y = $("#smallBox").height()-tool.offsetHeight//当下偏移出小盒子时，设为小盒子的高度-黄色放大区域高度
    }
    tool.style.left = x + "px";//黄色放大区域距离小盒子左偏距
    tool.style.top = y + "px";//黄色放大区域距离小盒子上偏距
    /*因为图片放大了两倍，所以偏移距离也得放大两倍，相当于控制放大图的移动速度*/
    /*小黄块右移，放大图片的就得相对其所在的div往左移*/
    bigImg.style.left = -x*2 + "px";
    bigImg.style.top = -y*2 + "px";
}
// “立即购买”
function buyNow(){
    var id=staticID;
    var name=page.orders.name;
    var price=page.orders.price;
    var attributes=$(".formatDIV input[type='radio']:checked").eq(0).val();
    for(var i=1;i<$(".formatDIV input[type='radio']:checked").length;i++)
        attributes+="-"+$(".formatDIV input[type='radio']:checked").eq(i).val();
    var count = $(".selectNum").val();
    //构造json参数串
    var data = [];
    var element = {};
    element.goodsNumber = staticID;
    element.goodsName = page.orders.name;
    element.price = page.orders.price;
    element.formatAndStyle = attributes;
    element.count = count;
    element.goodsPicture = page.goodsImgs[0].imgUrl;
    data.push(element);
    $("#cart").val(JSON.stringify(data));
    //请求的url
    var url = "/buyer/order/to/order" ;
    $("#cartFrom").attr("action",url);
    $("#cartFrom").submit();
    // $.ajax({
    //     url:'/buyer/order/to/order',
    //     type:'post',
    //     data:{id:id,name:name,price:price,attributes:attributes,count:count},
    //     dataType:'json',
    //     success:function(){
    //         console.log("立即购买触发成功");
    //     },
    //     error:function(){
    //         console.log("立即购买触发失败");
    //     }
    // })
}





var page = new Vue({
    // el为vue作用范围
    el: '.app',
    data: {
        orders: [],
        categories:[],
        goodsImgs:[],
        spuDetail:[],
        format0:"",
        format1:"",
        format2:"",
        formatCombination:[],
        // {
        //     "整套-礼盒版-一年保修": "1",
        //     "单本-精装版-默认": "2",
        //     "单本-精装版-一年保修": "3",
        //     "单本-礼盒版-一年保修": "4"
        // },
        comments: [],
        commentTab: -1,//评价类型选项卡
        all: 8, //总页数
        cur: 1,//当前页码
    },
    //监听每次数据的修改，并执行修改后的方法
    watch: {
        cur: function () {
            this.getComments();
        },
        //评价类型选项卡变化时，请求信数据
        commentTab: function () {
            console.log(this.commentTab);
            this.cur = 1;
            this.getComments();
        }
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
                staticID=object.id;
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
                        var format=that.orders.attributes.split("-");
                        that.format0=format[0]!=null?format[0].split(";")[0]:format[0];
                        that.format1=format[1]!=null?format[1].split(";")[0]:format[1];
                        that.format2=format[2]!=null?format[2].split(";")[0]:format[2];
                        that.getSPUCategories(data.data[0].spu.categoryId);
                        // that.getSPUFormat(data.data[0].spu.attributesName);
                        that.orders.spu.params=JSON.parse(that.orders.spu.params);
                        that.getGoodsImg(object.id);
                        that.getSpuDetail(that.orders.spu.id);
                        that.getFormatCombination(that.orders.spu.id);
                    }
                    else{
                        window.location.href="../404.html";
                    }
                },
                error:function(data){
                    window.location.href="../404.html";
                }
            })

    },
    // 方法
    methods: {
        overVForMain:function(){
            var partName1 = $("input[name='format0']:checked").val();
            var partName2 = $("input[name='format1']:checked").val();
            var partName3 = $("input[name='format2']:checked").val();
            if($("input[type='radio'][name='format2']").length!=0) {//三种规格的情况
                $(".RadioGroupDIV").eq(2).find("input[type='radio']").parent().addClass("disclickRadio");//修改单选按钮样式为“不可点击
                $(".RadioGroupDIV").eq(2).find("input[type='radio']").attr("disabled","disabled");
                var radioGroup3 = $(".RadioGroupDIV").eq(2).find(".style-radio-label");//取得第三组规格单选按钮
                for (var i=0;i<radioGroup3.size();i++) {//遍历第三组规格并组合成属性字符串，与该货品所有的商品的属性字符串对比，不存在的就将按钮设为灰色
                    for(var key in page.formatCombination) {
                        partName3=$(radioGroup3[i]).find("input").val();
                        console.log(partName1+"-"+partName2+"-"+partName3);
                        if(key.indexOf(partName1+"-"+partName2+"-"+partName3)==0){
                            $(radioGroup3[i]).find("input").removeAttr("disabled");
                            $(radioGroup3[i]).find("input").parent().removeClass("disclickRadio");
                        }
                    }
                }
            }
            else if($("input[type='radio'][name='format1']").length!=0) {//两种规格的情况
                $(".RadioGroupDIV").eq(1).find("input[type='radio']").parent().addClass("disclickRadio");//修改单选按钮样式为“不可点击
                $(".RadioGroupDIV").eq(1).find("input[type='radio']").attr("disabled","disabled");
                var radioGroup2 = $(".RadioGroupDIV").eq(1).find(".style-radio-label");//取得第三组规格单选按钮
                for (var i=0;i<radioGroup2.size();i++) {//遍历第三组规格并组合成属性字符串，与该货品所有的商品的属性字符串对比，不存在的就将按钮设为灰色
                    for(var key in page.formatCombination) {
                        partName2=$(radioGroup2[i]).find("input").val();
                        if(key.indexOf(partName1+"-"+partName2)==0){
                            $(radioGroup2[i]).find("input").removeAttr("disabled");
                            $(radioGroup2[i]).find("input").parent().removeClass("disclickRadio");
                        }
                    }
                }
            }
            else{
                $(".RadioGroupDIV").eq(0).find("input[type='radio']").parent().addClass("disclickRadio");//修改单选按钮样式为“不可点击
                $(".RadioGroupDIV").eq(0).find("input[type='radio']").attr("disabled","disabled");
                var radioGroup1 = $(".RadioGroupDIV").eq(0).find(".style-radio-label");//取得第三组规格单选按钮
                for (var i=0;i<radioGroup1.size();i++) {//遍历第三组规格并组合成属性字符串，与该货品所有的商品的属性字符串对比，不存在的就将按钮设为灰色
                    for(var key in page.formatCombination) {
                        partName1=$(radioGroup1[i]).find("input").val();
                        if(key.indexOf(partName1)==0){
                            $(radioGroup1[i]).find("input").removeAttr("disabled");
                            $(radioGroup1[i]).find("input").parent().removeClass("disclickRadio");
                        }
                    }
                }
            }
        },
        getFormatCombination:function(spuId){//对保存SPU规格的json进行处理
            var that = this;
            $.ajax({
                type: "POST",
                url: "/goods/getIdAndAttributesBySpuId",
                data: {
                    spuId: spuId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (data) {
                    if (data.code == 0) {
                        that.formatCombination=data.data;
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
                url: "/goodsImg/getGoodsImgByGoodsId",
                data: {
                    goodsId: goodsId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (data) {
                    if (data.code == 0) {
                        that.goodsImgs=data.data;
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
        getSpuDetail: function (spuId) {//获取指定id的类别的id、name，以及其所属父级、祖级的id、name
            var that = this;
            $.ajax({
                type: "POST",
                url: "/spuDetail/getSpuDetailBySPUId",
                data: {
                    spuId: spuId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (data) {
                    if (data.code == 0) {
                        that.spuDetail=data.data;
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
        getComments: function () {
            var url = decodeURI(location.search); //?id="123456"&Name="bicycle";
            var object = {};
            if (url.indexOf("?") != -1)//url中存在问号，也就说有参数。
            {
                var str = url.substr(1);  //得到?后面的字符串
                var strs = str.split("&");  //将得到的参数分隔成数组[id="123456",Name="bicycle"];
                for (var i = 0; i < strs.length; i++) {
                    object[strs[i].split("=")[0]] = strs[i].split("=")[1]
                }
            }
            var that = this;
            $.ajax({
                type: 'GET',
                url: "/comment/comList/" + object.id + "?goodsScore=" + that.commentTab + "&page=" + that.cur + "&size=" + 8,
                dataType: "json",
                contentType: 'application/json;charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("评论查找成功");
                        console.log(msg.data.rows);
                        that.comments = msg.data.rows;
                        that.all = msg.data.pages;
                    }
                    else {
                        console.log("评论查找失败");
                        that.comments = [];
                        that.all = 1;
                    }
                },
                error: function () {
                    console.log("查找评论错误");
                }
            });
        },
        /*formatDateTime: function (inputTime) {
         var date = new Date(inputTime);
         var y = date.getFullYear();
         var m = date.getMonth() + 1;
         m = m < 10 ? ('0' + m) : m;
         var d = date.getDate();
         d = d < 10 ? ('0' + d) : d;
         var h = date.getHours();
         h = h < 10 ? ('0' + h) : h;
         var minute = date.getMinutes();
         var second = date.getSeconds();
         minute = minute < 10 ? ('0' + minute) : minute;
         second = second < 10 ? ('0' + second) : second;
         return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
         },*/
        //点击订单页面页码
        btnClick: function (data) {
            //console.log(data);
            if (data !== this.cur) {
                this.cur = data;
            }
        },

        pageClick: function () {
            //console.log('现在在'+this.cur+'页');
        },
        showImg:function (url) {
            console.log(url);
            var img = "<img src='" + url + "' />";
            img.src = url;
            var setting = {
                type: 1,
                shadeClose: true,
                scrollbar: false,//屏蔽浏览器滚动条
                skin: 'layui-layer-nobg', //没有背景色
                title: false, //不显示标题
                content: img //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            }
            var windowH = $(window).height();
            var windowW = $(window).width();
            console.log(windowH);
            console.log(windowW);
            this.getImageWidth(url, function (w, h) {
                // 调整图片大小
                console.log(w);
                console.log(h);
                if (w > windowW || h > windowH) {
                    if (w > windowW && h > windowH) {
                        setting.area = [windowW + "px", windowH + "px"];
                    } else if (w > windowW) {
                        setting.area = [windowW + 'px', 'auto'];
                    } else {
                        setting.area = ['auto', window.innerHeight + 'px'];
                        console.log(setting.area+"#");
                    }
                }
                else{
                    setting.area = [img.width + 'px', img.height + 'px'];
                    console.log(img.width+"*");
                }
                console.log(img);
                console.log(setting);
                layer.open(setting);
            });
        },
        getImageWidth:function (url,callback) {
            var img = new Image();
            img.src = url;
            // 如果图片被缓存，则直接返回缓存数据
            if(img.complete){
                callback(img.width, img.height);
            }else{
                // 完全加载完毕的事件
                img.onload = function(){
                    callback(img.width, img.height);
                }
            }
            console.log(callback+"?");
        }
    },
    //计算属性，当对象的某个值改变的时候，同时会触发实时计算
    computed: {
        indexs: function () {
            var left = 1;
            var right = this.all;
            var ar = [];
            //总页数大于5时
            if (this.all >= 5) {
                if (this.cur > 3 && this.cur < this.all - 2) {
                    left = this.cur - 2;
                    right = this.cur + 2;
                } else {
                    if (this.cur <= 3) {
                        left = 1;
                        right = 5;
                    } else {
                        right = this.all;
                        left = this.all - 4;
                    }
                }
            }
            while (left <= right) {
                ar.push(left);
                left++;
            }
            return ar;
        }
    }
})