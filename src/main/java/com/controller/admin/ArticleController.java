package com.controller.admin;

import com.constant.Types;
import com.dto.cond.ContentCond;
import com.entity.Content;
import com.github.pagehelper.PageInfo;
import com.service.Log.LogService;
import com.service.content.ContentService;
import com.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @RequestParam(name="",defaultValue="")
 * 虽然在defaultValue中的内容是String类型的数据，String数据会根据其里面的内容直接自动转换成为我们需要的内容。
 *
 * @author jhc on 2018/10/11
 */
@Api("文章内容管理")
@Controller
@RequestMapping(value = "/admin/article")
public class ArticleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ContentService contentService;

    @Autowired
    private LogService logService;

    @ApiOperation("文章显示页面")
    @GetMapping(value="")
    public String index(
                        HttpServletRequest request,
                        @ApiParam(name="pageNum",value="页的数目",required = false)
                        @RequestParam(name="pageNum",defaultValue = "1")
                        int pageNum,
                        @ApiParam(name="pageSize",value="每页的数量",required = false)
                        @RequestParam(name="pageSize",defaultValue = "15")
                        int pageSize
                        ){
        ContentCond contentCond = new ContentCond();

        PageInfo<Content> articles = contentService.getArticlesByCond(contentCond,pageNum,pageSize);
        request.setAttribute("articles",articles);
        return "admin/article_list";
    }

    @ApiOperation("发布文章的页面")
    @GetMapping("/publish")
    public String publish(HttpServletRequest request){
        return "admin/article_edit";
    }


    @ApiOperation("发布文章")
    @PostMapping("/publish")
    @ResponseBody
    public APIResponse publishArticle(@ApiParam(name="title",value="标题",required=true)
                                      @RequestParam(name="title",required = true)
                                      String title,
                                               @ApiParam(name="titlePic",value="标题图片",required=false)
                                      @RequestParam(name="titlePic",required = false)
                                      String titlePic,
                                               @ApiParam(name="slug",value="缩略名",required=false)
                                      @RequestParam(name="slug",required = false)
                                      String slug,
                                               @ApiParam(name="content",value="内容",required=true)
                                      @RequestParam(name="content",required = true)
                                      String content,
                                               @ApiParam(name="type",value="文章类型",required=true)
                                      @RequestParam(name="type",required = true)
                                      String type,
                                               @ApiParam(name="status",value="文章状态",required = true)
                                      @RequestParam(name="status",required = true)
                                      String status,
                                               @ApiParam(name="tags",value="标签",required = false)
                                      @RequestParam(name="tags",required = false)
                                      String tags,
                                               @ApiParam(name="categories",value = "分类",required = false)
                                      @RequestParam(name="categories",required = false,defaultValue = "默认分类")
                                      String categories,
                                               @ApiParam(name="allowComment",value="是否允许评论",required = true)
                                      @RequestParam(name="allowComment",required = true)
                                      Boolean allowComment
                                      ){
        Content article = new Content();
        article.setTitle(title);
        article.setTitlePic(titlePic);
        article.setSlug(slug);
        article.setContent(content);
        article.setType(type);
        article.setStatus(status);
        article.setTags(tags.equals(Types.ARTICLE.getType())?tags:null);
        /**
         * 不太懂
         * 只允许博客文章有分类，防止作品被收入分类？
         */
        article.setCategories(categories.equals(Types.ARTICLE.getType())?categories:null);
        article.setAllowComment(allowComment?1:0);
        try{
            contentService.addArticle(article);
        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }



}
