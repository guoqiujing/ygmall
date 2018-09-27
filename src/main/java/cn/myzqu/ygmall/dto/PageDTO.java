package cn.myzqu.ygmall.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页信息
 * Created by 的川 on 2018/5/8.
 */
@Data
public class PageDTO implements Serializable{

    private static final long serialVersionUID = 4295137897214180169L;
    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer Pages;

    /**
     * 当前页数，默认从第一页开始
     */
    private Integer pageNum;

    /**
     * 列表数据
     */
    private List<?> rows;

    /**
     * 构造方法
     * @param rows 列表数据
     * @param total 总记录数
     * @param pageSize 每页记录条数
     * @param pageNum 当前页数
     */
    public PageDTO(List<?> rows, int total, int pageSize, int pageNum) {
        this.rows = rows;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.Pages = (int)Math.ceil((double)total/pageSize);
    }

    /**
     * 构造方法
     * @param rows
     * @param total
     * @param pageSize
     * @param pageNum
     * @param Pages
     */
    public PageDTO(List<?> rows, int total, int pageSize, int pageNum, int Pages) {
        this.rows = rows;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.Pages = Pages;
    }

}
