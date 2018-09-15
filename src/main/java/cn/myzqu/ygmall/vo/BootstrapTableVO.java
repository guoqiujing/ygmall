package cn.myzqu.ygmall.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by 小奇冰 on 2018/9/15.
 */
@Data
public class BootstrapTableVO {

    private int total;

    private List<?> rows;

    public BootstrapTableVO(int total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public BootstrapTableVO() {
    }

    @Override
    public String toString() {
        return "BootstrapTableVO{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
