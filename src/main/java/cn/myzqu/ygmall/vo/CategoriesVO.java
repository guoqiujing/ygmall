package cn.myzqu.ygmall.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Simon on 2018/9/22.
 */
public class CategoriesVO {
    private HashMap<Integer,String> name_map;
    private HashMap<Integer,HashSet> l12_map;
    private HashMap<Integer,HashSet> l23_map;

    public HashMap<Integer, String> getName_map() {
        return name_map;
    }

    public void setName_map(HashMap<Integer, String> name_map) {
        this.name_map = name_map;
    }

    public HashMap<Integer, HashSet> getL12_map() {
        return l12_map;
    }

    public void setL12_map(HashMap<Integer, HashSet> l12_map) {
        this.l12_map = l12_map;
    }

    public HashMap<Integer, HashSet> getL23_map() {
        return l23_map;
    }

    public void setL23_map(HashMap<Integer, HashSet> l23_map) {
        this.l23_map = l23_map;
    }
}
