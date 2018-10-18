<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="robots" content="noindex,nofollow" />
    <title>购物流程_美术生艺考用品专营电商</title>
    <link rel="shortcut icon" href="favicon.ico" />
    <link href="http://www.nahanms.com/themes/kf666/style.css" rel="stylesheet" type="text/css" />
    <link href="http://www.nahanms.com/themes/kf666/wangyin.css" rel="stylesheet" type="text/css" />

</head>
<body>

<div class="flow_top">
    <div class="flow_wrap">
        <div class="f_l flow_logo">
            <h1><a href="http://www.nahanms.com/index.html">呐喊美术商城官方网站</a></h1>
        </div>
        <div class="flow_member f_r">
            <ul class="member_info f_l" id="ECS_MEMBERZONE"><script src="http://www.nahanms.com/gogo.php?id=1" type="text/javascript"></script></ul>
            <div class="brocodes brocodes_top f_r" >
                <dt class="ico_wx">官方微信商城</dt>
                <dd class="brocodes_detail"><img src="http://www.nahanms.com/themes/kf666/images/brode.jpg"></dd>
            </div>
            <div class="member_info f_r" >
            </div>
            <ul class="member_info f_r">
                <li><a rel="nofollow" href="http://www.nahanms.com/user.php?act=order_list">订单查询</a></li>
                <li><a rel="nofollow" href="http://www.nahanms.com/user.php">我的呐喊</a></li>
                <li class="fav"><a rel="nofollow" href="http://www.nahanms.com/user.php?act=collection_list">我的收藏</a></li>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div class="blank30"></div>
