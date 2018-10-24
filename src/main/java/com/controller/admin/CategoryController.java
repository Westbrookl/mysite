package com.controller.admin;

import com.constant.Types;
import com.constant.WebConst;
import com.dto.MetaDto;
import com.exception.BusinessException;
import com.service.meta.MetaService;
import com.utils.APIResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController{
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private MetaService metaService;

    @ApiOperation("进入标签页")
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(),null, WebConst.MAX_POSTS);
        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(),null,WebConst.MAX_POSTS);
        request.setAttribute("categories",categories);
        request.setAttribute("tags",tags);
        return "admin/category";
    }

    /**
     * 保存分类的逻辑 传入的有 name和id
     * 首先要判断name是否存在
     * name存在的话，传入type以及 mid
     * @param cname
     * @param mid
     * @return
     */
    @ApiOperation("保存分类")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public APIResponse addCategory(
            @ApiParam(name="cname",value="分类名",required = true)
            @RequestParam(name="cname",required = true)
            String cname,
            @ApiParam(name="mid",value="分类编号",required = true)
            @RequestParam(name="mid",required = true)
            Integer mid
    ){
        try{
            metaService.saveMeta(cname,Types.CATEGORY.getType(),mid);
        }catch (Exception e){
            e.printStackTrace();
            String msg = "分类信息保存失败";
            if(e instanceof BusinessException){
                BusinessException ex = (BusinessException) e;
                msg = ex.getErrorCode();
            }
            LOGGER.error(msg,e);
            return APIResponse.fail(msg);
        }
        return APIResponse.success();
    }

    @ApiOperation("删除分类")
    @PostMapping("/delete")
    @ResponseBody
    public APIResponse deleteMeta(
            @ApiParam(name="mid",value="项目ID",required = true)
            @RequestParam(name="mid",required = true)
            Integer mid
    ){
        try{
            metaService.deleteMetaById(mid);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();
    }

}