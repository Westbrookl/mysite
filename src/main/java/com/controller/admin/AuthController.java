package com.controller.admin;

import com.constant.LogActions;
import com.constant.WebConst;
import com.entity.User;
import com.exception.BusinessException;
import com.service.Log.LogService;
import com.service.user.UserService;
import com.utils.APIResponse;
import com.utils.MapCache;
import com.utils.TaleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author jhc on 2018/10/8
 */
@Api("登录相关的接口")
@Controller
@RequestMapping("/admin")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    private MapCache cache = new MapCache();
    @ApiOperation("跳转到登录页面")
    @GetMapping(value="/login")
    public String login(){
        return "admin/login";
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    @ResponseBody
    public APIResponse toLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(name="username",value="用户名",required=true)
            String username,
            @ApiParam(name="password",value="密码",required = true)
            String password,
            @ApiParam(name="remeber_me",value="记住我",required = false)
            String remeber_me
    ){
        Integer error_count = cache.get("login_error_count");
        try{
            User userInfo = userService.login(username,password);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY,userInfo);
            if(StringUtils.isNotBlank(remeber_me)){
                TaleUtils.setCookie(response,userInfo.getUid());
            }
            logService.addLog(LogActions.LOGIN.getAction(),null,request.getRemoteAddr(),userInfo.getUid());

            System.out.println(LogActions.LOGIN.getAction());
        }catch (Exception e){
//            System.out.println(333333333);
            LOGGER.error(e.getMessage());
            error_count = error_count==null ? 1: error_count+1;
            System.out.println(error_count);
            if(error_count > 3){
                return APIResponse.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }
            cache.set("login_error_count",error_count,10*60);
            String msg = "登录失败";
            if(e instanceof BusinessException){


                msg = ((BusinessException) e).getErrorCode();

            }else{

                LOGGER.error(msg,e);
            }

//            System.out.println(msg);
//            System.out.println("1---------------");
            return APIResponse.fail(msg);
        }
//        System.out.println(44444);
        return APIResponse.success();
    }

    @ApiOperation("注销")
    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request){
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE,"");
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        try{
            response.sendRedirect("/admin/login");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("注销失败",e);
        }
    }
}
