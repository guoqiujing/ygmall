package cn.myzqu.ygmall.pojo;

public class Brand {
    private Integer id;

    private String name;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public Brand(String name, Byte status) {
        this.name = name;
        this.status = status;
    }

    public Brand(Integer id, String name, Byte status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Brand() {
    }
}