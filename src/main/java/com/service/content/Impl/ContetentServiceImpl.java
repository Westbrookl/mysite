package com.service.content.Impl;

import com.constant.ErrorConstant;
import com.constant.WebConst;
import com.dao.ContentDao;
import com.dto.cond.ContentCond;
import com.entity.Content;
import com.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.content.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 服务层只是对服务的逻辑进行处理并不关系其数据层里面的具体的操作
 * @author jhc on 2018/10/11
 */
@Service
public class ContetentServiceImpl implements ContentService {
    @Autowired
    private ContentDao contentDao;

    @Override
    public void addArticle(Content content) {
        if(content == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        if(StringUtils.isBlank(content.getTitle())){
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_CAN_NOT_EMPTY);
        }
        if(content.getTitle().length()>WebConst.MAX_TITLE_COUNT){
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_IS_TOO_LONG);
        }
        if(StringUtils.isBlank(content.getContent())){
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_CAN_NOT_EMPTY);
        }
        if(content.getContent().length()>WebConst.MAX_TEXT_COUNT){
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_IS_TOO_LONG);
        }
        /**
         * 增加内容，标签服务尚未完成。
         */
        String category = content.getCategories();
        String meta = content.getTags();
        contentDao.addArticle(content);
    }

    @Override
    public void deleteArticleById(Integer cid) {
        if(cid == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        contentDao.deleteArticleById(cid);
        /**
         * 删除标签下面的评论并没有完成
         */
    }

    /**
     * 更新文章内容
     * @param content
     */
    @Override
    public void updateArticleById(Content content) {
        if(content == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        contentDao.updateArticleById(content);
    }

    @Override
    public void updateCategory(String original, String newCategory) {
        /**
         * 具体的做法是把原来标签内容下的文章全部取出来，然后把文章的标签全部换成新标签并且更新
         */
        ContentCond contentCond = new ContentCond();
        contentCond.setCategory(original);
        List<Content> contents = contentDao.getArticlesByCond(contentCond);
        for(Content content:contents){
            content.setCategories(newCategory);
            contentDao.updateArticleById(content);
        }
    }

    @Override
    public void updateContentByCid(Content content) {

    }

    @Override
    public Content getArticleById(Integer cid) {
        if(cid == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        Content content = contentDao.getArticleById(cid);
        return content;
    }

    @Override
    public PageInfo<Content> getArticlesByCond(ContentCond contentCond, int pageNum, int pageSize) {
        if(contentCond == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Content> contents = contentDao.getArticlesByCond(contentCond);
        PageInfo<Content> articles = new PageInfo<>(contents);
        return articles;
    }

    @Override
    public PageInfo<Content> getRecentlyArticle(int pageNum, int pageSize) {
       PageHelper.startPage(pageNum,pageSize);
       List<Content> contents = contentDao.getRecentlyArticle();
       PageInfo<Content> article = new PageInfo<>(contents);
       return article;
    }

    @Override
    public PageInfo<Content> searchArticle(String param, int pageNum, int pageSize) {
        if(param == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Content> article = contentDao.searchArticle(param);
        PageInfo<Content> content = new PageInfo<>(article);
        return content;
    }
}
