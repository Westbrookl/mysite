package com.dto;

import com.entity.Content;

import java.util.List;

/**
 * 文章归档类
 * @author jhc on 2018/9/29
 */
public class ArchiveDto {
    private String date;
    private String count;
    private List<Content> articles;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Content> getArticles() {
        return articles;
    }

    public void setArticles(List<Content> articles) {
        this.articles = articles;
    }
}