<div class="flow_block">
    <div class="cartnew-title">
        <h2 class="step2">我的购物车</h2>
        <ul>
            <li><span>查看购物车</span></li>
            <li><span class="act">填写订单</span></li>
            <li class="end"><span>付款，完成购买</span></li>
        </ul>
    </div>
    <div class="flow_title">
        请填写并核对以下信息
    </div>

    <div class="flowBoxbd">
        <div class="flowBox">
            <div class="title">填写收货信息：</div>
            <div class="consignee_form">
                <div class="shipping-address">

                </div>
            </div>
            <div class="clearfix blank"></div>
            <div class="address_wrap">
                <div class="address_add_new f_l">
                    <button type="button" class="tc-btn createAddr" name="address_id" id="new_address" value="0" onclick="show_ConsigneeDetail(0)"><strong class="f16">+</strong> 添加新地址</button>
                </div>
                <div class="address_manage f_r"><a href="user.php?act=address_list">管理收货地址</a></div>
            </div>
            <div id="showboxid" class="popup_block" >
                <table width="100%" border="0" cellpadding="6" cellspacing="0">
                    <tr>
                        <td class="f_666 f_12" width="15%" align="right">收货人姓名：</td>
                        <td class="f_999 f_12" align="left"><input name="consignee" id="newconsignee" type="text" class="inputbg" /><span class="f_star">*</span> 请填写您的真实姓名</td>
                    </tr>
                    <tr>
                        <td class="f_666 f_12" align="right">省市:</td>
                        <td colspan="3" align="left">
                            <select name="country" id="selCountries_1000" onchange="region.changed(this, 1, 'selProvinces_1000')" class="flow_inputbg"  style="display:none">
                                <option value="0">请选择国家</option>
                                <option value="1" selected="true">中国</option>
                            </select>
                            <select name="province" id="selProvinces_1000" onchange="region.changed(this, 2, 'selCities_1000')" class="flow_inputbg">
                                <option value="0">请选择省</option>
                                <option value=""></option>
                            </select>
                            <select name="city" id="selCities_1000"  onchange="region.changed(this, 3, 'selDistricts_1000')" class="flow_inputbg">
                                <option value="0">请选择市</option>
                                <option value=""></option>
                            </select>
                            <select name="district" id="selDistricts_1000"  class="flow_inputbg">
                                <option value="0">请选择区</option>
                                <option value=""></option>
                            </select><span class="f_star">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="f_666 f_12" align="right">街道地址：</td>
                        <td class="f_999 f_12" align="left"><input name="address"  id="newaddress" class="inputbg" size="40" /><span class="f_star">*</span>请输入收货的详细地址</td>
                    </tr>
                    <tr>
                        <td class="f_666 f_12" align="right">手机号码：</td>
                        <td class="f_999 f_12" align="left"><input name="mobile" id="newmobile" type="text" class="inputbg" /><span class="f_star">*</span>填写正确手机号便于接收发货和收货通知</td>
                    </tr>
                    <tr>
                        <td class="f_666 f_12" align="right">联系电话：</td>
                        <td class="f_999 f_12" align="left"><input name="tel" id="newtel" type="text" class="inputbg" />　如：020-88888888，固话和手机号至少填一项</td>
                    </tr>
                    <tr>
                        <td class="f_666 f_12" align="right">电子邮件：</td>
                        <td class="f_999 f_12" align="left"><input name="email" id="newemail" type="text" class="inputbg" />　用来接收订单提醒邮件，便于您及时了解订单状态</td>
                    </tr>
                    <tr>
                        <td class="f_666 f_12" align="right">邮政编码：</td>
                        <td class="f_999 f_12" align="left"><input name="zipcode" id="newzipcode" type="text" class="inputbg" />　请填写准确的邮编，以确保商品尽快送达</td>
                    </tr>
                    <tr>
                        <td class="f_666 f_12">设为默认地址：</td>
                        <td><input type="checkbox" name="default"  id="defaultid" value="1" /></td>
                    </tr>
                    <tr>
                        <td align="right">&nbsp;</td>
                        <td colspan="3">
                            <input name="user_ids" type="hidden" id="user_ids" value="0"  />
                            <input type="submit" name="submit" class="bnt_tongyong"  value="保存" onclick="update_Consignee()"/>
                            <input name="address_id" id="newaddress_id" type="hidden"  />
                        </td>
                    </tr>
                </table></div>
            <form action="flow.php" method="post" name="theForm" id="theForm" onsubmit="return checkOrderForm(this)">
                <script type="text/javascript">
                    var flow_no_payment = "您必须选定一个支付方式。";
                    var flow_no_shipping = "您必须选定一个配送方式。";
                    var flow_no_address = "请先保存收货地址。";
                </script>
                <input type="hidden" value="" name="display_info" />
                <input type="hidden" name="flow_consignee" id="flow_consignee" value="1" />
        </div>
        <div class="clearfix blank"></div>
        <div class="flowBox">
        </div>
        <div class="flowBox">
            <div class="title">选择支付方式：</div>
            <div class="pay_wrap">
                <dd >
                    <ul class="list-bank">
                        <li id="li1" ><input type="radio" id="pay1" name="payment" value="1"  isCod="0" onclick="selectPayment(this)" /><label for="pay1" class="icon alipay" onclick="sel(li1);"></label></li>
                        <li id="li5" ><input type="radio" id="pay5" name="payment" value="5"  isCod="0" onclick="selectPayment(this)" /><label for="pay5" class="icon CMB" onclick="sel(li5);"></label></li>
                    </ul>
                </dd>
            </div>
        </div>
    </div>
    <div class="blank clearfix"></div>
    <div class="flow_title_cartinfo"><span><a href="flow.php">返回修改购物车</a></span></div>
    <div class="flowcart" id="orderFrom">
        <table width="100%" align="center" border="0" cellpadding="5" cellspacing="0">
            <tr>
                <th>商品信息</th>
                <th>单价（元）</th>
                <th>数量</th>
                <th>小计（元）</th>
            </tr>
            <tr v-for="item in list" v-cloak>
                <td class="bbd">
                    <a href="#" target="_blank">
                        <img v-bind:src="item.picture" v-bind:alt="item.name" class="small_goodsimg"/>
                    </a>
                    <a href="#" target="_blank">{{item.name}}</a>
                    <br/><span class="f4"></span>
                </td>
                <td class="bbd" align=center>{{item.price}}</td>
                <td class="bbd" align=center>{{item.count}}</td>
                <td class="bbdr" align=center>¥3.00</td>
            </tr>
        </table>
    </div>

    <div class="flowBoxbd">
        <div class="flowBox">
            <div class="blank20 clearfix"></div>
            <div class="flowbuy">
                <div class="flowbeizhu f_l">
                    <div class="bztitle">添加订单备注信息：</div>
                    <textarea name="postscript" cols="50" rows="2" id="postscript" class="textarea_inputbg"></textarea>
                </div>
                <div class="flowprice f_r">
                    <div class="order_total">
                        <div id="ECS_ORDERTOTAL">
                            <div class="order_total_price">
                                <dl>
                                    <dt class="f_16">应付总额：</dt>
                                    <dd><span class="shop_total">¥3.00</span></dd>
                                </dl>
                            </div>
                        </div>  </div>
                    <div class="order_submit">
                        <input class="bnt_submit_order f_r" type="submit" value="马上提交订单" />
                        <input type="hidden" name="step" value="done" />
                    </div>
                </div>
                <div class="clearfix blank"></div>
            </div>
        </div>
    </div>
    </form>
</div>

<div class="clearfix blank50"></div>
<div class="footer">

</div>
<script type="text/javascript" src="../../../js/vue.js"></script>
<script type="text/javascript" src="../../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../../js/bootstrap.js"></script>
<script type="text/javascript" src="../../../layer/layer.js"></script>
<script type="text/javascript" src="js/create.js"></script>

</body>
</html>
