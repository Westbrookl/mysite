package com.entity;

import java.io.Serializable;

/**
 * @author jhc on 2018/10/3
 */
public class Meta implements Serializable {
    private static final long SerialVersionUID = 1L;
    /**
     * 项目主键
     */
    private Integer mid;
    /**
     * 名称
     */
    private String name;
    /**
     * 缩略名
     */
    private String slug;
    /**
     * 标签类型
     */
    private String type;
    /**
     * 文章类型
     */
    private String contentType;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父标签
     */
    private Integer parent;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
}
