$(document).ready(function(){
	 /*全站公用tab*/
	 $(".tabline h2").click(function(){
		tabindex = $(this).index();
		$(this).removeClass("h2bg").siblings().addClass("h2bg");
//		$(".tab-box blockquote").eq(tabindex).show().siblings().hide();
	 })
})