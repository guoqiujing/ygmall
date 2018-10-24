package cn.myzqu.ygmall.vo;

import cn.myzqu.ygmall.pojo.Attribute;

import java.util.List;

/**
 * Created by Simon on 2018/9/21.
 */
public class CategoriesAttributeVO {
    private Integer categoryId;
    private String categoriesName;
    private String attribute0;
    private String attribute1;
    private String attribute2;
    private List<Attribute> attributes;

    public void setAttributes012(){
        if(attributes.size()>0)
            this.attribute0=attributes.get(0).getName();
        if(attributes.size()>1)
            this.attribute1=attributes.get(1).getName();
        if(attributes.size()>2)
            this.attribute2=attributes.get(2).getName();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getAttribute0() {
        return attribute0;
    }

    public void setAttribute0(String attribute0) {
        this.attribute0 = attribute0;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }
}
