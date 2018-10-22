package cn.myzqu.ygmall.pojo;

import java.util.Date;

public class AfterSaleAlter {
    private String id;

    private String afterSaleId;

    private Byte state;

    private String operator;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAfterSaleId() {
        return afterSaleId;
    }

    public void setAfterSaleId(String afterSaleId) {
        this.afterSaleId = afterSaleId == null ? null : afterSaleId.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public AfterSaleAlter() {
    }

    public AfterSaleAlter(String id, String afterSaleId, Byte state, String operator) {
        this.id = id;
        this.afterSaleId = afterSaleId;
        this.state = state;
        this.operator = operator;
    }

    public AfterSaleAlter(String id, String afterSaleId, Byte state) {
        this.id = id;
        this.afterSaleId = afterSaleId;
        this.state = state;
    }
}