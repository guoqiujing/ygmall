package cn.myzqu.ygmall.pojo;

import java.util.Date;

public class CustomerAddress {
    private String id;

    private String userId;

    private String receiverName;

    private String province;

    private String city;

    private String county;

    private String detail;

    private String receiverTel;

    private String postalCode;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel == null ? null : receiverTel.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public CustomerAddress(String id, String userId, String receiverName, String province, String city, String county, String detail, String receiverTel, String postalCode, Byte status) {
        this.id = id;
        this.userId = userId;
        this.receiverName = receiverName;
        this.province = province;
        this.city = city;
        this.county = county;
        this.detail = detail;
        this.receiverTel = receiverTel;
        this.postalCode = postalCode;
        this.status = status;
    }

    public CustomerAddress(String userId, String receiverName, String province, String city, String county, String detail, String receiverTel, String postalCode, Byte status) {
        this.userId = userId;
        this.receiverName = receiverName;
        this.province = province;
        this.city = city;
        this.county = county;
        this.detail = detail;
        this.receiverTel = receiverTel;
        this.postalCode = postalCode;
        this.status = status;
    }

    public CustomerAddress() {
    }
}