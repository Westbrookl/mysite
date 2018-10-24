package com.dao;
/**
 * @author jhc
 * @date 2018-10-24
*/
import com.dto.cond.CommentCond;
import com.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao{
    /**
     * 增加评论
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 根据评论Id删除评论
     * @param coid
     * @return
     */
   int deleteComment(@Param("coid")Integer coid);

    /**
     * 更新评论状态
     * @param coid
     * @param status
     * @return
     */
   int updateCommentStatus(@Param("coid")Integer coid,@Param("status")String status);

    /**
     * 根据评论主键获得评论
     * @param coid
     * @return
     */
   Comment getCommentById(@Param("coid") Integer coid);

    /**
     * 根据评论条件获得评论
     * @param commentCond
     * @return
     */
   List<Comment> getCommentsByCond(CommentCond commentCond);

    /**
     * 根据文章主键获得文章所有评论
     * @param cid
     * @return
     */
   List<Comment> getCommentsByCId(@Param("cid")Integer cid);

    /**
     * 获取评论的所有内容
     * @return
     */
   Long getCommentsCount();
}
