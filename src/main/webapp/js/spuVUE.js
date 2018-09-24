function clickBrandRadio(target){
    $("input[type='radio'][name='brandRadio']").parent().removeClass("checked");
    $(target).parent().addClass("checked");
}
// 填写货品详细参数时自动生成下一行
function createAttrRow(target){
    if($(target).attr("alt")=="0") {
        var newRow = "<div class='form-group'>" +
            "<div class='col-sm-3'><input class='form-control' name='attrName' placeholder='参数名称：'></div>" +
            "<div class='col-sm-9'><input class='form-control' name='attrValue' placeholder='参数值' alt='0' onfocus='createAttrRow(this)' onblur='ifAttrRow(this)'> </div>" +
            "</div>";
        $(target).parents(".form-horizontal").append(newRow);
    }
}
// 若上一行并没有实际的输入，则取消对下一行的生成
function ifAttrRow(target){
    console.log($(target).val());
   if($(target).val().trim()==""||$(target).val()==null)
       $(target).parents(".form-group").next().remove();
   else
       $(target).attr("alt","1");
}


function collapse(target){
    $(target).next().slideToggle(150);
}

function clickCategoriesRadio(target){
    $(target).parents(".spuCategories").find(".glyphicon-ok").css("display","none");
    $("input[type='radio'][name='CategoriesRadio']").parent().removeClass("checked");
    $(target).parent().addClass("checked");
    $(target).next().css("display","inline-block");
}



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