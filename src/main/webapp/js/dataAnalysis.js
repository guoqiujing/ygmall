
//创建Vue实例
var app=new Vue({
    el: '#app',
    data:{
        userId:'123',//从session获取的用户id
        user:{}
    },
    //创建vue实例之后的事件
    created: function (){
    },

    mounted:function () {
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
                    text: '2017年每月销售额'
                },
                credits:{
                    enabled: false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'
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
                    data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
                }]
            });
        },

        //注销
        logout:function () {
            $.ajax({
                type: "POST",
                url: "/app/logout",
                dataType: "json",
                success: function(data){
                    if(data.code===0){
                        console.log("注销成功")
                        window.location.href="/page/user/login.html";
                    }
                }
            });
        }


    }
});

$(document).ready(function(){
    //chartcrea();
});