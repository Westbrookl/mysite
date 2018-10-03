package com.dto.cond;

/**
 * @author jhc on 2018/10/3
 */
public class MetaCond {
    /**
     * 标签的名字作为筛选条件
     */
    private String name;

    /**
     * 标签的类型作为筛选条件
     */

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
