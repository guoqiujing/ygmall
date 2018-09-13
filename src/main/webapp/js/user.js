$(document).ready(function(){
	 /*全站公用tab*/
	 $(".tabline h2").click(function(){
		tabindex = $(this).index();
		$(this).removeClass("h2bg").siblings().addClass("h2bg");
//		$(".tab-box blockquote").eq(tabindex).show().siblings().hide();
	 })
})

var app = new Vue({
  el: '#app2',
  data: {
  	test1:'233',
  	object: {
  
   },
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
      }
    ],
    token:"abc"
  },
  methods:{
  		
  }
})