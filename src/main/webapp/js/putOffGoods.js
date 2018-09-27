$().ready(function (){
    //初始化表格
    $('#goodsTable').bootstrapTable({
        url:"/goods/getAllPutOff",//要请求数据的文件路径
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
                title: '编号'
            },{
                field: 'name',
                title: '名称'
            },{
                field: 'operation',
                title: '操作',
                // events: operaEvents,
                formatter:
                    function formatterOperate(value, row, index){
                        return "&nbsp&nbsp<button onclick='putOff("+row.id+")' class='btn btn-default btn-sm'><span class='glyphicon glyphicon-pencil'></span>下架</button>";
                    }
            }
            ]
    });
});

// 请求服务数据时所传参数
function queryParams(params){
    console.log("111111");
    console.log($('#searchInput').val());
    return{
        //每页多少条数据
        pageSize: params.pageSize,
        // 请求第几页
        pageIndex:params.pageNumber,
        searchInput:$('#searchInput').val()
    }
}

//    操作列的按钮
function putOff(id){
    var r=confirm("确定下架？");
    if (r==true) {
        $.ajax({
            url:"/goods/putOffById",
            type:"post",
            data:{id:id},
            dataType:"json",
            success:function(value){
                if(value.code==0) {
                    alert("操作成功！");
                    window.location.href = "/page/admin/putOffGoods.html";
                }
                else
                    alert("操作失败！");
            },
            error:function(){
                alert("请求失败！");
            }
        })
    }
}
//查询
$('#btn_query').click(function () {
    $('#goodsTable').bootstrapTable('refresh');
});