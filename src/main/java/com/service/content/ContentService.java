package com.service.content;

import com.dto.cond.ContentCond;
import com.entity.Content;
import com.github.pagehelper.PageInfo;
/**
 *文章服务层
 */
public interface ContentService {
    /**
     * 添加文章
     * @param content
     */
    void addArticle(Content content);

    /**
     * 根据文章编号删除文章
     * @param cid
     */
    void deleteArticleById(Integer cid);

    /**
     * 更新文章
     * @param content
     */
    void updateArticleById(Content content);

    /**
     * 更新文章的分类
     * @param original
     * @param newCategory
     */
    void updateCategory(String original,String newCategory);

    /**
     * 添加文章点击量
     * @param content
     */
        void updateContentByCid(Content content);

    /**
     * 根据文章编号获取文章
     * @param cid
     * @return
     */
    Content getArticleById(Integer cid);

    /**
     * 根据条件获取文章列表
     * @param contentCond
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Content> getArticlesByCond(ContentCond contentCond,int pageNum,int pageSize);

    /**
     * 获取最近的文章
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Content> getRecentlyArticle(int pageNum,int pageSize);

    /**
     * 搜索文章
     * @param param
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Content> searchArticle(String param,int pageNum,int pageSize);
}
