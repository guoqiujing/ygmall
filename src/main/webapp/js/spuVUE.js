// 选中品牌后
function clickBrandRadio(target){
    $("input[type='radio'][name='brandId']").parent().removeClass("checked");
    $(target).parent().addClass("checked");
}
// 填写货品详细参数时自动生成下一行
function createAttrRow(target){
    if($(target).attr("alt")=="0") {
        var newRow = "<div class='form-group'>" +
            "<div class='col-sm-3'><input class='form-control attrName' name='attrName' placeholder='参数名称：'></div>" +
            "<div class='col-sm-9'><input class='form-control attrValue' name='attrValue' placeholder='参数值' alt='0' onfocus='createAttrRow(this)' onblur='ifAttrRow(this)'> </div>" +
            "</div>";
        $(target).parents(".form-horizontal").append(newRow);
    }
}
// 若上一行并没有实际的输入，则取消对下一行的生成
function ifAttrRow(target){
   if($(target).val().trim()==""||$(target).val()==null)
       $(target).parents(".form-group").next().remove();
   else
       $(target).attr("alt","1");
}
// 折叠子类所在面板
function collapse(target){
    $(target).next().slideToggle(150);
}
// 选中商品分类时的特效
function clickCategoriesRadio(target){
    $(target).parents(".spuCategories").find(".glyphicon-ok").css("display","none");
    $("input[type='radio'][name='categoryId']").parent().removeClass("checked");
    $(target).parent().addClass("checked");
    $(target).next().css("display","inline-block");
    $(".spuCategories").css("border","0px solid");
    $(".spuCategories>.panel-heading").css("color","#676a6d");
}
function showAllBrand(target){
    if($(target).html()=="展开所有"){
        $("#brandVUE").css("height","auto");
        $(target).html("收起");
    }
    else{
        $("#brandVUE").css("height","100px");
        $(target).html("收起");
    }
}

function submitAnd(mark){
    if(mark==0)
        mark="/page/admin/putOnGoods.html";
    else
        mark="/page/admin/putOnSpu.html";
    var name=$(".inputname").val();
    var subtitle=$(".inputsubtitle").val();
    var brandId=$("input[name='brandId']:checked").val();
    var categoryId=$("input[name='categoryId']:checked").val();
    var attrNames=$(".attrName");
    var attrValues=$(".attrValue");
    var attrNamesArray=[];
    var attrValuesArray=[];
    for(var i=0;i<attrNames.size();i++) {
        if(attrNames[i].value.trim()!=""&&attrValues[i].value.trim()!=""){
            attrNamesArray.push(attrNames[i].value.trim());
            attrValuesArray.push(attrValues[i].value.trim());
        }
    }
    $('#spu_form').data('bootstrapValidator').validate();
    if(!$('#spu_form').data('bootstrapValidator').isValid()){
        return ;
    }
    else {
        if (categoryId != null) {
            $.ajax({
                url: '/spu/createSPU',
                type: 'post',
                traditional: true,
                data: {
                    name: name,
                    categoryId: categoryId,
                    brandId: brandId,
                    attrNamesArray: attrNamesArray,
                    attrValuesArray: attrValuesArray,
                    subtitle: subtitle
                },
                dataType: 'json',
                success: function (data) {
                    if (data.code == 0) {
                        alert("提交成功");
                        window.location.href=mark;
                    }
                },
                error: function (data) {
                    alert("提交失败！");
                }
            });
            $('#spu_form').submit();
        }
        else {
            $(".spuCategories").css("border", "1px solid #a94442");
            $(".spuCategories>.panel-heading").css("color", "#a94442");
        }
    }
}

// 校验器
$(function () {
    $('#spu_form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded:[":disabled"],
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '请输入SPU名称'
                    }
                }
            },
            subtitle: {
                validators: {
                    stringLength: {//检测长度
                        min: 0,
                        max: 50,
                        message: '长度必须在0-50之间'
                    }
                }
            },
            brandId: {
                validators: {
                    notEmpty: {
                        message: '请选择货品品牌'
                    }
                }
            },
            attrValue: {
                validators: {
                    notEmpty: {
                        message: '请输入货品参数'
                    }
                }
            }
        }
    })
})


var page = new Vue({
    // el为vue作用范围
    el: '#brandVUE',
    data: {
        allBrand: [],
    },
    //在vue对象创建完成时自动触发的事件
    created: function (data) {
        var that = this;
        $.ajax({
            url:'/brand/findAll',
            type: 'get',
            dataType:'json',
            success:function(data){
                if(data.code==0){
                    that.allBrand=data.data;
                }
            },
            error:function(data){
                alert("系统错误，请联系管理员！");
            }
        })
    }
})

