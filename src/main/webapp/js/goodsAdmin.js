var oAttributes="";
var oSpuId="";
var FName0="",oFVal0="",nFval0="";
var FName1="",oFVal1="",nFval1="";
var FName2="",oFVal2="",nFval2="";
// ①spu框
$().ready(function (){
// 将侧边栏收起，腾出空间
    if(!$('body').hasClass('layout-fullwidth')) {
        $('body').addClass('layout-fullwidth');

    } else {
        $('body').removeClass('layout-fullwidth');
        $('body').removeClass('layout-default'); // also remove default behaviour if set
    }

    $(this).find('.lnr').toggleClass('lnr-arrow-left-circle lnr-arrow-right-circle');

    if($(window).innerWidth() < 1025) {
        if(!$('body').hasClass('offcanvas-active')) {
            $('body').addClass('offcanvas-active');
        } else {
            $('body').removeClass('offcanvas-active');
        }
    }
    //初始化表格
    $('#goodsTable').bootstrapTable({
        url:"/goods/getAllGoods_Spu_Img",//要请求数据的文件路径
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
                field: 'id',
                title: '编号',
                cellStyle:{
                    css:{"width":"50px"}
                },
            }, {
                field: 'attributes',
                title: '规格字符串',
                visible: false
            }, {
                field: 'categoriesAttributeVO.categoriesName',
                title: '分类'
            }, {
                field: 'name',
                title: '名称'
            }, {
                field: 'goodsImgList',
                title: '图片',
                formatter:function(value,row,index){
                    var str="";
                    for(var i=0;i<value.length;i++)
                        str+="<a href=\"javascript:void(0)\" onclick=\"showImg('"+value[i].imgUrl+"')\"><img style='width: 50px;margin: 3px;border: solid 1px #c8c8c8;' src='"+value[i].imgUrl+"'></img></a>";
                    return str;
                },
            }, {
                field: 'spuId',
                title: 'SPU编号',
                cellStyle:{
                    css:{"width":"50px"}
                },
                formatter:function(value,row,index){
                    return "<a href='#'>"+value+"</a>";
                },
            }, {
                field: 'cost',
                title: '成本价'
            }, {
                field: 'marketPrice',
                title: '市场价'
            }, {
                field: 'price',
                title: '艺格价'
            }, {
                field: 'salePrice',
                title: '促销价'
            }, {
                field: 'unit',
                title: '单位'
            }, {
                field: 'status',
                title: '状态',
                formatter:function(value,row,index){
                    if(value==0)
                        return "在售";
                    else if(value==1)
                        return "预售";
                    else if(value==2)
                        return "缺货";
                    else if(value==3)
                        return "下架";
                    else
                        return "促销";
                },
            }, {
                field: 'inventory',
                title: '库存'
            }, {
                field: 'saleCount',
                title: '销量'
            }, {
                field: 'note',
                title: '备注',
                cellStyle:{
                    css:{"width":"50px"}
                },
            }, {
                field: 'operation',
                title: '操作',
                // events: operaEvents,
                formatter:
                    function formatterOperate(value, row, index){
                        var imgList=[];
                        for(var i=0;i<row.goodsImgList.length;i++)
                            imgList.push(row.goodsImgList[i].imgUrl);
                        return "<a onclick=\"beforeEdit('"+row.id+"','"+row.name+"','"+imgList+"','"+row.cost+"','"+row.marketPrice+"','"+row.price+"','"+row.salePrice+"','"+row.unit+"','"+row.status+"','"+row.inventory+"','"+row.saleCount+"','"+row.note+"','"+row.attributes+"','"+row.spuId+"')\" data-toggle='modal' data-target='#ModalEdit' type='button' class='glyphicon glyphicon-pencil'></a>";
                }
            }]
    });
});
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
function updateAttr(){
    var str1="";
    var sstr1="";
    if($("#format0").css("display")!="none"){
        str1+=$("#inputformat0").val()+";规格1："+$("#format0 label").html();
        sstr1+=$("#inputformat0").val();
        nFval0=$("#inputformat0").val();
    }
    if($("#format1").css("display")!="none"){
        str1+="-"+$("#inputformat1").val()+";规格2："+$("#format1 label").html();
        sstr1+="-"+$("#inputformat1").val();
        nFval1=$("#inputformat1").val();
    }
    if($("#format2").css("display")!="none"){
        str1+="-"+$("#inputformat2").val()+";规格3："+$("#format2 label").html();
        sstr1+="-"+$("#inputformat2").val();
        nFval2=$("#inputformat2").val();
    }
    $("#inputAttributes").val(str1);
    var tempName=$("#inputName").val();
    if(tempName.indexOf(oAttributes)!=-1){
        tempName=tempName.replace(oAttributes,sstr1);
        $("#inputName").val(tempName);
    }
    else
        $("#inputName").val($("#inputName").val()+" "+sstr1);
    oAttributes=sstr1;
}
function beforeEdit(id,name,ImgStr,cost,marketPrice,price,salePrice,unit,status,inventory,saleCount,note,attributes,spuId){//,attr0,attr1,attr2
    oSpuId=spuId;
    $('#ModalEdit').on('show.bs.modal',function(event) {
        changeStatus();
        updateAttr();
        var attrInitList=attributes.split("-");
        var tempAttr="";
        for(var k=0;k<3;k++){
            $("#format"+k).css("display","none");
            $("#inputformat"+k).attr("disabled","disabled");
        }
        for(var i in attrInitList){
            var attrInitList2 =attrInitList[i].split(";");
            $("#format"+i).css("display","block");
            $("#inputformat"+i).removeAttr("disabled");
            $("#format"+i+" label").html(attrInitList2[1].substring(4));
            $("#inputformat"+i).val(attrInitList2[0]);
            tempAttr+=attrInitList2[0]+"-";
            if(i==0) {
                FName0 = attrInitList2[1].substring(4);
                oFVal0 = attrInitList2[0];
            }
            if(i==1) {
                FName1 = attrInitList2[1].substring(4);
                oFVal1 = attrInitList2[0];
            }
            if(i==2) {
                FName2 = attrInitList2[1].substring(4);
                oFVal2 = attrInitList2[0];
            }
        }
        oAttributes=tempAttr.substring(0,tempAttr.length-1);
        $("#goodsImgList").css("display","block");
        $("#showImgDiv").css("display","none");
        $("#inputId").val(id);
        $("#hiddenId").val(id);
        $("#inputName").val(name);
        $("#inputCost").val(cost);
        $("#inputMarketPrice").val(marketPrice);
        $("#inputPrice").val(price);
        if(salePrice!=null&&salePrice!="null")
            $("#inputSalePrice").val(salePrice);
        $("#inputUnit").val(unit);
        $("#inputStatus").val(status);
        $("#inputInventory").val(inventory);
        $("#inputSaleCount").val(saleCount);
        if(note!=null&&note!="null")
            $("#inputNote").val(note);
        var ImgList=ImgStr.split(",");
        $("#goodsImgList").html("");
        for(var i in ImgList)
            $("#goodsImgList").append("<a href=\"javascript:void(0)\" onclick=\"showImg('"+ImgList[i]+"')\"><img style='width: 50px;margin: 3px;border: solid 1px #c8c8c8;' src='"+ImgList[i]+"'></img></a>");
    });
}
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
function changeStatus(){
    if($("#inputStatus").val()=="4")
        $("#inputSalePrice").removeAttr("disabled");
    else
        $("#inputSalePrice").attr("disabled","disabled");
}
// 保存修改
function updateGoods(){
    var name=$("#inputName").val();
    var cost=$("#inputCost").val();
    var marketPrice=$("#inputMarketPrice").val();
    var price=$("#inputPrice").val();
    var salePrice="0";
    if($("#inputStatus").val()=="4")
        salePrice=$("#inputSalePrice").val();
    var unit=$("#inputUnit").val();
    var inventory=$("#inputInventory").val();
    var saleCount=$("#inputSaleCount").val();
    var note=$("#inputNote").val();
    var img=0;
    if("none"!=$("#showImgDiv").css("display")){
        if(pageImg.imgArray.length<0)
            img=1;
    }
    var formats=0;
    if("none"!=$("#format0").css("display")){
        if($("#inputformat0").val().trim()==""||$("#inputformat0").val().trim()=="null"||$("#inputformat0").val().trim()==null)
            formats=1;
    }
    if("none"!=$("#format1").css("display")){
        if($("#inputformat1").val().trim()==""||$("#inputformat1").val().trim()=="null"||$("#inputformat1").val()==null)
            formats=1;
    }
    if("none"!=$("#format2").css("display")){
        if($("#inputformat2").val().trim()==""||$("#inputformat2").val().trim()=="null"||$("#inputformat2").val()==null)
            formats=1;
    }
    if(name!=null&&name!="null"&&name.trim()!=""&&
        cost!=null&&cost!="null"&&cost.trim()!=""&&
        marketPrice!=null&&marketPrice!="null"&&marketPrice.trim()!=""&&
        price!=null&&price!="null"&&price.trim()!=""&&
        salePrice!=null&&salePrice!="null"&&salePrice.trim()!=""&&
        unit!=null&&unit!="null"&&unit.trim()!=""&&
        inventory!=null&&inventory!="null"&&inventory.trim()!=""&&
        saleCount!=null&&saleCount!="null"&&saleCount.trim()!=""&&
        note!=null&&note!="null"&&note.trim()!=""&&
        img==0&&formats==0) {

        $("#ModalEdit").modal("hide");
        var formData = $("#form_update").serialize();
        var goodsId = $("#inputId").val();
        $.ajax({
            url: '/goods/update',
            type: 'post',
            data: formData,
            dataType: 'json',
            success: function (data) {
                $(".loadingWrap").removeClass();
                if (data.code == 0) {
                    var b=updateSpuAttr();
                    if(b==0){
                        var a = pageImg.uploadImg(goodsId);
                        if (a == 0) {
                            layer.msg('修改成功', {time: 2000});
                            setTimeout("window.location.href='../admin/goodsAdmin.html'; ", 1000);
                        }
                        else
                            layer.msg('图片上传失败', {time: 2000});
                    }
                    else
                        layer.msg('同步更新spu失败', {time: 2000});
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
// 修改spu的attributes_name
function updateSpuAttr(){
    var a=1;
    $.ajax({
        url:'/spu/updateSPU_Attr',
        type:"post",
        data:{oSpuId:oSpuId,FName0:FName0,oFVal0:oFVal0,nFval0:nFval0,FName1:FName1,oFVal1:oFVal1,nFval1:nFval1,FName2:FName2,oFVal2:oFVal2,nFval2:nFval2},
        dataType:'json',
        async:false,
        success:function(data){
            if(data.code==0){
                console.log("更新spu的attributes_name成功");
                a=0;
            }
            else
                console.log("更新spu的attributes_name失败");
        },
        error:function(){
            console.log("请求更新spu的attributes_name失败");
        }
    });
    return a;
}
var pageImg = new Vue({
    el: '#GoodsAdmin',
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
            $("#goodsImgList").css("display","none");
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
        uploadImg: function (goodsId) {
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
                            var as = that.saveImgToDB(goodsId, msg.data + "");
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
        saveImgToDB:function(goodsId,urls){
            var a=1;
            console.log("goodsId:"+goodsId);
            console.log("urls:"+urls);
            $.ajax({
                url:'/goodsImg/update',
                data:{goodsId:goodsId,urlList:urls},
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