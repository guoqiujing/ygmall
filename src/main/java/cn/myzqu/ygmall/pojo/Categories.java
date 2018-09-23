package cn.myzqu.ygmall.pojo;

public class Categories {
    private Integer id;

    private String name;

    private Integer parentLevel;

    private Integer grandLevel;

    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentLevel() {
        return parentLevel;
    }

    public void setParentLevel(Integer parentLevel) {
        this.parentLevel = parentLevel;
    }

    public Integer getGrandLevel() {
        return grandLevel;
    }

    public void setGrandLevel(Integer grandLevel) {
        this.grandLevel = grandLevel;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentLevel=" + parentLevel +
                ", grandLevel=" + grandLevel +
                ", status=" + status +
                '}';
    }
}