//package com.service.content.Impl;
//
//import com.constant.ErrorConstant;
//import com.constant.Types;
//import com.constant.WebConst;
//import com.dao.CommentDao;
//import com.dao.ContentDao;
//import com.dto.cond.ContentCond;
//import com.entity.Comment;
//import com.entity.Content;
//import com.exception.BusinessException;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.service.content.ContentService;
//import com.service.meta.MetaService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * @author jhc on 2018/9/29
// */
////@Service
//public class ContentServiceImpl implements ContentService {
//    @Autowired
//    private ContentDao contentDao;
//
//    @Autowired
//    private CommentDao commentDao;
//
//    @Autowired
//    private MetaService metaService;
//
//    @Autowired
//    private RelationShipDao relationShipDao;
////    @Transactional
//    @Override
////    @CacheEvict()
//    public void addArticle(Content content){
//        if(content == null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        if(content.getTitle().trim().equals("")||content.getTitle() == null){
//            throw  BusinessException.withErrorCode(ErrorConstant.Article.TITLE_CAN_NOT_EMPTY);
//        }
//        if(content.getTitle().length()> WebConst.MAX_TITLE_COUNT){
//            throw  BusinessException.withErrorCode(ErrorConstant.Article.TITLE_IS_TOO_LONG);
//        }
//        if(StringUtils.isBlank(content.getContent())){
//            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_CAN_NOT_EMPTY);
//        }
//        if(content.getContent().length()>WebConst.MAX_TEXT_COUNT){
//            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_IS_TOO_LONG);
//        }
//        //标签和分类
//        String tags = content.getTags();
//        String categories = content.getCategories();
//
//        contentDao.addArticle(content);
//
//        int cid = content.getCid();
//        metaService.addMetas(cid,tags,Types.TAG.getType());
//        metaService.addMetas(cid,categories, Types.CATEGORY.getType());
//    }
//    @Override
////    @CacheEvict
////    @Transactional
//    public void updateCategory(String original,String newCategory){
//        ContentCond cond = new ContentCond();
//        cond.setCategory(original);
//        List<Content> articles = contentDao.getArticlesByCond(cond);
//        for(Content c:articles){
//            c.setCategories(c.getCategories().replace(original,newCategory));
//            contentDao.updateArticleById(c);
//        }
//    }
//    @Override
////    @Transactional
////    @CacheEvict
//    public void deleteArticleById(Integer cid){
//        if(cid == null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        contentDao.deleteArticleById(cid);
//        //删除对应的评论
//        List<Comment> comments = commentDao.getCommentByCId(cid);
//        if(comments!=null && comments.size()>0){
//            for(Comment c:comments){
//                commentDao.deleteComment(c.getCoid());
//            }
//        }
//    }
//    @Override
////    @Transactional
////    @CacheEvict
//    public void updateArticleById(Content content){
//        String tags = content.getTags();
//        String categories = content.getCategories();
//
//        contentDao.updateArticleById(content);
//        int cid = content.getCid();
//        relationShipDao.deleteRelationShipByCid(cid);
//        metaService.addMeatas(cid,tags,Types.TAG.getType());
//        metaService.addMetas(cid,categories,Types.CATEGORY.getType());
//    }
//    @Override
////    @Transactional
////    @CacheEvict
//    public void updateContentByCid(Content content){
//        if(content!=null&& content.getCid()!=null){
//            contentDao.updateArticleById(content);
//        }
//    }
//    @Override
////    @Transactional
////    @CacheEvict
//    public Content getArticleById(Integer cid){
//        if(cid == null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        return contentDao.getArticleById(cid);
//    }
//    @Override
////    @Cacheable
//    public PageInfo<Content> getArticlesByCond(ContentCond contentCond, int pageNum,int pageSize){
//      if(contentCond == null){
//          throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//      }
//      PageHelper.startPage(pageNum,pageSize);
//      List<Content>  contents = contentDao.getArticlesByCond(contentCond);
//      PageInfo<Content> pageInfo = new PageInfo<>(contents);
//      return pageInfo;
//    }
//    @Override
////    @Cacheable
//    public PageInfo<Content> getRecentlyArticle(int pageNum, int pageSize){
//        PageHelper.startPage(pageNum,pageSize);
//        List<Content> recentlyArticle = contentDao.getRecentlyArticle();
//        PageInfo<Content> pageInfo = new PageInfo<>(recentlyArticle);
//        return pageInfo;
//    }
//    @Override
//    public PageInfo<Content> searchArticle(String param,int pageNum,int pageSize){
//        PageHelper.startPage(pageNum,pageSize);
//        List<Content> content = contentDao.searchArticle(param);
//        PageInfo<Content> pageInfo = new PageInfo<>(content);
//        return pageInfo;
//    }
//
//}
