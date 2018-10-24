package com.controller.admin;

import com.constant.ErrorConstant;
import com.constant.Types;
import com.dto.cond.MetaCond;
import com.entity.Meta;
import com.exception.BusinessException;
import com.service.meta.MetaService;
import com.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jhc on 2018/10/19
 */
@Api("友情链接")
@Controller
@RequestMapping("/admin/links")
public class LinksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

    @Autowired
    private MetaService metaService;

    @ApiOperation("友情链接的页面")
    @GetMapping("")
    public String index(HttpServletRequest request){
        MetaCond metaCond  = new MetaCond();
        metaCond.setType(Types.LINK.getType());
        List<Meta> metas  = metaService.getMetas(metaCond);
        request.setAttribute("links",metas);
        return "admin/links";
    }

    @ApiOperation("新增友情链接的页面")
    @PostMapping("save")
    @ResponseBody
    public APIResponse saveLinks(
            @ApiParam(name="mid",value = "项目编号",required = false)
            @RequestParam(name="mid",required = false)
                    Integer mid,
            @ApiParam(name="title",value = "链接名称",required = true)
            @RequestParam(name="title",required = true)
            String title,
            @ApiParam(name="url",value="链接",required = true)
            @RequestParam(name="url",required = true)
            String url,
            @ApiParam(name="logo",value="logo",required = false)
            @RequestParam(name="logo",required = false)
            String logo,
            @ApiParam(name="sort",value="sort",required = false)
            @RequestParam(name="sort",required = false)
            Integer sort
    ){
        try {
            Meta meta = new Meta();
            meta.setName(title);
            meta.setSlug(url);
//                meta.setDescription(logo);
            meta.setDescription(logo);
            meta.setSort(sort);
            meta.setType(Types.LINK.getType());
            if (mid != null) {
                meta.setMid(mid);
                metaService.updateMeta(meta);
            } else {
                metaService.addMeta(meta);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw BusinessException.withErrorCode(ErrorConstant.Meta.META_IS_EXIST);
        }
        System.out.println("11111111");
        return APIResponse.success();
    }

    @ApiOperation("删除链接")
    @PostMapping("/delete")
    @ResponseBody
    public APIResponse deleteById(
            @ApiParam(name="mid",value="项目主键",required = true)
            @RequestParam(name="mid",required =  true)
            Integer mid
    ){
        try{
            metaService.deleteMetaById(mid);
        }catch (Exception e){
            e.printStackTrace();
            throw  BusinessException.withErrorCode(ErrorConstant.Meta.DELETE_META_FAIL);
        }
        return APIResponse.success();
    }

}
