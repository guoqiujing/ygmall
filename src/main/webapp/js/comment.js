/**
 * Created by CC on 2018/9/26.
 */
//创建Vue实例
var page = new Vue({
    el: '#comment',
    data: {
        commentId: '',
        comment: {},
        reply: {},
        replyContent: '',
        addFlag: false,//追评标志
        /*replyFlag:true,*/
        replyConFlag: false,//回复标志
        conFlag: false,//回复追评标志
    },
    //创建vue实例之后的事件
    created: function () {
    },
    watch: {},
    methods: {
        //弹出模态框进行的操作
        select: function () {
            this.getComment();
            this.getReply();
        },
        //获取当前评论id
        getCommentId: function (id) {
            this.commentId = id;
        },
        //根据评论id获取评论以及追评
        getComment: function () {
            var that = this;
            $.ajax({
                type: "POST",
                url: "/comment/commentInfo",
                data: {
                    id: that.commentId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        console.log(msg.data);
                        that.comment = msg.data;
                        that.judgeadd();
                    }
                    else {
                        /*alert("查找失败");*/
                    }
                },
                error: function () {
                    alert("错误");
                }
            })
        },
        //判断是否有追评
        judgeadd: function () {
            var that = this;
            console.log(that.comment.additionalComment);
            if (that.comment.additionalComment != null) {
                that.addFlag = true;
            }
            else{
                that.addFlag = false;
            }
            console.log(that.addFlag);
        },
        //根据评论ID获取回复以及追评回复
        getReply: function () {
            var that = this;
            $.ajax({
                type: "POST",
                url: "/replyComment/replyInfo",
                data: {
                    id: that.commentId
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    console.log(msg)
                    if (msg.code == 0) {
                        console.log(that.replyConFlag);
                        console.log(msg.data);
                        that.reply = msg.data;
                        that.judgereply();
                    }
                    else {//商家没有进行回复
                        that.replyConFlag=false;
                        that.conFlag=false;
                    }
                },
                error: function () {
                    alert("错误");
                }
            })
        },
        //判断是否有回复以及追评回复
        judgereply: function () {
            var that = this;
            /*console.log(that.reply.content);*/
            if (that.reply.replyContent != null) {
                that.replyConFlag = true;
            }
            if (that.reply.content != null) {
                that.conFlag = true;
            }
        },
        //点击模态框中的回复
        replyCon: function () {
            var that = this;
            console.log(that.addFlag);
            console.log(that.replyConFlag);
            console.log(that.conFlag);
            if (!that.addFlag && !that.replyConFlag) {//客户评论的但是没有回复
                if (that.replyContent == "") {
                    layer.msg('请输入您的回复!', {time: 2000});
                }
                else {
                    that.addReply();
                }
            }
            else if (that.addFlag) {
                if (that.replyConFlag) {
                    if (that.conFlag) {//评论和追评都已回复
                        layer.msg('每条评论只能回复一次!', {time: 2000});
                    }
                    else {//回复了评论没回复追评
                        if (that.replyContent == "") {
                            layer.msg('请输入您的回复!', {time: 2000});
                        }
                        else{
                            that.updateContent();
                        }
                    }
                }
                else {
                    if(that.conFlag){//已回复追评
                        layer.msg('每条评论只能回复一次!', {time: 2000});
                    }
                    else {//评论和追评都没回复
                        if (that.replyContent == "") {
                            layer.msg('请输入您的回复!', {time: 2000});
                        }
                        else{
                            that.addContent();
                        }
                    }
                }
            }
            else {
                layer.msg('只能回复一次！!', {time: 2000});
            }
        },
        addReply: function () {//添加回复
            var that = this;
            $.ajax({
                type: "POST",
                url: "/replyComment/addReply",
                data: {
                    commentId: that.commentId,
                    replyContent: that.replyContent
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        console.log(msg.data);
                        that.updateStatus();
                    }
                    else {
                        alert("回复失败");
                    }
                },
                error: function () {
                    alert("回复评论错误");
                }
            });
        },
        clean: function () {
            this.replyContent = '';
        },
        addContent: function () {//添加追评回复
            var that = this;
            $.ajax({
                type: "POST",
                url: "/replyComment/addContent",
                data: {
                    commentId: that.commentId,
                    content: that.replyContent
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        console.log(msg.data);
                        that.updateStatus();
                    }
                    else {
                        alert("回复追评失败");
                    }
                },
                error: function () {
                    alert("添加追评错误");
                }
            });
        },
        updateContent: function () {
            var that = this;
            $.ajax({
                type: "POST",
                url: "/replyComment/updateContent",
                data: {
                    id: that.reply.id,
                    content: that.replyContent
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        console.log(msg.data);
                        that.updateStatus();
                    }
                    else {
                        alert("回复追评失败");
                    }
                },
                error: function () {
                    alert("追评错误");
                }
            });
        },
        updateStatus:function () {
            var that = this;
            $.ajax({
                type: "POST",
                url: "/comment/updateStatus",
                data: {
                    id: that.commentId,
                    commentStatus:1
                },
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (msg) {
                    if (msg.code == 0) {
                        console.log("成功");
                        console.log(msg.data);
                        window.location.href = "/page/admin/commentAdmin.html";
                    }
                    else {
                        alert("更改回复状态失败");
                    }
                },
                error: function () {
                    alert("更新状态错误");
                }
            });
        }
    },
})