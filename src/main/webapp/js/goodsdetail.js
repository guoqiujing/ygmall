$(function(){
	winWidth = window.innerWidth;
	smallBoxHright=winWidth*0.32;
	smallimgWidth=winWidth*0.15;
//	$(".small-box").css("height",smallBoxHright+"px");
//	$(".smallimg").css("height",smallimgWidth+"px");
	$("input[type='radio']").click(function() {
		$("input[type='radio'][name='" + $(this).attr('name') + "']").parent().removeClass("checked")
		$(this).parent().addClass("checked");
	});
})
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