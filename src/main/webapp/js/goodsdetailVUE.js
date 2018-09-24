function addSelectedNum(){
    $(".selectNum").val(parseInt($('.selectNum').val())+parseInt(1));
}
function subtractSelectedNum(){
    $(".selectNum").val(parseInt($('.selectNum').val())-parseInt(1));
}
// 规格选框的选中逻辑及样式变化
function clickRadio(target){
    var partName1="",partName2="",partName3="";
    $("input[type='radio'][name='" + $(target).attr('name') + "']").parent().removeClass("checked")
    $(target).parent().addClass("checked");
    var clickedRadio=$(target).attr('name');

    $(target).parents(".RadioGroupDIV").nextAll().find("input[type='radio']").parent().addClass("disclickRadio");//修改单选按钮样式为“不可点击
    $(target).parents(".RadioGroupDIV").nextAll().find("input[type='radio']").attr("disabled","disabled");

    if($(target).attr("name")=="format0") {//如果点击的是第一级规格
        partName1 = $(target).val();
        var radioGroup2 = $(target).parents(".RadioGroupDIV").next().find(".style-radio-label");//取得第二组规格单选按钮
        for (var i=0;i<radioGroup2.size();i++) {
            var tempRadio=$(radioGroup2[i]).find("input");
            partName2=tempRadio.val();
            for(var key in page.formatCombination) {
                if(key.indexOf(partName1+"-"+partName2)==0){
                    tempRadio.removeAttr("disabled");
                    tempRadio.parent().removeClass("disclickRadio");
                }
            }
        }
    }
    if($(target).attr("name")=="format1") {//如果点击的是第二级规格
        partName1 = $("input[name='format0']:checked").val();
        partName2 = $(target).val();
        var radioGroup3 = $(target).parents(".RadioGroupDIV").next().find(".style-radio-label");//取得第三组规格单选按钮
        for (var i=0;i<radioGroup3.size();i++) {
            var tempRadio=$(radioGroup3[i]).find("input");//单选按钮组里的第i个单选按钮
            partName3=tempRadio.val();
            for(var key in page.formatCombination) {
                if(key.indexOf(partName1+"-"+partName2+"-"+partName3)==0){
                    tempRadio.removeAttr("disabled");
                    tempRadio.parent().removeClass("disclickRadio");
                }
            }
        }
    }
    if($(target).attr("name")=="format2") {//如果点击的是第三级规格，跳转到新的商品页面
        partName1 = $("input[name='format0']:checked").val();
        partName2 = $("input[name='format1']:checked").val();
        partName3 = $("input[name='format2']:checked").val();
        var newGoodsId=page.formatCombination[partName1 + "-" + partName2 + "-" + partName3];
        window.location.href='../user/goodsdetail.jsp?id='+newGoodsId;
    }
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
    }
    else{//一种规格的情况
        ;
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
    console.log("jinlaile");
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
                        console.log(that.orders);
                        var format=that.orders.attributes.split("-");
                        that.format0=format[0];
                        that.format1=format[1];
                        that.format2=format[2];
                        that.getSPUCategories(data.data[0].spu.categoryId);
                        // that.getSPUFormat(data.data[0].spu.attributesName);
                        that.orders.spu.params=JSON.parse(that.orders.spu.params);
                        that.getGoodsImg(object.id);
                        that.getSpuDetail(that.orders.spu.id);
                        that.getFormatCombination(that.orders.spu.id);
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
                    console.log("date2"+date2);                                                     //class=\"style-radio-label\"
                    formatDivHTML+= "<label for=\""+date2+"\" class=\"style-radio-label\" >" +
                        "<input v-model=\"sex\" type=\"radio\" value=\""+temp[i]+"\" id=\""+date2+"\" name=\""+date1+"\" onclick=\"clickRadio(this)\"/>"+temp[i]+"</label>";
                    symbol2++;
                }
                symbol1++;
                formatDivHTML+="</div><div class=\"clear\"></div></div>";
                attributesName[prop]=formatDivHTML;//并将分割结果（数组）赋值回相应的key
                strlll+=formatDivHTML;
            }
            that.orders.spu.attributesName=strlll;
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
        createRadioId:function(i){
            var date=(new Date()).getTime()+1;
            return date;
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
        }
    }
})