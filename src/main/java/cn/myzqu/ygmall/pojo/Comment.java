package cn.myzqu.ygmall.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Comment {
    private String id;

    private String goodsId;

    private String orderId;

    private String userId;

    private String userName;

    private String userImg;

    private Byte goodsScore;

    private Byte serviceScore;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String comment;

    private String commentImg;

    private String additionalComment;

    private String additionalCommentImg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date additionalCommentTime;

    private String formatAndStyle;

    private Byte display;

    private Byte commentStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }

    public Byte getGoodsScore() {
        return goodsScore;
    }

    public void setGoodsScore(Byte goodsScore) {
        this.goodsScore = goodsScore;
    }

    public Byte getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(Byte serviceScore) {
        this.serviceScore = serviceScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCommentImg() {
        return commentImg;
    }

    public void setCommentImg(String commentImg) {
        this.commentImg = commentImg == null ? null : commentImg.trim();
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment == null ? null : additionalComment.trim();
    }

    public String getAdditionalCommentImg() {
        return additionalCommentImg;
    }

    public void setAdditionalCommentImg(String additionalCommentImg) {
        this.additionalCommentImg = additionalCommentImg == null ? null : additionalCommentImg.trim();
    }

    public Date getAdditionalCommentTime() {
        return additionalCommentTime;
    }

    public void setAdditionalCommentTime(Date additionalCommentTime) {
        this.additionalCommentTime = additionalCommentTime;
    }

    public String getFormatAndStyle() {
        return formatAndStyle;
    }

    public void setFormatAndStyle(String formatAndStyle) {
        this.formatAndStyle = formatAndStyle == null ? null : formatAndStyle.trim();
    }

    public Byte getDisplay() {
        return display;
    }

    public void setDisplay(Byte display) {
        this.display = display;
    }

    public Byte getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Byte commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Comment() {
    }

    public Comment(String id, Byte display) {
        this.id = id;
        this.display = display;

    }

    public Comment(Byte goodsScore, Byte commentStatus) {
        this.goodsScore = goodsScore;
        this.commentStatus = commentStatus;
    }

    public Comment(String id, String additionalComment, String additionalCommentImg, Byte commentStatus) {
        this.id = id;
        this.additionalComment = additionalComment;
        this.additionalCommentImg = additionalCommentImg;
        this.commentStatus = commentStatus;
    }
}