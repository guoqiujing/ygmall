package cn.myzqu.ygmall.pojo;

import java.util.Date;

public class ReplyComment {
    private String id;

    private String replyId;

    private String replyName;

    private String replyContent;

    private Date replyTime;

    private String content;

    private Date creatTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId == null ? null : replyId.trim();
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName == null ? null : replyName.trim();
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public ReplyComment() {
    }

    public ReplyComment(String id, String replyId, String replyContent) {
        this.id = id;
        this.replyId = replyId;
        this.replyContent = replyContent;
    }

    public ReplyComment(String id, String replyId, String content, Date creatTime) {
        this.id = id;
        this.replyId = replyId;
        this.content = content;
        this.creatTime = creatTime;
    }

    public ReplyComment(String id, String content) {
        this.id = id;
        this.content = content;
    }
}