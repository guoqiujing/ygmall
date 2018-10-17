package cn.myzqu.ygmall.pojo;

import java.util.Date;

public class Refund {
    private String id;

    private String serial;

    private Integer step;

    private String operator;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial == null ? null : serial.trim();
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
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

    public Refund() {
    }

    public Refund(String id, String serial, Integer step, String operator) {
        this.id = id;
        this.serial = serial;
        this.step = step;
        this.operator = operator;
    }

    public Refund(String id, Integer step) {
        this.id = id;
        this.step = step;
    }
}