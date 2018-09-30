package com.service.comment;


import com.dto.cond.CommentCond;
import com.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
  * @author jhc
  * @date 2018-09-30
    评论服务层


 */
public interface CommentService {
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
     * 更新评论状态
     * @param coid
     * @param status
     */
    void updateCommentStatus(Integer coid,String status);
    /**
     * 通过评论主键得到评论
     */
    Comment getCommentById(Integer coid);

    /**
     * 根据文章编号获取评论列表
     * @param cid
     * @return
     */
    List<Comment> getCommentByCId(Integer cid);

    /**
     * 根据条件获取评论列表
     * @param commentCond
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Comment> getCommentByCond(CommentCond commentCond,int pageNum,int pageSize);
}
