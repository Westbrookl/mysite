//package com.controller.admin;
//
//import com.constant.Types;
//import com.constant.WebConst;
//import com.controller.BaseController;
//import com.dto.MetaDto;
//import com.entity.Meta;
//import com.exception.BusinessException;
//import com.service.content.ContentService;
//import com.service.meta.MetaService;
//import com.utils.APIResponse;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//
//@Api("分类和标签管理")
//@Controller
//@RequestMapping(path="/admin/category",method = RequestMethod.GET)
//public class CategoryController extends BaseController {
//    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
//
//    @Autowired
//    private MetaService metaService;
//
//
//    @ApiOperation("增加标签")
//    @GetMapping("")
//    public String saveCategory(HttpServletRequest request){
//        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(),null, WebConst.MAX_POSTS);
//        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(),null,WebConst.MAX_POSTS);
//        request.setAttribute("categories",categories);
//        request.setAttribute("tags",tags);
//        return "admin/category";
//    }
//
//    @ApiOperation("保存分类")
//    @RequestMapping(path="/save",method=RequestMethod.POST)
//    @ResponseBody
//    public APIResponse addCategory(
//        @ApiParam(name="cname",value="分类名",required=true)
//        @RequestParam(name="cname",required = true)
//        String cname,
//        @ApiParam(name="mid",value="编号",required = false)
//        @RequestParam(name="mid",required = false)
//        Integer mid) {
//        try{
//            metaService.saveMeta(Types.CATEGORY.getType(),cname,mid);
//        }catch (Exception e){
//            e.printStackTrace();
//            String msg="分类信息保存失败";
//            if(e instanceof BusinessException){
//                BusinessException ex = (BusinessException) e;
//                msg = ex.getErrorCode();
//            }
//            logger.error(msg,e);
//            return APIResponse.fail(msg);
//        }
//        return APIResponse.success();
//    }
//
//    @ApiOperation("删除分类")
//    @RequestMapping(path="/delete",method = RequestMethod.POST)
//    @ResponseBody
//    public APIResponse deleteCategory(
//            @ApiParam(name="mid",value="分类的主键",required = true)
//            @RequestParam(name="mid",required = true)
//            Integer mid
//    ){
//        try{
//            metaService.deleteMetaById(mid);
//        }catch(Exception e){
//            e.printStackTrace();
//            String msg = "删除失败";
//            if(e instanceof BusinessException){
//                BusinessException ex = (BusinessException) e;
//                msg = ex.getErrorCode();
//            }
//            logger.error(msg,e);
//            return APIResponse.fail(msg);
//        }
//        return APIResponse.success();
//    }
//
//
//
//}
