package com.entity;

import java.io.Serializable;

/**
 * @author jhc on 2018/10/8
 */
public class Log implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 产生的行为
     */
    private String action;
    /**
     * 产生的数据
     */
    private String data;
    /**
     * 发生人的ID
     */
    private Integer authorId;
    /**
     * 日志产生的IP
     */
    private String ip;
    /**
     * 创建的时间
     */
    private Integer created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
    public static long getSerialVersionUID(){
        return serialVersionUID;
    }
}
