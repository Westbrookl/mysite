//package com.service.comment.impl;
//
//import com.constant.ErrorConstant;
//import com.dao.CommentDao;
//import com.dto.cond.CommentCond;
//import com.entity.Comment;
//import com.entity.Content;
//import com.exception.BusinessException;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.service.comment.CommentService;
//import com.service.content.ContentService;
//import com.utils.DateKit;
//import com.utils.TaleUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.apache.commons.lang3.StringUtils;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author jhc on 2018/9/30
// */
//@Service
//public class CommentServiceImpl implements CommentService {
//    @Autowired
//    private CommentDao commentDao;
//
//    @Autowired
//    private ContentService contentService;
//
//    private static final Map<String,String> STATUS_MAP = new HashMap<>();
//
//    private static final String STATUS_NORMAL = "approved";
//
//    private static final String STATUS_BLANK = "not_audit";
//    static{
//        STATUS_MAP.put("approved",STATUS_NORMAL);
//        STATUS_MAP.put("not_audit",STATUS_BLANK);
//    }
//    @Override
//    /**
//     * 添加评论的时候需要注意的问题
//     * 1 首先对评论人员的检查以及对评论信息的检查看看是不是符合我们评论的要求
//     *      如果不符合抛出异常否则就继续往下走
//     * 2 找到评论属于的文章如果没有抛出异常否则继续
//     * 3 设置comment所属的文章的ID并且设置评论的状态和评论创建的时间
//     * 4 更新原文章的评论的数目
//     *
//     */
////    @Transactional
////    @CacheEvict
//    public void addComment(Comment comment) {
//        String msg = null;
//        if(comment == null){
//            msg = "评论不能为空";
//        }
//        if(StringUtils.isBlank(comment.getAuthor())){
//            comment.setAuthor("热心网友");
//        }
//        if(StringUtils.isNotBlank(comment.getMail()) && !TaleUtils.isEmail(comment.getMail())){
//            msg = "请输入正确的邮箱格式";
//        }
//        if(StringUtils.isBlank(comment.getContent())){
//            msg= "评论内容不能为空";
//        }
//        if(comment.getContent().length()<5 || comment.getContent().length()>2000){
//            msg = "评论字数在5-2000字符之间";
//        }
//        if(comment.getCid() == null){
//            msg="评论文章不能为空";
//        }
//        if(msg != null){
//            throw  BusinessException.withErrorCode(msg);
//        }
//        Content article = contentService.getArticleById(comment.getCid());
//        if(article == null){
//            throw BusinessException.withErrorCode("该文章不存在");
//        }
//        comment.setOwnerId(article.getAuthorId());
//        comment.setStatus(STATUS_MAP.get(STATUS_NORMAL));
//        comment.setCreated(DateKit.getCurrentUnixTime());
//        commentDao.addComment(comment);
//
//        Content temp = new Content();
//        temp.setCid(article.getCid());
//        Integer count = article.getCommentsNum();
//        if(count == null){
//            count = 0;
//        }
//        temp.setCommentsNum(count+1);
//        contentService.updateContentByCid(temp);
//    }
//
////    @Transactional
////    @CacheEvict
//    @Override
//    public void deleteComment(Integer coid) {
//        if(coid == null){
//            throw  BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        //如果有子评论一律删除
//        //查找是否有子评论
//        CommentCond commentCond = new CommentCond();
//        commentCond.setParent(coid);
//        Comment comment = commentDao.getCommentById(coid);
//        List<Comment> childComments = commentDao.getCommentByCond(commentCond);
//        Integer count = 0;
//        if(childComments!=null && childComments.size()>0){
//            for(Comment com : childComments){
//                commentDao.deleteComment(com.getCoid());
//                count++;
//            }
//        }
//        //删除当前评论
//        commentDao.deleteComment(coid);
//        count++;
//
//        //更新当前文章的评论数
//        Content content = contentService.getArticleById(comment.getCid());
//        if(content!=null && content.getCommentsNum()!=null && content.getCommentsNum() != 0){
//            content.setCommentsNum(content.getCommentsNum()-count);
//            contentService.updateContentByCid(content);
//        }
//    }
//
//
////    @CacheEvict
//    @Override
//    public void updateCommentStatus(Integer coid, String status) {
//        if(coid == null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        commentDao.updateCommentStatus(coid,status);
//    }
//
////    @CacheEvict
//    @Override
//    public Comment getCommentById(Integer coid) {
//        if(coid  == null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        return commentDao.getCommentById(coid);
//    }
////    @Cacheable
//    @Override
//    public List<Comment> getCommentByCId(Integer cid) {
//        if(cid == null)
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        return commentDao.getCommentByCId(cid);
//    }
//
//    @Override
//    public PageInfo<Comment> getCommentByCond(CommentCond commentCond, int pageNum, int pageSize) {
//        if(commentCond == null )
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        PageHelper.startPage(pageNum,pageSize);
//        List<Comment> comments = commentDao.getCommentByCond(commentCond);
//        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
//        return pageInfo;
//    }
//}
