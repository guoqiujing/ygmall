/**
 * Created by Simon on 2018/9/22.
 */
var page = new Vue({
    // el为vue作用范围
    el: '#app',
    data: {
        categoriesL1_2: [],
        categoriesL2_3:[],
        categoriesid_name:[],
    },
    //在vue对象创建完成时自动触发的事件
    created: function (data) {
        var that = this;
        $.ajax({
            url:'/categories/getAllCompleteCategories',
            type: 'post',
            dataType:'json',
            success:function(data){
                if(data.code==0){
                    that.categoriesL1_2=data.data.l12_map;
                    that.categoriesL2_3=data.data.l23_map;
                    that.categoriesid_name=data.data.name_map;
                    console.log("categoriesL1_2："+that.categoriesL1_2["1000"])
                    console.log("categoriesL2_3："+that.categoriesL2_3)
                    console.log("categoriesid_name："+that.categoriesid_name)
                }
            },
            error:function(data){
                alert("系统错误，请联系管理员！");
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
        }
    }
})