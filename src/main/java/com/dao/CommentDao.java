package com.dao;

import com.dto.cond.CommentCond;
import com.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    /**
     * 增加评论
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 根据评论的主键删除评论
     * @param coid
     * @return
     */
    int deleteComment(@Param("coid") Integer coid);

    /**
     * 更新评论的状态
     * @param coid
     * @param status
     * @return
     */
    int updateCommentStatus(@Param("coid")Integer coid,@Param("status")String status);

    /**
     * 获取单条评论
     * @param coid
     * @return
     */
    Comment getCommentById(@Param("coid")Integer coid);

    /**
     * 根据文章编号获取所有评论
     * @param cid
     * @return
     */
    List<Comment> getCommentByCId(@Param("cid")Integer cid);

    /**
     * 根据条件获取评论列表
     * @param commentCond
     * @return
     */
    List<Comment> getCommentByCond(CommentCond commentCond);

    /**
     * 得到评论总数
     * @return
     */
    Long getCommentsCount();

}
