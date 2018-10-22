/**
 * Created by CC on 2018/10/15.
 */

//获取存在session的用户信息
var userId = $.myPlugin.getUserId();

//创建Vue实例
var after = new Vue({
    el: '#audit',
    data: {
        auditId: '',
        audit: {},
        userId:userId,//从session获取的用户id
        /*user:{},*/
        operator:'mavis',
        state:'',
    },
    methods: {
        //获取当前申请售后id
        getAuditId: function (id) {
            this.auditId = id;
        },
        //根据售后id获取当前售后详情
        getAfterSale: function () {
            var that = this;
            $.ajax({
                type: "GET",
                url: "/afterSale/info/" + that.auditId,
                dataType: "json",
                contentType: 'application/json;charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("查找成功");
                        console.log(msg.data);
                        that.audit = msg.data;
                    }
                    else {
                        console.log("查找失败");
                    }
                },
                error: function () {
                    console.log("错误");
                }
            });
        },
        agreeRefund: function () {//商家审核通过，同意退款
            var that = this;
            $.ajax({
                type: "POST",
                url: "/afterSale/updateStatus",
                data: {
                    id: that.auditId,
                    status: 0
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        that.state=0;
                        that.addAfterSaleAlter();
                    }
                    else {
                        alert("同意退款失败");
                    }
                },
                error: function () {
                    alert("同意退款错误");
                }
            })
        },
        refuse: function () {//商家审核拒绝退款
            var that = this;
            $.ajax({
                type: "POST",
                url: "/afterSale/updateStatus",
                data: {
                    id: that.auditId,
                    status: 1
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        console.log(msg.data);
                        that.state=1;
                        that.addAfterSaleAlter();
                    }
                    else {
                        alert("拒绝退款失败");
                    }
                },
                error: function () {
                    alert("拒绝退款错误");
                }
            })
        },
        addAfterSaleAlter:function () {//向售后变更表添加数据
            var that = this;
            $.ajax({
                type: "POST",
                url: "/afterSaleAlter/addAlter",
                data: {
                    afterSaleId: that.auditId,
                    state: that.state,
                    operator:that.userId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        if(that.state==0){
                            that.addRefund();
                        }
                        else if(that.state==1){
                            window.location.href = "/page/admin/auditAdmin.html";
                        }
                    }
                    else {
                        alert("添加售后变更状态失败");
                    }
                },
                error: function () {
                    alert("添加售后变更状态错误");
                }
            });
        },
        addRefund:function () {//商家同意退款后想退款表添加数据
            var that = this;
            $.ajax({
                type: "POST",
                url: "/refund/addRefund",
                data: {
                    serial:that.auditId,
                    step:0,
                    operator:that.userId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        window.location.href = "/page/admin/auditAdmin.html";
                    }
                    else {
                        alert("添加退款失败");
                    }
                },
                error: function () {
                    alert("添加退款错误");
                }
            });
        }
    }
})