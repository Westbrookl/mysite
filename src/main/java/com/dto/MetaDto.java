package com.dto;

import com.entity.Meta;

/**
 * 标签分类列表
 * @author jhc on 2018/10/3
 */
public class MetaDto extends Meta {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
