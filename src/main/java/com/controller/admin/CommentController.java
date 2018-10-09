//package com.controller.admin;
//
//import com.constant.ErrorConstant;
//import com.controller.BaseController;
//import com.dto.cond.CommentCond;
//import com.entity.Comment;
//import com.entity.User;
//import com.exception.BusinessException;
//import com.github.pagehelper.PageInfo;
//import com.service.comment.CommentService;
//import com.utils.APIResponse;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 评论控制器
// * @author jhc on 2018/9/30
// */
//@Api("评论相关接口")
//@Controller
//@RequestMapping("/admin/comments")
//public class CommentController extends BaseController {
//    public static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
//
//    @Autowired
//    private CommentService commentService;
//
//    @ApiOperation("进入评论列表")
//    @GetMapping("")
//    public String index(
//            @ApiParam(name="page",value="页数",required = false)
//            @RequestParam(name="page" ,required = false,defaultValue = "1")
//            int page,
//            @ApiParam(name = "limit",value="每页条数",required=false)
//            @RequestParam(name="limit",required=false,defaultValue="15")
//            int limit,
//            HttpServletRequest request
//    ){
//        User user = this.user(request);
//
//        PageInfo<Comment> comments = commentService.getCommentByCond(new CommentCond(),page,limit);
//        request.setAttribute("comments",comments);
//        return "admin/comment_list";
//    }
//
//    @ApiOperation("删除一条评论")
//    @PostMapping(value="/delete")
//    @ResponseBody
//    public APIResponse deleteComment(
//            @ApiParam(name="coid",value="评论编号",required = true)
//            @RequestParam(name="coid",required = true)
//            Integer coid
//    ){
//        try{
//            Comment comment = commentService.getCommentById(coid);
//            if(comment == null){
//                throw BusinessException.withErrorCode(ErrorConstant.Comment.COMMENT_NOT_EXIST);
//            }
//            commentService.deleteComment(coid);
//        }catch(Exception e){
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//            return APIResponse.fail(e.getMessage());
//        }
//        return APIResponse.success();
//    }
//
//    @ApiOperation("更改评论状态")
//    @PostMapping(value="/update")
//    @ResponseBody
//    public APIResponse changeStatus(
//            @ApiParam(name="coid",value = "评论编号",required=true)
//            @RequestParam(name="coid",required=true)
//            Integer coid,
//            @ApiParam(name="status",value="评论状态",required=true)
//            @RequestParam(name="status",required = true)
//            String status
//    ){
//        try{
//            Comment comment = commentService.getCommentById(coid);
//            if(comment != null ){
//                commentService.updateCommentStatus(coid,status);
//            }else{
//                return APIResponse.fail("删除失败");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//            return APIResponse.fail("删除失败");
//        }
//        return APIResponse.success();
//    }
//
//}
