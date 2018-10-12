
//创建Vue实例
var app=new Vue({
    el: '#app',
    data:{
        payUser:0,
        payUserScale:0,
        noPayUser:0,
        noPayUserScale:0,
        male:0,
        maleScale:0,
        female:0,
        femaleScale:0,
        secret:0,
        secretScale:0,
        newUser:0,
        firBuy:0,
        firBuyScale:0,
        secBuy:0,
        secBuyScale:0
    },
    //创建vue实例之后的事件
    created: function (){
    },

    mounted:function () {
        this.findNewUser();
        this.findSexCount();
        this.findUserAndPay();
        this.findFirAndSec();
        this.chart1create();
    },
    methods: {
       //表格1生成
        chart1create:function () {
            var chart = Highcharts.chart('chart1',{
                chart: {
                    type: 'column'
                },
                title: {
                    style:{
                      color: 'rgb(103, 106, 109)'
                    },
                    text: '最近一周销售额'
                },
                credits:{
                    enabled: false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        this.getDay(-7),this.getDay(-6),this.getDay(-5),this.getDay(-4),this.getDay(-3),this.getDay(-2),this.getDay(-1)
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '销售额 (元)'
                    }
                },
                tooltip: {
                    // head + 每个 point + footer 拼接成完整的 table
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} 元</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        borderWidth: 0
                    }
                },
                series: [{
                    name: '销售额',
                    data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6],
                    showInLegend: false // 设置为 false 即为不显示在图例中
                }]
            });
        },

        //饼图1生成
        pie1create:function () {
            Highcharts.chart('pie1', {
                credits:{
                    enabled: false // 禁用版权信息
                },
                legend: {
                    //backgroundColor: '#FFFFFF',
                    layout: 'vertical',
                    floating: true,
                    y:50,
                    align: 'right',
                    verticalAlign: 'top',
                    itemStyle:{
                        fontWeight: 300,
                        color: 'rgb(103, 106, 109)',
                        fontSize:16
                    }
                },
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: null
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br/>'+
                    '人数: <b>{point.x}</b><br/>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: false
                        },
                        showInLegend: true,
                        point: {                  // 每个扇区是数据点对象，所以事件应该写在 point 下面
                            events: {
                                // 鼠标滑过是，突出当前扇区
                                mouseOver: function() {
                                    this.slice();
                                },
                                // 鼠标移出时，收回突出显示
                                mouseOut: function() {
                                    this.slice();
                                },
                                // 默认是点击突出，这里屏蔽掉
                                click: function() {
                                    return false;
                                }
                            }
                        }
                    }
                },
                colors: ['#95CEFF', '#E8AAE5', '#5C5C61'],
                series: [{
                    innerSize: '50%',
                    name: '比例',
                    colorByPoint: true,
                    data: [{
                        name: '男',
                        x:this.male,
                        y: this.maleScale
                    }, {
                        name: '女',
                        x:this.female,
                        y: this.femaleScale
                    }, {
                        name: '保密',
                        x:this.secret,
                        y: this.secretScale
                    }
                    ]
                }]
            });
        },

        //饼图2生成
        pie2create:function () {
            Highcharts.chart('pie2', {
                credits:{
                    enabled: false // 禁用版权信息
                },
                legend: {
                    //backgroundColor: '#FFFFFF',
                    layout: 'vertical',
                    floating: true,
                    y:50,
                    align: 'right',
                    verticalAlign: 'top',
                    itemStyle:{
                        fontWeight: 300,
                        color: 'rgb(103, 106, 109)',
                        fontSize:16
                    }
                },
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: null
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br/>'+
                    '人数: <b>{point.x}</b><br/>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: false
                        },
                        showInLegend: true,
                        point: {                  // 每个扇区是数据点对象，所以事件应该写在 point 下面
                            events: {
                                // 鼠标滑过是，突出当前扇区
                                mouseOver: function() {
                                    this.slice();
                                },
                                // 鼠标移出时，收回突出显示
                                mouseOut: function() {
                                    this.slice();
                                },
                                // 默认是点击突出，这里屏蔽掉
                                click: function() {
                                    return false;
                                }
                            }
                        }
                    }
                },
                colors: ['#87e591', '#FAC49B'],
                series: [{
                    innerSize: '50%',
                    name: '比例',
                    colorByPoint: true,
                    data: [{
                        name: '付费用户',
                        x: this.payUser,
                        y: this.payUserScale
                    }, {
                        name: '非付费用户',
                        x: this.noPayUser,
                        y: this.noPayUserScale
                    }]
                }]
            });
        },

        //饼图3生成
        pie3create:function () {
            Highcharts.chart('pie3', {
                credits:{
                    enabled: false // 禁用版权信息
                },
                legend: {
                    //backgroundColor: '#FFFFFF',
                    layout: 'vertical',
                    floating: true,
                    y:10,
                    align: 'right',
                    verticalAlign: 'top',
                    itemStyle:{
                        fontWeight: 300,
                        color: 'rgb(103, 106, 109)',
                        fontSize:14
                    }
                },
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: null
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br/>'+
                    '人数: <b>{point.x}</b><br/>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: false
                        },
                        showInLegend: true,
                        point: {                  // 每个扇区是数据点对象，所以事件应该写在 point 下面
                            events: {
                                // 鼠标滑过是，突出当前扇区
                                mouseOver: function() {
                                    this.slice();
                                },
                                // 鼠标移出时，收回突出显示
                                mouseOut: function() {
                                    this.slice();
                                },
                                // 默认是点击突出，这里屏蔽掉
                                click: function() {
                                    return false;
                                }
                            }
                        }
                    }
                },
                colors: ['#92dee5', '#6185fa'],
                series: [{
                    innerSize: '50%',
                    name: '比例',
                    colorByPoint: true,
                    data: [{
                        name: '单次购买率',
                        x: this.firBuy,
                        y: this.firBuyScale
                    }, {
                        name: '多次购买率',
                        x: this.secBuy,
                        y: this.secBuyScale
                    }]
                }]
            });
        },

        //获得某一天的日期
        getDay:function(day){
            var today = new Date();
            var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;
            today.setTime(targetday_milliseconds); //注意，这行是关键代码
            var tYear = today.getFullYear();
            var tMonth = today.getMonth();
            var tDate = today.getDate();
            tMonth = this.doHandleMonth(tMonth + 1);
            tDate = this.doHandleMonth(tDate);
            return tYear+"-"+tMonth+"-"+tDate;
        },

        //操作月份
        doHandleMonth:function(month){
            var m = month;
            if(month.toString().length == 1){
                m = "0" + month;
            }         return m;
        },

        //请求最近一周的用户数
        findNewUser:function () {
            var that=this;
            $.ajax({
                type: "POST",
                url: "/account/findNewUser",
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code===0){
                        //console.log("成功");
                        //console.log(msg.data);
                        that.newUser=msg.data;
                    }
                    else {
                        layer.msg('查找失败', {time: 2000});
                    }
                },
                error: function () {
                    layer.msg('发生错误', {time: 2000});
                }
            });
        },

        //请求查找用户数和付费用户数
        findUserAndPay:function () {
            var that=this;
            $.ajax({
                type: "POST",
                url: "/account/findUserAndPay",
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code===0){
                        //console.log("成功");
                        //console.log(msg.data);
                        that.payUser=msg.data.pay_user;
                        that.noPayUser=msg.data.user_count-msg.data.pay_user;
                        that.payUserScale=(msg.data.pay_user/msg.data.user_count).toFixed(3)*100;
                        that.noPayUserScale=100-(msg.data.pay_user/msg.data.user_count).toFixed(3)*100;
                        that.pie2create();
                    }
                    else {
                        layer.msg('查找失败', {time: 2000});
                    }
                },
                error: function () {
                    layer.msg('发生错误', {time: 2000});
                }
            });
        },

        //请求查找不同性别的用户数
        findSexCount:function () {
            var that=this;
            $.ajax({
                type: "POST",
                url: "/customer/findSexCount",
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code===0){
                        //console.log("成功");
                        //console.log(msg.data);
                        var sum=msg.data.male+msg.data.female+msg.data.secret;
                        that.male=msg.data.male;
                        that.female=msg.data.female;
                        that.secret=msg.data.secret;
                        that.maleScale=(msg.data.male/sum).toFixed(3)*100;
                        that.femaleScale=(msg.data.female/sum).toFixed(3)*100;
                        that.secretScale=(msg.data.secret/sum).toFixed(3)*100;
                        that.pie1create();
                    }
                    else {
                        layer.msg('查找失败', {time: 2000});
                    }
                },
                error: function () {
                    layer.msg('发生错误', {time: 2000});
                }
            });
        },

        //请求查找单次购买用户和多次购买用户
        findFirAndSec:function () {
            var that=this;
            $.ajax({
                type: "POST",
                url: "/account/findFirAndSec",
                dataType: "json",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if(msg.code===0){
                        console.log("成功");
                        console.log(msg.data);
                        var sum=msg.data.fir_buy+msg.data.sec_buy;
                        that.firBuy=msg.data.fir_buy-msg.data.sec_buy;
                        that.secBuy=msg.data.sec_buy;
                        that.firBuyScale=((msg.data.fir_buy-msg.data.sec_buy)/sum).toFixed(3)*100;
                        that.secBuyScale=(msg.data.sec_buy/sum).toFixed(3)*100;
                        that.pie3create();
                    }
                    else {
                        layer.msg('查找失败', {time: 2000});
                    }
                },
                error: function () {
                    layer.msg('发生错误', {time: 2000});
                }
            });
        }

    }
});

$(document).ready(function(){

});