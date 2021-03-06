$().ready(function (){
    //初始化表格
    $('#spuTable').bootstrapTable({
        url:"/spu/findAllIdAndName",//要请求数据的文件路径
        method:'post',
        dataType: "json",
        // toolbar: '#toolbar',
        // async:false,
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
                field: 'name',
                title: '名称'
            }, {
                field: 'id',
                title: '编号'
            },{
                field: 'createtime',
                title: '创建时间',
                formatter:function(value,row,index){
                    var unixTimestamp = new Date( value) ;
                    return unixTimestamp.toLocaleString();
                }
            }, {
                field: 'operation',
                title: '操作',
                // events: operaEvents,
                formatter:
                    function formatterOperate(value, row, index){
                        return "&nbsp&nbsp<button onclick=\"putOff('"+row.id+"')\" class='btn btn-default btn-sm'><span class='glyphicon glyphicon-pencil'></span>下架</button>";
                    }
            }]
    });
});

// 请求服务数据时所传参数
function queryParams(params){
    return{
        //每页多少条数据
        pageSize: params.pageSize,
        // 请求第几页
        pageIndex:params.pageNumber,
        searchInput:$("#searchInput").val()
    }
}

//    操作列的按钮
function putOff(id){
    var r=confirm("该操作将下架此货品下的所有商品！是否确定下架？");
    if (r==true) {
        $.ajax({
            url:"/spu/putOff",
            type:"post",
            data:{id:id},
            dataType:"json",
            success:function(value){
                if(value.code==0) {
                    alert("操作成功！");
                    window.location.href = "/page/admin/putOffSpu.html";
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
    $('#spuTable').bootstrapTable('refresh');
});