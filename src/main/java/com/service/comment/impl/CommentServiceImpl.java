package com.service.comment.impl;

import com.constant.ErrorConstant;
import com.constant.WebConst;
import com.dao.CommentDao;
import com.dto.cond.CommentCond;
import com.entity.Comment;
import com.entity.Content;
import com.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.comment.CommentService;
import com.service.content.ContentService;
import com.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ContentService contentService;

    private static final Map<String,String> STATUS_MAP = new ConcurrentHashMap<>();

    private static final String STATUS_NORMAL = "approved";
    private static final String STATUS_BLANK = "not_audit";
    static{
        STATUS_MAP.put("approved",STATUS_NORMAL);
        STATUS_MAP.put("not_audit",STATUS_BLANK);
    }
    @Override
    public void addComment(Comment comment) {
        String msg = null;
        if(comment == null){
            msg = "评论对象为空";
        }
        if(StringUtils.isBlank(comment.getAuthor())){
            comment.setAuthor("热心网友");
        }
        if(StringUtils.isNotBlank(comment.getMail()) && !TaleUtils.isEmail(comment.getMail())){
            msg = "请输入正确的邮箱";
        }
        if(StringUtils.isBlank(comment.getContent())){
            msg = "评论内容不能为空";
        }
        if(comment.getContent().length()<5 || comment.getContent().length()>2000){
            msg = "评论字数在5-2000字之间";
        }
        if(comment.getCid()==null){
            msg = "评论文章不能为空";
        }
        if(msg!=null){
            throw BusinessException.withErrorCode(msg);
        }
        Content content = contentService.getArticleById(comment.getCid());
        if(content == null){
            throw BusinessException.withErrorCode("文章不存在");
        }
        comment.setOwnerId(content.getAuthorId());
        comment.setStatus(STATUS_MAP.get(STATUS_BLANK));
//        comment.setCreated(Syst);
        commentDao.addComment(comment);

        Content temp = new Content();
        temp.setCid(content.getCid());
        Integer count = content.getCommentsNum();
        if(count == null){
            count = 0;
        }
        temp.setCommentsNum(count+1);
        contentService.updateContentByCid(temp);
    }

    @Override
    public void deleteComment(Integer coid) {
        if( coid == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        CommentCond commentCond = new CommentCond();
        commentCond.setParent(coid);
        Comment comment = commentDao.getCommentById(coid);
        List<Comment> childComments = commentDao.getCommentsByCond(commentCond);
        Integer count = 0;
        if(childComments !=null && childComments.size()>0){
            for(Comment c1 : childComments){
                commentDao.deleteComment(c1.getCoid());
                count++;
            }
        }
        commentDao.deleteComment(coid);
        count++;

        Content content1 = contentService.getArticleById(comment.getCid());
        if(content1!=null && content1.getCommentsNum() !=null && content1.getCommentsNum()!=0){
            content1.setCommentsNum(content1.getCommentsNum()-count);
            contentService.updateContentByCid(content1);
        }

    }

    @Override
    public Comment getCommentById(Integer coid){
        if(coid == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
    }
    return commentDao.getCommentById(coid);
    }

    @Override
    public void updateComment(Integer coid, String status) {
        if(coid == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        commentDao.updateCommentStatus(coid,status);
    }

    @Override
    public List<Comment> getCommentsByCId(Integer cid) {

        if(cid==null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return commentDao.getCommentsByCId(cid);
    }

    @Override
    public PageInfo<Comment> getCommentsByCond(CommentCond commentCond, int pageNum, int pageSize) {
        if(commentCond == null) throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> comments = commentDao.getCommentsByCond(commentCond);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return pageInfo;

    }
}