var oInit = new Object();
$().ready(function () {
    // $(".search").css({"width":"100%"});
// 初始化
    $.ajax({
        url: '/attribute/getAllCategoriesAttribute',
        type: "post",
        dataType: "json",
        success: function (value) {
            $('#tableAttribute').bootstrapTable({
                uniqueId: 'attrValue',
                type: 'post',
                data: value.data,
                pagination: true,
                search:true,
                searchAlign:"left",
                columns: [
                    {
                        field: 'categoryId',
                        sortable: true,
                        title: '类别编号',
                        cellStyle: {
                            css: {"width": "80px"}
                        },
                    },{
                        field: 'categoriesName',
                        sortable: true,
                        title: '类别',
                    }, {
                        field: 'attribute0',
                        sortable: true,
                        title: '规格一',
                        class: 'editable',
                    }, {
                        field: 'attribute1',
                        sortable: true,
                        title: '规格二',
                        class: 'editable',
                    }, {
                        title: '规格三',
                        sortable: true,
                        field: 'attribute2',
                        class: 'editable'
                    }, {
                        title: '操作',
                        formatter: formatterOperate,
                        cellStyle: {
                            css: {"width": "210px"}
                        },
                    }]
            });
        }
    })
});
//操作按钮
function formatterOperate(value, row, index){
    return "&nbsp&nbsp<button onclick='editRow("+index+")' class='btn btn-default btn-sm'><span class='glyphicon glyphicon-pencil'></span>编辑</button>&nbsp"+
        "<button onclick='saveRow("+index+","+row.categoryId+")' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-ok'></span>保存</button>&nbsp";
}
// 编辑
function editRow(index){
    console.log(index);
    // var str="#tableAttribute>tbody>tr:nth-child("+ (index+1) +") td.editable";
    var str="#tableAttribute>tbody>tr[data-index='"+index+"'] td.editable";
    $(str).each(function(){
        var value = $(this).text();
        $(this).html("<input value='"+value+"'>");
    });
}
//根据分类id删除规格属性
function deleteArrtibute(categoryId){
    $.ajax({
        type: 'post',
        url: '/attribute/deleteAttributeByCategoryId',
        data: {"categoryId": categoryId},
        dataType: 'json',
        async:false,
        success:function(value){
            $(".loadingWrap").removeClass();
            if(value.code!=0){
                del=false;
            }
        },
        error: function (value) {
            $(".loadingWrap").removeClass();
            del = false;
        },
        beforeSend: function(){
            $('<div class="loadingWrap"></div>').appendTo("body");
        },
        complete: function(){
            $(".loadingWrap").removeClass();
        }
    })
}
//根据分类id和属性名添加规格属性
function insertArrtibute(categoryId,name){
    $.ajax({
        type: 'post',
        url:'/attribute/insertAttribute',
        data:{"categoryId":categoryId,"name":name},
        dataType:'json',
        async:false,
        success:function(value){
            $(".loadingWrap").removeClass();
            if(value.code!=0){
              insert0=false;
            }
        },
        error:function(value){
            $(".loadingWrap").removeClass();
            insert0=false;
        },
        beforeSend: function(){
            $('<div class="loadingWrap"></div>').appendTo("body");
        },
        complete: function(){
            $(".loadingWrap").removeClass();
        }
    });
}
// 保存修改
function saveRow(index,categoryId){
    var obj=$("#tableAttribute>tbody>tr[data-index='"+index+"'] td.editable");
    var del=true,insert1=true,insert2=true,insert0=true;
    var attribute0 = obj.eq(0).find("input").val();
    var attribute1 = obj.eq(1).find("input").val();
    var attribute2 = obj.eq(2).find("input").val();
    var boolean0=Boolean(attribute0.trim().length!=0&&attribute0.trim()!="-");
    var boolean1=Boolean(attribute1.trim().length!=0&&attribute1.trim()!="-");
    var boolean2=Boolean(attribute2.trim().length!=0&&attribute2.trim()!="-");
    if(boolean0||boolean1||boolean2) {
        deleteArrtibute(categoryId);
        if(boolean0){
            insertArrtibute(categoryId,attribute0);
        }
        if(boolean1){
            insertArrtibute(categoryId,attribute1);
        }
        if(boolean2){
            insertArrtibute(categoryId,attribute2);
        }
    }
    if(del&&insert0&&insert1&&insert2)
        alert("修改成功");
    else
        alert("修改失败，请联系管理员。");
    $("#tableAttribute").bootstrapTable('updateRow', {
        index: index,
        row: {
            attribute0: attribute0,
            attribute1: attribute1,
            attribute2: attribute2
        }
    });
}
