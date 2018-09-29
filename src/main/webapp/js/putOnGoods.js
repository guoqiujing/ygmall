var newGoodsForm="";
var clickNum=1;
// ①spu框
$().ready(function (){
    //初始化表格
    $('#spuTable').bootstrapTable({
        url:"/spu/findAllIdAndName",//要请求数据的文件路径
        method:'post',
        dataType: "json",
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        buttonsAlign:"left",
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页
        pageNumber: 1,                     //初始化加载第一页，默认第一页
        pageSize:4,                       //每页的记录行数（*）
        pageList: [4,8,16,32],        //可供选择的每页的行数（*）
        queryParamsType : "undefined",     //查询参数组织方式
        queryParams:queryParams,//请求服务器时所传的参数
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        responseHandler:function(res){
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res.data;
        },
        columns:
            [{
                field: 'name',
                title: '名称'
            }, {
                field: 'id',
                title: '编号'
            }, {
                field: 'categoryId',
                title: '类别编号'
            }, {
                field: 'operation',
                title: '操作',
                // events: operaEvents,
                formatter:
                    function formatterOperate(value, row, index){
                        return "&nbsp&nbsp<button type='button' onclick='selectSpu(\""+row.id+"\",\""+row.name+"\",\""+row.categoryId+"\")' class='btn btn-default btn-sm'>选择</button>";
                    }
            }]
    });
});
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
//  ①操作列的按钮
//  ②生成规格输入框
function selectSpu(id,name,categoryId){
    $("#selectSpuBg h2").html(name);
    $("#goodsForm1 .categoryId").val(categoryId);
    $("#goodsForm1 .name").val(name);
    $("#goodsForm1 .spuId").val(id);
    var height=$(".selectSpu").height();
    var width=$(".selectSpu").width();
    $("#selectSpuBg").css({"padding-top": "120px","width": width,"height": height,"background": "rgba(255, 255, 255, 0.78)","z-index": "10","position":"absolute"});
    $("#selectSpuBg").fadeIn(200);
    $("#createGoods").fadeIn(200);
    $.ajax({
        url:"/attribute/getCategoriesAttributeByCategoryId",
        type:"post",
        data:{categoryId:categoryId},
        dataType:"json",
        success:function(value){
            if(value.code==0) {
                $("#attributesDiv").html("");
                $("#attributesDiv1").html("");
                if(value.data.attribute1!=null){
                    $("#attributesDiv").css("display","block");
                    $("#attributesDiv").append("<label for='' class='col-sm-2 control-label'>规格2："+value.data.attribute1+"</label><div class='col-sm-3'><input onchange='changeName()' type='text' id='' class='form-control attribute1'></div>");
                }
                if(value.data.attribute2!=null){
                    $("#attributesDiv").append("<label for='' class='col-sm-2 control-label'>规格3："+value.data.attribute2+"</label><div class='col-sm-3'><input onchange='changeName()' type='text' id='' class='form-control attribute2'></div>");
                }
                if(value.data.attribute0!=null){
                    $("#attributesDiv1").css("display","block");
                    $("#attributesDiv1").append("<label for='' class='col-sm-2 control-label'>规格1："+value.data.attribute0+"</label><div class='col-sm-3'><input onchange='changeName()' type='text' id='' class='form-control attribute0'></div>");
                }
                newGoodsForm=$("#goodsForm1").html();
            }
            else
                alert("操作失败！");
        },
        error:function(){
            alert("请求失败！");
        }
    })
}
// 当商品规格输入框改变时，更新隐藏的商品名称
function  changeName(){
    var oldname= $("#selectSpuBg h2").html();
    var f0="";
    var f1="";
    var f2="";
    if($(".attribute0").length>0)
        f0=$(".attribute0").eq(0).val();
    if($(".attribute1").length>0)
        f1=$(".attribute1").eq(0).val();
    if($(".attribute2").length>0)
        f2=$(".attribute2").eq(0).val();
    $(".name").eq(0).val(oldname+" "+f0+" "+f1+" "+f2);
}
// 点击添加按钮，添加一个商品表单
function addGoodsForm(){
    clickNum++;
    $("#createGoods .panel-body").append("<form class='form-horizontal' id='goodsForm"+clickNum+"'>"+newGoodsForm+"</form><hr>");
}
// ①查询
// $('#btn_query').click(function () {
//     console.log("11111");
//     $('#spuTable').bootstrapTable('refresh');
// });
// 修改“促销价”输入框状态
function changeSale(){
    var status=$(".status").val();
    if(status==4){
        $(".salePrice").removeAttr("disabled");
    }
    else{
        $(".salePrice").attr("disabled","disabled");
    }
}
var page = new Vue({
    el: '#putOnGoods',
    data: {
        file: [],//要上传的图片文件
        imgArray: [],//头像图片预览图
        imgShow: true//是否显示预览图，默认显示
    },
    methods: {
        //改变选择的图片时
        getFile: function (event) {
            //如果图片文件不为空，给本地file赋值为接收的图片
            var putOnFiles=event.target.files;
            if (putOnFiles) {
                $(".fileBtn").html("重新上传");
                this.file=[];
                if(putOnFiles.length>5){
                    alert("每个商品最多只能上传5张图片！");
                }
                else {
                    for (var i = 0; i < putOnFiles.length; i++)
                        this.file.push(event.target.files[i]);
                }
            }
            //图片文件为空，给本地file赋空值
            else {
                this.file = '';
            }
        },
        //图片预览
        showImg: function (event) {
            $("#showImgDiv").html("");
            var file = event.target.files;
            var reader = new FileReader();
            var that = this;
            console.log(file.length);
            if (file && event.target.files&&file.length<6) {
                for(var i=0;i<file.length;i++) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file[i]);
                    reader.onload = function (e) {
                        that.imgArray.push(e.target.result);
                    }
                }
            }
            // else {
            //     this.imgShow = false;
            // }
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
                        // var as=that.saveImgToDB(spuId,msg.data+"");
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