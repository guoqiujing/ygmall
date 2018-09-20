<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="../../css/bootstrap.css" />
		<link rel="stylesheet" href="../../css/page-head.css">
		<link rel="stylesheet" href="../../css/goodsdetail.css" />
		<script type="text/javascript" src="../../js/vue.js"></script>
		<script src="../../js/jquery.min.js"></script>
		<script src="../../js/bootstrap.js"></script>
		<script src="../../js/goodsdetail.js"></script>
		<title>JSP Page</title>
		<style>
			[v-cloak]{
				display:none !important;
			}
		</style>
	</head>

	<body>
		<div class="container-fluid head" style="border-bottom: 1px solid #DEDEDE; background-color: #F2F2F2;color: #484848;">
			<div class="container">
				<div class="row" style="font-size: 12px;padding:5px;">
					<div class="col-md-2">欢迎来到艺格艺术生用品商城</div>
					<div class="col-md-1"><a href="login.html">请登录</a></div>
					<div class="col-md-1"><a href="register.html">免费注册</a></div>
					<!--<div class="col-md-2" style="float: right;">官方微信商城</div>-->
					<div class="col-md-1" style="float: right;"><a href="page/user/user.html">我的艺格</a></div>
					<div class="col-md-1" style="float: right;">我的收藏</div>
					<div class="col-md-1" style="float: right;">订单查询</div>
				</div>
			</div>
		</div>

		<div class="head" style="margin: 20px 0 20px 0;">
			<div class="head-center" style="justify-content: space-between;">
				<div style="margin-right: 0;">
					<a href="index.html"><img src="../../img/yigeshangchenglogo.png"></a>
				</div>

				<div class="divcenter">
					<div class="input-group" style="max-width:400px;min-width:100px;">
						<input placeholder="水粉颜料" type="text" class="form-control" style="border: 2px solid #987E46;">
						<span class="input-group-btn">
									<button class="btn btn-default" type="button" style="color: #fff;background-color: #987E46;border: 1px solid #987E46;">搜索</button>
								</span>
					</div>
				</div>

				<a href="#" class="divcenter" style="font-size:15px;color: #666;border: 1px solid #E6E6E6;width: 132px;height: 38px;background-color: #F2F2F2;">
					<img src="../../img/carts.png" style="margin-right: 10px;" /> 购物车
				</a>

			</div>
		</div>

		<div class="head" style="background-color: #181818;">
			<div class="head-center" style="padding: 0;background-color:#181818 ;height: auto;">

				<ul class="nav navbar-nav">
					<li class="active" style="background-color: #484848;">
						<a href="#" style="padding: 15px 50px 15px 50px;">热门商品分类</a>
					</li>
					<li>
						<a href="index.html" style="padding: 15px 30px ;">首页</a>
					</li>
					<li>
						<a href="#" style="padding: 15px 30px ;">美术生专区</a>
					</li>
					<li>
						<a href="#" style="padding: 15px 30px ;">音乐生专区</a>
					</li>
					<li>
						<a href="#" style="padding: 15px 30px ;">舞蹈生专区</a>
					</li>
				</ul>

			</div>
		</div>
		<!--商品详情-->
		<div id="detail-page" class="app" v-cloak style="width:80%;margin: 0 10%;">
			<!--<div id="detail-page" style="width:80%;position: absolute;left:10%;">-->
		<!--导航-->
			<div class="" style="padding:1em">
				<a href="#"><small>首页</small></a>
				>
				<a v-bind:href="categories.grandId+'.jsp'" ><small class="">{{categories.grandName}}</small></a>
				>
				<a v-bind:href="categories.parentId"><small class="">{{categories.parentName}}</small></a>
				>
				<a v-bind:href="categories.id"><small class="">{{categories.name}}</small></a>
				><small>{{orders.spu.name}}</small>
			</div>
			<!--商品细节图-->
			<div style="width:40%;float:left;">
				<!--大图-->
				<div class="small-box" id="smallBox" onmouseenter="mouseEnterSmallBox()" onmousemove="mouseMoveSmallBox()" onmouseleave="mouseLeaveSmallBox()">
					<img src="../../img/detail1.jpg" style="width:100%;height:auto;"/>
					<div class="tool" id="tool" style="width:50%;height:50%"></div>
				</div>
				<!--小图-->
				<div class="smallimg" style="width:19%;float:left;padding-top:0.3em;margin-right:1%;" onmousemove="changeimg(this)">
					<img src="../../img/detail1.jpg" style="width:100%;" />
				</div>
				<div class="smallimg" style="width:19%;float:left;padding-top:0.3em;margin-right:1%;" onmousemove="changeimg(this)">
					<img src="../../img/detail2.jpg" style="width:100%;" />
				</div>
				<div class="smallimg" style="width:19%;float:left;padding-top:0.3em;margin-right:1%;" onmousemove="changeimg(this)">
					<img src="../../img/detail3.jpg" style="width:100%;" />
				</div>
				<div class="smallimg" style="width:19%;float:left;padding-top:0.3em;margin-right:1%;" onmousemove="changeimg(this)">
					<img src="../../img/detail4.jpg" style="width:100%;" />
				</div>
				<div class="smallimg" style="width:19%;float:left;padding-top:0.3em;margin-right:1%;" onmousemove="changeimg(this)">
					<img src="../../img/detail5.jpg" style="width:100%;" />
				</div>
				<div class="clear"></div> 
			</div>
			<!--商品基本信息：名称、价格、款式等-->
			<div style="width:58%;float:right;">
				<!--放大的商品细节图-->
				<div class="big-box" id="bigBox">
					<img src="../../img/detail1.jpg" id="bigImg" />
				</div>
				<h4><strong>{{orders.name}}</strong></h4>
				<span style="color: #3399ff;">{{orders.spu.subtitle}}</span>
				<div style="background: url(../../img/img1.png);padding:1em;margin-top:1em;margin-bottom:1em;">
					<div style="margin-bottom: 5px">市场价：<span><s>¥{{orders.marketPrice}}</s></span></div>
					<div>艺格价：<span style="font-size:20px;color: #FF4800;">¥{{orders.price}}</span></div>
				</div>
				<div>
		            <div style="color:red;margin-bottom: 1em;font-size: 16px;">
						<div v-if="orders.status==1">
							该商品正在进行预售哦！
						</div>
						<div v-if="orders.status==2">
							抱歉，该商品暂时缺货！
						</div>
						<div v-if="orders.status==3">
							抱歉，该商品已下架！
						</div>
						<div v-if="orders.status==4">
							该商品正在进行促销活动哦！
						</div>
					</div>
					<!--成交、评论、好评率-->
					<div class="goods-sales" style="margin:0.5em 0">
						<div class="clearfix"></div>
						<li>成交 <em class="fnum" id="sold_num">{{orders.saleCount}}</em></li>
						<li>评论 <em class="fnum" id="comment_num">{{orders.spu.commentCount}}</em></li>
						<li>库存 <em class="fnum">{{orders.inventory}}</em></li>
						<em class="fnum"><em><div class="clearfix"></div></em></em>
					</div>

					<span v-html="orders.spu.attributesName"></span>
			        <div id="shoppingnum" class="clearfix shoppingnum">
			        	<div style="float:left;width:15%;padding:0em 1em;"><label>数量</label></div>
						<span class="button" id="goods_buy">
							<span class="pre">-</span>
							<span class="color_num">
								<input type="text" class="color_input isnumber" size="4" value="1" name="color_input" id="alork_num">
					            <input class="spec_id 01713" type="hidden" value="01713" name="spec_id">
					            <input class="color_input" type="hidden" value="1" name="number">
					            <input class="goods_stock" type="hidden" name="goods_stock" id="s_goods_stock" value="672">
					            <input class="goods_id" type="hidden" name="goods_id" value="1713">                       
					        </span>
							<span class="next">+</span>
						</span>
						<div class="clear"></div> 
					</div>
					<div id="BtnArea" class="cartboxss">
						<a class="btn-buy-now" title="立即购买" href="#none" onclick="alork_addcart(1713,1)">立即购买</a>
						<a id="btn-addCart" class="bnt-add-cart" href="#none" onclick="alork_addcart(1713,0)" title="加入购物车"><span>加入购物车</span></a>
						<div class="blank20 clearfix"></div>
					</div>
			        
				</div>
		    	<div class="clear"></div> 
		    </div>
			
			<!--商品详情、规格包装、商品评价、服务详情-->
			<div style="width:100%;float:left;margin-top:6em;">
	        	<div style="float:left;width:20%;height:300px;">
	        		<div class="blockTitle">标题</div>
	        		<h1>内容待定</h1>
	        	</div>
	        	<!--导航栏-->
	        	<div class="goods-nav" style="float:right;width:78%;">
	        		<ul>
						<li><a href="#goods-details" class="active" onclick="detailForward(this)">商品详情</a></li>
						<li><a href="#goods-format" onclick="detailForward(this)">规格包装</a></li>
						<li><a href="#goods-comment" onclick="detailForward(this)">商品评价</a></li>
						<li><a href="#service-detail"  onclick="detailForward(this)">服务详情</a></li>
						<li style="float:right;background-color: #987e46;"><a href="#" style="color:#FFFFFF">加入购物车</a></li>
						<div class="clear"></div> 
	        		</ul>
				</div>
				<!--商品详情-->
				<a name="goods-details" style="display:none">商品详情</a>
				<div class="goods-details" style="float:right;width:78%;">
					<!--商品参数摘要-->
					<div class="goods-params">
						<div style="margin-bottom: 3em;">品牌名称：
							<a v-bind:href="orders.spu.brandId" class="brand-name">{{orders.spu.params["品牌"]}}</a>
						</div>
						<ul class="params-list">
							<template v-for="(val,key,index) in orders.spu.params" v-if="index<6">
								<li v-bind:title="val">{{key}}：{{val}}</li>
							</template>
						</ul>
						<div style="margin-bottom: 1em;text-align:right;margin-right: 50px" >
							<a  href="#goods-format" onclick="detailForward(this)">查看更多参数>></a>
						</div>
					</div>
					<hr>
					<!--商品图片介绍-->
					<div class="detail-img" style="text-align: center;">
						<img src="../../img/dt1.jpg"/>
						<img src="../../img/dt2.jpg"/>
						<img src="../../img/dt3.jpg"/>
						<img src="../../img/dt4.jpg"/>
						<img src="../../img/dt5.jpg"/>
						<img src="../../img/dt6.jpg"/>
						<img src="../../img/dt7.jpg"/>
						<img src="../../img/dt8.jpg"/>
						<img src="../../img/dt9.jpg"/>
					</div>
				</div> 	
			
				<!--规格包装-->	
				<a name="goods-format" style="display:none">规格包装</a>
				<div class="goods-format" style="float:right;width:78%;">
					<div class="blockTitle"><a>规格包装</a></div>
                    <div class="package-list">
						<dl>
							<template v-for="(val,key) in orders.spu.params" >
								<dt>{{key}}</dt>
								<dd>{{val}}</dd>
							</template>
						</dl>
					</div>
               </div>
				<!--商品评价-->
				<a name="goods-comment" style="display:none" >商品评价</a>
				<div class="goods-comment" style="float:right;width:78%;height:500px;">
					<div class="blockTitle"><a>商品评价</a></div>
					<div>
						假装有留言
					</div>
				</div>
                <!--服务详情-->
                <a name="service-detail" style="display:none">服务详情</a>				
				<div class="service-detail" style="float:right;width:78%;">
					<div class="blockTitle"><a>服务详情</a></div>
                    <div class="service-list">
                    		<dl>                    	
                    			<dt>
                    	            <i style="background: url(../../img/cjfw.png);background-size:100%;"></i>
                    	            <strong>厂家服务</strong>
                    	        </dt>
                    			<dd>
                    				本产品全国联保，享受三包服务，质保期为：一年质保<br> 如因质量问题或故障，凭厂商维修中心或特约维修点的质量检测证明，享受7日内退货，15日内换货，15日以上在质保期内享受免费保修等三包服务！
                    				<br>
                    				<a target="_blank" href="http://www.asus.com.cn">http://www.asus.com.cn</a><br> 售后服务电话：400-600-6655 
                    			</dd>
                    			<dt>
                    	            <i style="background: url(../../img/zpbz.png);background-size:100%;"></i>
                    	            <strong>正品行货</strong>
                    	        </dt>
                    	        <dd>艺格商城向您保证所售商品均为正品行货，均可开具机打发票或电子发票</dd>
                    			<dt>
                    				<i style="background: url(../../img/qglb.png);background-size:100%;"></i>
                    				<strong>全国联保</strong>
                    			</dt>
                    			<dd>
                    				凭质保证书及艺格商城发票，可享受全国联保服务，与您亲临商场选购的商品享受相同的质量保证。艺格商城还为您提供具有竞争力的商品价格和
                    				<a href="//help.jd.com/help/question-892.html" target="_blank">运费政策</a>，请您放心购买！
                    				<br><br>注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！
                    			</dd>
                    			<dt>
                    			<i style="background: url(../../img/wyth.png);background-size:100%;"></i>
                    			<strong>无忧退货</strong>
                    			</dt>
                    			<dd class="no-worries-text">
                    				客户购买艺格自营商品7日内（含7日，自客户收到商品之日起计算），在保证商品完好的前提下，可无理由退货。（部分商品除外，详情请见各商品细则）
                    			</dd>
                    		</dl>

					</div>
	                <div class="service-list2">
	                	<strong>权利声明：</strong>
						艺格上的所有商品信息、客户评价等内容，是艺格重要的经营资源，未经许可，禁止非法转载使用。<br>
						注：本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。
					</div>
	                <div class="service-list2">
	                	<strong>价格说明：</strong>
						艺格价：艺格价为商品的销售价，是您最终决定是否购买商品的依据。<br>
						划线价：商品展示的划横线价格为参考价，并非原价，该价格可能是品牌专柜标价、商品吊牌价或由品牌供应商提供的正品零售价（如厂商指导价、建议零售价等）或该商品在京东平台上曾经展示过的销售价；由于地区、时间的差异性和市场行情波动，品牌专柜标价、商品吊牌价等可能会与您购物时展示的不一致，该价格仅供您参考。
						<br>
						折扣：如无特殊说明，折扣指销售商在原价、或划线价（如品牌专柜标价、商品吊牌价、厂商指导价、厂商建议零售价）等某一价格基础上计算出的优惠比例或优惠金额；如有疑问，您可在购买前联系销售商进行咨询。
						<br>
						异常问题：商品促销信息以商品详情页“促销”栏中的信息为准；商品的具体售价以订单结算页价格为准；如您发现活动商品售价或促销信息有异常，建议购买前先联系销售商咨询。
					</div>
                </div>
				<div class="clear"></div> 
			</div>
				
			<div style="width:100%;float:left;margin:30px 0" id="testttttt">
				<div class="blockTitle"><strong style="color:#666">猜你喜欢</strong></div>
				<div style="border: solid 1px #ddd;height:200px"></div>
				<div class="clear"></div> 
			</div>	
			<div class="clear"></div> 
		</div>
			<div class="footer">
				<div style="height:500px;color:#FFF">
				</div>
			</div>
		<div class="clear"></div>
		<script src="../../js/goodsdetailVUE.js"></script>
	</body>
</html>