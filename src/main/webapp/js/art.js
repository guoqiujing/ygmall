/**
 * Created by CC on 2018/9/18.
 */
$(function(){
    $('.new_books .page_list a').click(function(){
        var index = $(this).index();
        $('.new_books .new_goods_list .big_box').hide().eq(index).show();
        $('.new_books .page_list a').attr('class','page_other').eq(index).attr('class','page_now');
    });

    //宸︿晶鑿滃崟 begin//
    windowLift();
    $(".main-lift a").click(function(){
        var i = $(this).index();
        $('.main-cont').each(function(){
            var _this =$(this);
            if( _this.data('id') == i){
                var scrollTop = $(this).offset().top;
                $('html,body').animate({scrollTop:scrollTop-10},300);
            }
        })
    });
    //宸︿晶鑿滃崟 end//

});

//宸︿晶鑿滃崟begin//
function windowLift(){
    var _ismobile = false;
    var windowTop = $(window).scrollTop();
    var windowBottom = windowTop + $(window).height();
    var showNum = !_ismobile ? 4 :16;
    if( windowTop > $('.main-cont').eq(0).offset().top){
        $('.main-lift').addClass("show");
    }else{
        $(".main-lift").removeClass("show");
    }

    $('.main-cont').each(function(){

        var pageQ1 = $(this).offset().top + $(this).height() / showNum;
        var pageQ3 = $(this).offset().top  + $(this).height() / 1;

        if( ( pageQ1 <= windowBottom ) && ( pageQ3 >= windowTop ) ){
            $(this).addClass('cur');
            var i = $(this).data('id');
            $('.lift-nav').eq(i).addClass('cur');

        }else {
            var i = $(this).index();
            $(this).removeClass('cur');
            var i = $(this).data('id');
            $('.lift-nav').eq(i).removeClass('cur');
        }
    });
}

$(window).scroll(function () {
    windowLift();
});
//宸︿晶鑿滃崟end//
