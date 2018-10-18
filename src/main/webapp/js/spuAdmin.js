// ①spu框
$().ready(function (){
// 将侧边栏收起，腾出空间
    if(!$('body').hasClass('layout-fullwidth')) {
        $('body').addClass('layout-fullwidth');
    } else {
        $('body').removeClass('layout-fullwidth');
        $('body').removeClass('layout-default'); // also remove default behaviour if set
    }
    if($(window).innerWidth() < 1025) {
        if(!$('body').hasClass('offcanvas-active')) {
            $('body').addClass('offcanvas-active');
        } else {
            $('body').removeClass('offcanvas-active');
        }
    }
    //初始化表格
    $('#spuTable').bootstrapTable({
        url:"/spu/selectAllSpu_Img",
        method:'post',
        dataType: "json",
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        buttonsAlign:"left",
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页
        pageNumber: 1,                     //初始化加载第一页，默认第一页
        pageSize:8,                       //每页的记录行数（*）
        pageList: [8,16,24,32],        //可供选择的每页的行数（*）
        queryParamsType : "undefined",     //查询参数组织方式
        queryParams:queryParams,//请求服务器时所传的参数
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        responseHandler:function(res){
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res.data;
        },
        columns:
            [{
                field: 'spu.id',
                title: '编号',
                visible: false
            // }, {
            //     field: 'attributes',
            //     title: '规格字符串',
            //     visible: false
            }, {
                field: 'categoriesName',
                title: '分类',
                cellStyle:{
                    css:{"width":"110px"}
                },
                formatter:
                    function formatterOperate(value, row, index) {
                        return value.replace(/>/g,"><br>");
                    }
            }, {
                field: 'brandName',
                title: '品牌'
            }, {
                field: 'spu.name',
                title: '名称'
            }, {
                field: 'spuImgList',
                title: '图片',
                formatter:function(value,row,index){
                    var str="";
                    for(var i=0;i<value.length;i++)
                        str+="<a href=\"javascript:void(0)\" onclick=\"showImg('"+value[i].imgUrl+"')\"><img style='width: 50px;margin: 3px;border: solid 1px #c8c8c8;' src='"+value[i].imgUrl+"'></img></a>";
                    return str;
                },
            }, {
                field: 'spu.subtitle',
                title: '宣传标语',
                cellStyle:{
                    css:{"width":"100px"}
                },
            }, {
                field: 'spu.status',
                title: '状态',
                cellStyle:{
                    css:{"width":"70px"}
                },
                formatter:function(value,row,index){
                    if(value=="0")
                        return "默认";
                    else
                        return "已下架";
                },
            }, {
                field: 'spu.saleCount',
                title: '销量'
            }, {
                field: 'spu.commentCount',
                title: '评论数'
            }, {
                field: 'spu.params',
                title: '详细参数',
                cellStyle:{
                    css:{"width":"200px"}
                },
                formatter:
                    function formatterOperate(value, row, index) {
                        return value.replace(/"/g,"").replace(/{/g,"").replace(/}/g,"").replace(/,/g,"<br>");
                    }
            }, {
                field: 'spu.createtime',
                title: '创建时间',
                formatter:
                    function formatterOperate(value, row, index) {
                        return new Date(parseInt(value) ).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                    }
            }, {
                field: 'operation',
                title: '操作',
                formatter:
                    function formatterOperate(value, row, index){
                        var imgList=[];
                        for(var i=0;i<row.spuImgList.length;i++)
                            imgList.push(row.spuImgList[i].imgUrl);
                        return "<a data-toggle='modal' data-target='#ModalEdit' type='button' class='glyphicon glyphicon-pencil' onclick=\"beforeEdit('"+
                            row.spu.id+"','"+row.spu.name+"','"+imgList+"','"+
                            row.spu.createtime+"','"+row.spu.saleCount+"','"+
                            row.spu.commentCount+"','"+row.spu.params.replace(/"/g,"")+"','"+
                            row.spu.subtitle+"','"+row.spu.status+"','"+row.brandName+"','"+row.categoriesName+
                            "')\"></a>";
                }
            }]
    });
});

function beforeEdit(id,name,ImgStr,createtime,saleCount,commentCount,params,subtitle,status,brandName,categoriesName){//,attr0,attr1,attr2
    // console.log(id+"、"+name+"、"+ImgStr+"、"+createtime+"、"+saleCount+"、"+commentCount+"、"+params+"、"+subtitle+"、"+status+"、"+brandName);
    // 给弹出的模态框赋初值
    $('#ModalEdit').on('show.bs.modal',function(event) {
        $("#spuImgList").css("display","block");
        $("#showImgDiv").css("display","none");
        $("#inputId").val(id);
        $("#hiddenId").val(id);
        $("#inputCategoriesName").val(categoriesName);
        $("#inputBrandName").val(brandName);
        $("#inputName").val(name);
        var ImgList=ImgStr.split(",");
        $("#spuImgList").html("");
        for(var i in ImgList)
            $("#spuImgList").append("<a href=\"javascript:void(0)\" onclick=\"showImg('"+ImgList[i]+"')\"><img style='width: 50px;margin: 3px;border: solid 1px #c8c8c8;' src='"+ImgList[i]+"'></img></a>");
        $("#inputSubtitle").val(subtitle);
        $("#inputStatus").val(status);
        $("#inputSaleCount").val(saleCount);
        $("#inputCommentCount").val(commentCount);
        // $("#paramsDiv").html(params);
        var paramsList=params.replace(/{/g,"").replace(/}/g,"").split(",");
        var paramsHtml="";
        for(var j=0;j<paramsList.length;j++) {
            var temparam=paramsList[j].split(":");
            paramsHtml+="<div class=\"form-group\">" +
            "<div class=\"col-sm-5\">" +
            "<input class=\"form-control attrName\" name=\"attrName\" value='"+temparam[0]+"' onblur=\"ifAttrRow(this)\">" +
            "</div>" +
            "<div class=\"col-sm-7\">" +
            "<input class=\"form-control attrValue\" name=\"attrValue\"  value=\""+temparam[1]+"\" alt=\"0\" onblur=\"ifAttrRow(this)\">" +
            "</div></div>";
        }
        $("#paramsDiv").html(paramsHtml);
        $("#inputCreatetime").val(new Date(parseInt(createtime) ).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " "));
    });
}

// 对字符串参数中的引号进行转义
// function altStrQuotes(str){
//     return str.replace(/'/g,"\\'").replace(/"/g,'\\"');
// }
// 添加一行货品参数
    function createAttrRow(){
    var newRow = "<div class='form-group'>" +
        "<div class='col-sm-5'><input class='form-control attrName' name='attrName' placeholder='参数名称：' onblur='ifAttrRow(this)'></div>" +
        "<div class='col-sm-7'><input class='form-control attrValue' name='attrValue' placeholder='参数值' alt='0' onblur='ifAttrRow(this)'></div>" +
        "</div>";
    $("#paramsDiv").append(newRow);
}
// 若当前行的参数名和参数值都为空，则删除当前行，则取消对下一行的生成
function ifAttrRow(target){
    if($(target).val()==null||$(target).val().trim()==""){
        if($(target).parent().prev().find("input").attr("name")){
            if($(target).parent().prev().find("input").val().trim()==""||$(target).parent().prev().find("input").attr("value").trim()==null)
                $(target).parent().parent().remove();
        }
        if($(target).parent().next().find("input").attr("name")){
            if($(target).parent().next().find("input").val().trim()==""||$(target).parent().next().find("input").attr("value").trim()==null)
                $(target).parent().parent().remove();
        }
    }
}
// 校验器
$(function () {
    $('#form_update').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded:[":disabled"],//表示只对于禁用域不进行验证，其他的表单元素都要验证
        fields: {
            name: {validators: {notEmpty: {message: '请输入商品名称'}}},
            cost: {validators: {notEmpty: {message: '请输入商品成本'}}},
            marketPrice: {validators: {notEmpty: {message: '请输入商品市场价'}}},
            price: {validators: {notEmpty: {message: '请输入商品售价'}}},
            salePrice: {validators: {notEmpty: {message: '请输入商品促销价'}}},
            unit: {validators: {notEmpty: {message: '请输入商品计数单位'}}},
            inventory: {validators: {notEmpty: {message: '请输入商品库存'}}},
            saleCount: {validators: {notEmpty: {message: '请输入商品销量'}}},
            inputformat0: {validators: {notEmpty: {message: '不能为空'}}},
            inputformat1: {validators: {notEmpty: {message: '不能为空'}}},
            inputformat2: {validators: {notEmpty: {message: '不能为空'}}},
        }
    })
})
function showImg(url) {
    var img = "<img src='" + url + "' />";
    img.src = url;
    var setting = {
        type: 1,
        shadeClose: true,
        scrollbar: false,//屏蔽浏览器滚动条
        skin: 'layui-layer-nobg', //没有背景色
        title: false, //不显示标题
        content: img //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
    }
    var windowH = $(window).height();
    var windowW = $(window).width();
    getImageWidth(url, function (w, h) {
        // 调整图片大小
        if (w > windowW || h > windowH) {
            if (w > windowW && h > windowH) {
                setting.area = [windowW + "px", windowH + "px"];
            } else if (w > windowW) {
                setting.area = [windowW + 'px', 'auto'];
            } else {
                setting.area = ['auto', window.innerHeight + 'px'];
            }
        }
        else{
            setting.area = [img.width + 'px', img.height + 'px'];
        }
        layer.open(setting);
    });
}
function getImageWidth(url,callback){
    var img = new Image();
    img.src = url;
    // 如果图片被缓存，则直接返回缓存数据
    if(img.complete){
        callback(img.width, img.height);
    }else{
        // 完全加载完毕的事件
        img.onload = function(){
            callback(img.width, img.height);
        }
    }
}
// ①请求服务数据时所传参数
function queryParams(params){
    return{
        //每页多少条数据
        pageSize: params.pageSize,
        // 请求第几页
        pageIndex:params.pageNumber,
        searchInput:$("#searchInput").val()
    }
}
function putOnOrOff(id,status){
    var a=1;
    var url="";
    if(status=="1")
        url="/spu/putOff";
    else
        url="/spu/putOn";
    $.ajax({
        url:url,
        type:"post",
        data:{id:id},
        dataType:"json",
        async:false,
        success:function(value){
            if(value.code==0) {
                a=0;
                console.log("货品下架成功！");
            }
            else{
                console.log("货品下架失败！");
            }
        },
        error:function(){
            console.log("请求下架货品失败！");
        }
    });
    return a;
}
// 保存修改
function updateSpu(){
    var id=$("#hiddenId").val();
    var status=$("#inputStatus").val();
    var name=$("#inputName").val();
    var subtitle=$("#inputSubtitle").val();
    var saleCount=$("#inputSaleCount").val();
    var commentCount=$("#inputCommentCount").val();
    var img=0;
    if("none"!=$("#showImgDiv").css("display")){
        if(pageImg.imgArray.length<0)
            img=1;
    }
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
    if(name!=null&&name!="null"&&name.trim()!=""&&
        subtitle!=null&&subtitle!="null"&&subtitle.trim()!=""&&
        saleCount!=null&&saleCount!="null"&&saleCount.trim()!=""&&
        commentCount!=null&&commentCount!="null"&&commentCount.trim()!=""&&
        img==0) {

        $("#ModalEdit").modal("hide");
        $.ajax({
            url: '/spu/updateSPUSelective',
            type: 'post',
            traditional: true,
            data: {id:id,name:name,subtitle:subtitle,saleCount:saleCount,commentCount:commentCount,attrNamesArray:attrNamesArray,attrValuesArray:attrValuesArray},
            dataType: 'json',
            success: function (data) {
                $(".loadingWrap").removeClass();
                if (data.code == 0) {
                    var a = pageImg.uploadImg(id);
                    if (a == 0) {
                            var b=putOnOrOff(id,status);
                            if(b==0){
                                layer.msg('修改成功', {time: 2000});
                                setTimeout("window.location.href='../admin/spuAdmin.html'; ", 1000);
                            }
                            else
                                layer.msg('货品下架失败', {time: 2000});
                    }
                    else
                        layer.msg('图片上传失败', {time: 2000});
                }
            },
            error: function (data) {
                $(".loadingWrap").removeClass();
                layer.msg('修改失败', {time: 2000});
            },
            beforeSend: function () {
                $('<div class="loadingWrap"></div>').appendTo("body");
            },
            complete: function () {
                $(".loadingWrap").removeClass();
            }
        })
        $("#form_update").submit();
    }
}
var pageImg = new Vue({
    el: '#spuAdmin',
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
        },
        //图片预览
        showImgVue: function (event) {
            $("#spuImgList").css("display","none");
            $("#showImgDiv").css("display","block");
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
        uploadImg: function (id) {
            //阻止元素发生默认的行为
            // event.preventDefault();
            var a=0;
            var that=this;
            var formData = new FormData();
            if(this.file.length>0) {
                for (var i = 0; i < this.file.length; i++) {
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
                    async: false,
                    success: function (msg) {
                        $(".loadingWrap").removeClass();
                        if (msg.code == 0) {
                            console.log("上传至服务器成功");
                            console.log("图片地址：" + msg.data);
                            var as = that.saveImgToDB(id, msg.data + "");
                            if (as == 1) {
                                console.log("上传图片失败");
                                a = 1;
                            }
                        }
                        else {
                            console.log('上传图片失败');
                            a = 1;
                        }
                    },
                    error: function () {
                        $(".loadingWrap").removeClass();
                        console.log("上传图片失败");
                        a = 1;
                    },
                    beforeSend: function () {
                        $('<div class="loadingWrap"></div>').appendTo("body");
                    },
                    complete: function () {
                        $(".loadingWrap").removeClass();
                    }
                });
            }
            return a;
        },
        saveImgToDB:function(id,urls){
            var a=1;
            $.ajax({
                url:'/spuDetail/update',
                data:{spuId:id,urlList:urls},
                dataType:'json',
                type:'post',
                async: false,
                success:function(data){
                    if(data.code==0) {
                        a = 0;
                        console.log("保存图片成功");
                    }
                },
                error:function(){
                    console.log("保存图片失败");
                },
                beforeSend: function(){
                    $('<div class="loadingWrap"></div>').appendTo("body");
                },
                complete: function(){
                    $(".loadingWrap").removeClass();
                }
            });
            return a;
        }
    }
})