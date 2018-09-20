$(function(){
	// 设置商品细节图的大小
	// winWidth = window.innerWidth;
	// smallBoxHright=winWidth*0.32;
	// smallimgWidth=winWidth*0.15;
    // $("input[type='radio']").click(function() {
    //     $("input[type='radio'][name='" + $(this).attr('name') + "']").parent().removeClass("checked")
    //     $(this).parent().addClass("checked");
    // });
})
function clickRadio(target){
    $("input[type='radio'][name='" + $(target).attr('name') + "']").parent().removeClass("checked")
    $(target).parent().addClass("checked");
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








