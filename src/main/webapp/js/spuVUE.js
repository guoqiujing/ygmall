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
    var name=$(".inputname").val();
    var subtitle=$(".inputsubtitle").val();
    var brandId=$("input[name='brandId']:checked").val();
    var categoryId=$("input[name='categoryId']:checked").val();
    var attrNames=$(".attrName");
    var attrValues=$(".attrValue");
    var attrNamesArray=[];
    var attrValuesArray=[];
    if(mark==0)
        mark="/page/admin/putOnGoods.html?categoryId="+categoryId+"&name="+name+"&spuId=";
    else
        mark="/page/admin/putOnSpu.html";
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
                        pageImg.uploadImg(data.data);
                        alert("提交成功");
                        window.location.href=mark+data.data;
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

var pageImg = new Vue({
    el: '#spudetail_form',
    data: {
        file: [],//要上传的图片文件
        img: '',//头像图片预览图
        imgArray: [],//头像图片预览图
        imgShow: true//是否显示预览图，默认显示
    },
    methods: {
        //改变选择的图片时
        getFile: function (event) {
            //如果图片文件不为空，给本地file赋值为接收的图片
            var putOnFiles=event.target.files;
            if (putOnFiles) {
                this.file=[];
                for(var i=0;i<putOnFiles.length;i++)
                    this.file.push(event.target.files[i]);
            }
            //图片文件为空，给本地file赋空值
            else {
                this.file = '';
            }
            //console.log(this.file);
        },
        //图片预览
        showImg: function (event) {
            $("#showImgDiv").html("");
            var file = event.target.files;
            var reader = new FileReader();
            var that = this;
            if (file && event.target.files) {
                for(var i=0;i<file.length;i++) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file[i]);
                    reader.onload = function (e) {
                        that.imgArray.push(e.target.result);
                    }
                }
                $("#fileLabel").removeClass("unchexkedFileInput");
                $("#fileLabel").addClass("btn");
                $("#fileLabel").css({"display":"block","width":"100px"});
                $("#fileLabel").html("重新上传");
            }
            else {
                this.imgShow = false;
            }
        },
        //上传图片
        uploadImg: function (spuId) {
            //阻止元素发生默认的行为
            // event.preventDefault();
            var that=this;
            var formData = new FormData();
            for(var i=0;i<this.file.length;i++) {
                formData.append("files", this.file[i]);
            }
            $.ajax({
                type: "POST",
                url: "/files/qCloud/goodsInfo",
                data: formData,
                contentType: false,// 不设置Content-Type请求头，因为formData自带请求格式
                processData: false,// 不处理发送的数据
                mimeType: "multipart/form-data",//文件后缀名
                dataType: "json",
                success: function (msg) {
                    console.log(msg);
                    if (msg.code == 0) {
                        console.log("上传至服务器成功");
                        console.log("图片地址：" + msg.data);
                        var as=that.saveImgToDB(spuId,msg.data+"");
                        if(as==1)
                            alert("上传图片失败");
                        //将返回的图片地址赋值给本地icon
                    }
                    else {
                        layer.msg('上传图片失败', {time: 2000});
                    }
                },
                error: function () {
                    alert("错误");
                }
            });
        },
        saveImgToDB:function(spuId,urls){
            console.log("urlList:"+urls);
            $.ajax({
                url:'/spuDetail/insert',
                data:{spuId:spuId,urlList:urls},
                dataType:'json',
                type:'post',
                success:function(data){
                    if(data.code==0)
                        return 0;
                    else
                        return 1;
                },
                error:function(){
                    return 1;
                }
            })
        }
    }
})