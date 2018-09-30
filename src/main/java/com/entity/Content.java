package com.entity;

/**
 * 文章内容的实体
 */
public class Content {
    /**
     * @author jhc
     * @date 2018-09-29
     * 文章主键编号
    */
    private Integer cid;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章图片
     */
    private String titlePic;
    /**
     * 内容缩略图
     */
    private String slug;
    /**
     * 内容生成的时间
     */
    private Integer created;
    /**
     * 内容更改的时间
     */
    private Integer modified;
    /**
     * 文章的内容
     */
    private String content;
    /**
     * 文章所属用户的id
     */
    private Integer authorId;
    /**
     * 内容类别
     */
    private String type;
    /**
     * 内容状态
     */
    private String status;
    /**
     * 标签列表
     */
    private String tags;
    /**
     * 分类列表
     */
    private String categories;
    /**
     * 点击次数
     */
    private Integer hits;
    /**
     * 内容所属评论
     */
    private Integer commentsNum;
    /**
     * 是否允许评论
     */
    private Integer allowComment;
    /**
     * 是否允许Ping
     */
    private Integer allowedPing;
    /**
     * 允许出现在聚合类
     */
    private Integer allowFeed;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlePic() {
        return titlePic;
    }

    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public Integer getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
    }

    public Integer getAllowedPing() {
        return allowedPing;
    }

    public void setAllowedPing(Integer allowedPing) {
        this.allowedPing = allowedPing;
    }

    public Integer getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(Integer allowFeed) {
        this.allowFeed = allowFeed;
    }
}
