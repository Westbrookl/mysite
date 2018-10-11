package com.dao;

import com.dto.ArchiveDto;
import com.dto.cond.ContentCond;
import com.entity.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jhc
 * @date 2018-09-29
 *
 *
 * 对于归档数据的内容不是很了解 没有继续写下去
 * 新学到的是关于模糊查询的内容
 文章的持久层
*/
@Mapper
/**
 * @Param
 */
public interface ContentDao {
    /**
     * 添加文章
     * @param content
     * @return
     */
    int addArticle(Content content);

    /**
     * 按照主键删除文章
     * @param cid
     * @return
     */
    int deleteArticleById(@Param("cid")Integer cid);

    /**
     * 根据文章Id更新文章内容
     * @param content
     * @return
     */
    int updateArticleById(Content content);

    /**
     * 更新文章的评论数
     * @param cid
     * @param commentsNUm
     * @return
     */
    int updateArticleCommentCountById(@Param("cid")Integer cid,@Param("commnetsNum")Integer commentsNUm);

    /**
     * 根据编号获取文章
     * @param cid
     * @return
     */
    Content getArticleById(@Param("cid")Integer cid);

    /**
     * 根据条件获取文章列表
     * @param contentCond
     * @return
     */
    List<Content> getArticlesByCond(ContentCond contentCond);

    /**
     * 统计文章总数
     * @return
     */
    Long getArticleCount();

    /**
     * 获取归档数据
     * @param contentCond
     * @return
     */
//    List<ArchiveDto> getArchive(ContentCond contentCond);

    /**
     * 获取最近文章
     * @return
     */
    List<Content> getRecentlyArticle();

    /**
     *
     * 搜索文章-根据标题
     * @param param
     * @return
     */
    List<Content> searchArticle(@Param("param")String param);
}
