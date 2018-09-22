var oInit = new Object();
$().ready(function (){
// 初始化一级分类
    $.ajax({
        url:'/categories/GetCategories_l1',
        type : "post",
        dataType : "json",
        success : function(value) {
            $('#tableLevel12').bootstrapTable({
                uniqueId: 'attrValue',
                type:'post',
                data:value.data,
                pagination:true,
                toolbar: '#toolbar',
                buttonsAlign:"left",
                detailView:true,
                columns:[
                    {
                        field: 'id',
                        sortable:true,
                        title: '编号',
                    }, {
                        field: 'name',
                        sortable:true,
                        title: '名称',
                        class: 'editable',
                    }, {
                        field: 'status',
                        sortable:true,
                        title: '是否启用',
                        class: 'editable',
                        formatter:function(value,row,index){
                            if(value=='0')
                                return "未启用";
                            else
                                return "已启用";
                        }
                    },{
                        title: '操作',
                        field: 'operate',
                        formatter: formatterOperate,
                        cellStyle:{
                            css:{"width":"410px"}
                        },
                    }],
                onExpandRow: function(index, row, $detail) {
                    oInit.InitSubTable(index, row, $detail);
                }
            });
        }
    });
// 弹出三级模态框的时候初始化表格
    $('#ModalLevel3').on('show.bs.modal',function(event) {
        var button = $(event.relatedTarget); // 触发事件的按钮
        var id=button.data("id");
        $.ajax({
            url:'/categories/GetCategories_l23',
            type : "post",
            data:{"id":id},
            dataType : "json",
            error:function(value){
                alert("获取数据失败，请联系管理员。");
            },
            success : function(value) {
                $("#tableLevel3").bootstrapTable({
                    data:value.data,
                    showHeader:true,
                    pagination:true,
                    cache:false,
                    silent: true,
                    uniqueId: "MENU_ID",
                    columns: [{
                        field: 'id',
                        sortable:true,
                        title: '编号',
                    }, {
                        field: 'name',
                        sortable:true,
                        title: '名称',
                        class: 'editable'
                    }, {
                        field: 'status',
                        sortable:true,
                        title: '是否启用',
                        class: 'editable',
                        formatter:function(value,row,index){
                            if(value=='0')
                                return "未启用";
                            else
                                return "已启用";
                        }
                    },{
                        title: '操作',
                        field: 'operate',
                        formatter: function formatterOperate(value, row, index){
                            return "&nbsp&nbsp<button onclick='editRow("+index+",3)' class='btn btn-default btn-sm'><span class='glyphicon glyphicon-pencil'></span>编辑</button>&nbsp"+
                                "<button onclick='saveRow("+index+",3,"+row.id+")' class='btn btn-default btn-sm'><span class='glyphicon glyphicon-ok'></span>保存</button>&nbsp"+
                                "<button onclick='delRow("+index+","+row.id+")' class='btn btn-default btn-sm'><span class='glyphicon glyphicon-remove'></span>删除</button>";
                        }
                    }]
                });
            }
        });
    });
// 关闭三级模态框后销毁表格
    $('#ModalLevel3').on('hide.bs.modal',function(event) {
        $("#tableLevel3").bootstrapTable('destroy');
    });
});
//	添加分类时所需的最基本的信息，也就是一级分类所需的信息
function addBaseRow(){
    $('#ModalAdd').on('show.bs.modal',function(event) {
        $("#ModalAdd form").empty();
        $("#ModalAdd .modal-title").html("&nbsp添加顶级分类");
        str="<div class='form-group'>" +
            "<label for='inputName' class='col-sm-4 control-label'>类别名称</label><div class='col-sm-5'>"+
            "<input name='name' type='text' class='form-control' id='inputName' placeholder='请输入类别名称'>"+
            "</div></div>"+
            "<div class='form-group'>"+
            "<label for='selectType' class='col-sm-4 control-label'>启用状态</label><div class='col-sm-5'>"+
            "<select name='status' class='form-control'><option value='1'>启用</option><option value='0'>不启用</option></select>"+
            "</div></div>";
        $("#ModalAdd form").prepend(str);
    });
}
function addRow(index,id,name){
    $('#ModalAdd').on('show.bs.modal',function(event) {
        // console.log($(event.relatedTarget).parents(".detail-view").prev().children("td").eq(1));
        var button = $(event.relatedTarget); // 触发事件的按钮
        var mark=button.data("mark");
        var grand_level=$(button).parents(".detail-view").prev().children().eq(1).html();
        var grandName=$(button).parents(".detail-view").prev().children().eq(2).html();
        $("#ModalAdd form").empty();
        $("#ModalAdd .modal-title").html("&nbsp添加顶级分类");
        str="<div class='form-group'>" +
            "<label for='inputName' class='col-sm-4 control-label'>类别名称</label><div class='col-sm-5'>"+
            "<input name='name' type='text' class='form-control' id='inputName' placeholder='请输入类别名称'>"+
            "</div></div>"+
            "<div class='form-group'>"+
            "<label for='selectType' class='col-sm-4 control-label'>启用状态</label><div class='col-sm-5'>"+
            "<select name='status' class='form-control'><option value='1'>启用</option><option value='0'>不启用</option></select>"+
            "</div></div>";
        $("#ModalAdd form").prepend(str);
        $("#ModalAdd .modal-title").html("&nbsp"+name+"&nbsp>");
        $("#ModalAdd form").prepend("<div class='form-group'>"+
            "<label class='col-sm-4 control-label'>上级目录</label><div class='col-sm-5'>"+
            "<input class='col-sm-5 form-control' disabled='disabled' value='"+name+"'><input style='display:none' name='parent_level' value='"+id+"'>"+
            "</div></div>");
        if(mark==2){
            $("#ModalAdd .modal-title").html("&nbsp"+grandName+"&nbsp>&nbsp"+name+"&nbsp>&nbsp");
            $("#ModalAdd form").prepend("<div class='form-group'>"+
                "<label class='col-sm-4 control-label'>顶级目录</label><div class='col-sm-5'>"+
                "<input class='col-sm-5 form-control' disabled='disabled' value='"+grandName+"'><input style='display:none' name='grand_level' value='"+grand_level+"'>"+
                "</div></div>");
        }
    });
}
// 一级分类操作按钮
function formatterOperate(value, row, index){
    console.log(row);
    return "&nbsp&nbsp<button onclick='editRow("+index+",1)' class='btn btn-default btn-sm'><span class='glyphicon glyphicon-pencil'></span>编辑</button>&nbsp"+
        "<button onclick='saveRow("+index+",1,"+row.id+")' class='btn btn-default btn-sm'><span class='glyphicon glyphicon-ok'></span>保存</button>&nbsp"+
        "<button onclick='delRow("+index+","+row.id+")' class='btn btn-default btn-sm'><span class='glyphicon glyphicon-remove'></span>删除</button>&nbsp"+
        "<button onclick='addRow("+index+","+row.id+",\""+row.name+"\")' class='btn btn-primary btn-sm' data-mark='1' data-toggle='modal' data-target='#ModalAdd'>添加子分类</button>&nbsp";
}
// 删除
function delRow(index,id){
    var r=confirm("你确定要删除吗？");
    if (r==true){
        $.ajax({
            type: 'post',
            url:'/categories/deleteCategories',
            data:{"id":id},
            dataType:'json',
            success:function(value){
                if(value.code==0){
                    alert("已成功删除");
                    window.location.reload();
                }
                else
                    alert("删除失败");
            },
            error:function(value){
                alert("删除失败");
            }
        });
    }
}
// 编辑
function editRow(index,mark){
    var str="";
    if(mark==1)
        str="#tableLevel12>tbody>tr:nth-child("+ (index+1) +") td.editable";
    else if(mark==2)
        str="#tableLevel12 table tr:nth-child("+ (index+1) +") td.editable";
    else
        str="#ModalLevel3 table tr:nth-child("+ (index+1) +") td.editable";
    $(str).each(function(){
        var value = $(this).text();
        $(this).html("<input value='"+value+"'>");
    });
}
// 保存修改
function saveRow(index,mark,id){
    var obj;
    var target="#tableLevel12";
    if(mark==1)
        obj = $("#tableLevel12 tr:nth-child("+ (index+1) +") td.editable");
    else if(mark==2)
        obj = $("#tableLevel12 table tr:nth-child("+ (index+1) +") td.editable");
    else{
        obj=$("#ModalLevel3 table tr:nth-child("+ (index+1) +") td.editable");
        target="#tableLevel3";
    }
    var name = obj.eq(0).find("input").val();
    var status = obj.eq(1).find("input").val();
    if(status!=0&&status!=1){
        alert("请输入0或1！\n0：未启用\n1：已启用");
        window.location.reload();
    }
    else {
        if (name != "undefined" && status != "undefined") {
            $(target).bootstrapTable('updateRow', {
                index: index,
                row: {
                    name: name,
                    status: status
                }
            });
            $.ajax({
                type: 'post',
                url: '/categories/updateCategories',
                data: {"id": id, "name": name, "status": status},
                dataType: 'json',
                success: function (value) {
                    if (value.code == 0) {
                        alert("修改成功");
                    }
                },
                error: function (value) {
                    alert("修改失败，请联系管理员。");
                }
            });
        }
    }
    obj.find("input").remove();
}
// 字表，显示二级分类
oInit.InitSubTable = function (index, row, $detail) {
    var parentid = row.MENU_ID;
    var cur_table = $detail.html('<table></table>').find('table');
    $.ajax({
        url:'/categories/GetCategories_l23',
        type : "post",
        data:{"id":row.id},
        dataType : "json",
        error:function(value){
            alert("ddd");
        },
        success : function(value) {
            $(cur_table).bootstrapTable({
                data:value.data,
                queryParams: { strParentID: parentid },
                ajaxOptions: { strParentID: parentid },
                pagination:true,
                showHeader:true,
                clickToSelect:true,
                singleSelect:true,
                cache:false,
                silent: true,
                uniqueId: "MENU_ID",
                columns: [
                    {
                        title: '展开',
                        formatter: function formatterOperate(value, row, index){
                            return "<button type'button' data-id='"+row.id+"' class='glyphicon glyphicon-th-list' data-toggle='modal' data-target='#ModalLevel3' style='border:0px;background-color: rgba(0,0,0,0);'></span>";
                        },
                        cellStyle:{
                            css:{"width":"20px","text-align":"right"}
                        }
                    },
                    {
                        field: 'id',
                        sortable:true,
                        title: '编号',
                    }, {
                        field: 'name',
                        sortable:true,
                        title: '名称',
                        class: 'editable'
                    }, {
                        field: 'status',
                        sortable:true,
                        title: '是否启用',
                        class: 'editable',
                        formatter:function(value,row,index){
                            if(value=='0')
                                return "未启用";
                            else
                                return "已启用";
                        }
                    },{
                        title: '操作',
                        field: 'operate',
                        cellStyle:{
                            css:{"width":"400px"}
                        },
                        formatter: function formatterOperate(value, row, index){
                            return "&nbsp&nbsp<button onclick='editRow("+index+",2)' class='btn btn-sm'><span class='glyphicon glyphicon-pencil'></span>编辑</button>&nbsp"+
                                "<button onclick='saveRow("+index+",2,"+row.id+")' class='btn btn-sm'><span class='glyphicon glyphicon-ok'></span>保存</button>&nbsp"+
                                "<button onclick='delRow("+index+","+row.id+")' class='btn btn-sm'><span class='glyphicon glyphicon-remove'></span>删除</button>&nbsp"+
                                "<button onclick='addRow("+index+","+row.id+",\""+row.name+"\")' class='btn btn-info btn-sm ' data-mark='2' data-toggle='modal' data-target='#ModalAdd' ><span class='glyphicon glyphicon-flash'></span>添加子分类</button>&nbsp";
                        },
                    }
                ],
                // 无线循环取子表，直到子表里面没有记录
                // onExpandRow: function(index, row, $detail) {
                // oInit.InitSubTable(index, row, $detail);
                // }
            });
        }
    });
};
// 添加新分类
function addCategories(){
    var formdata=$("#ModalAdd form").serialize();
    console.log(formdata);
    $.ajax({
        url:"/categories/insertCategories",
        type:"post",
        data:formdata,
        dataType:"json",
        success:function(data){
            alert("success");
            window.location.reload();
        },
        error:function(data){
            alert("error");
            window.location.reload();
        }
    });
}
