package com.service.comment;

import com.dto.cond.CommentCond;
import com.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author jhc
 * @date 2018-10-24
 
 

*/
public interface CommentService{
    /**
     * 增加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 删除评论
     * @param coid
     */
    void deleteComment(Integer coid);

    /**
     * 通过id获取评论
     * @param coid
     * @return
     */
    Comment getCommentById(Integer coid);

    /**
     * 更新评论
     * @param coid
     * @param status
     */
    void updateComment(Integer coid,String status);

    /**
     * 通过文章主键获取评论
     * @param cid
     * @return
     */
    List<Comment> getCommentsByCId(Integer cid);

    /**
     * 通过条件获取评论
     * @param commentCond
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Comment> getCommentsByCond(CommentCond commentCond,int pageNum,int pageSize);
}
