package com.controller.admin;

import com.constant.LogActions;
import com.constant.WebConst;
import com.controller.BaseController;
import com.entity.Log;
import com.entity.User;
import com.exception.BusinessException;
import com.github.pagehelper.PageInfo;
import com.service.Log.LogService;
import com.service.user.UserService;
import com.sun.xml.internal.messaging.saaj.util.LogDomainConstants;
import com.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author jhc on 2018/10/10
 */
@RequestMapping(value = "/admin")
@Controller
@Api("后台页面")
public class IndexController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @ApiOperation("首页页面内容")
    @GetMapping(value = {"", "/index"})
    public String index(HttpServletRequest request) {
        PageInfo<Log> logs = logService.getLogs(1, 5);
        List<Log> list = logs.getList();
        request.setAttribute("logs", list);
        return "admin/index";
    }

    @ApiOperation("个人资料")
    @GetMapping(value = "/profile")
    public String profile() {
        return "admin/profile";
    }

    /**
     * 保存个人资料只保存的ScreenName和Email在这里并没有对Email的格式进行验证，也没有去新加内容后续的处理
     * 可以添加用户的头像的内容。
     * 另外也没有使用加密的方式对密码进行处理在安全性方面会有影响
     * @param screenName
     * @param email
     * @param request
     * @return
     */
    @ApiOperation("保存个人资料")
    @PostMapping(value = "/profile")
    @ResponseBody
    public APIResponse saveProfile(
            @ApiParam(name = "screenName", value = "用户名", required = true)
            @RequestParam(name="screenName",required = true)
                    String screenName,
            @RequestParam(name="email",required = true)
            @ApiParam(name = "email", value = "邮箱", required = true)
                    String email,
            HttpServletRequest request,
            HttpSession session
    ) {
        User users = this.user(request);
//        if (StringUtils.isBlank(screenName) || StringUtils.isBlank(email)) {
//            return APIResponse.fail("信息不能为空");
//        }
//        try {
//            User tempUser = new User();
//            tempUser.setUid(users.getUid());
//            tempUser.setScreenName(screenName);
//            tempUser.setEmail(email);
//            userService.updateUserInfo(tempUser);
//            logService.addLog(LogActions.UP_INFO.getAction(), null, request.getRemoteAddr(), users.getUid());
//            return APIResponse.success();
//        } catch (Exception e) {
//            String msg = "更新信息失败";
//            if (e instanceof BusinessException) {
//                msg = e.getMessage();
//            } else {
//                LOGGER.error(e.getMessage());
//            }
//            return APIResponse.fail(msg);
//        }
//        String temp1 = screenName;
//        String temp2 = email;
//        System.out.println(screenName+"----->"+email);
            if(StringUtils.isNotBlank(screenName) && StringUtils.isNotBlank(email)){
                User temp = new User();
                temp.setUid(users.getUid());
                temp.setScreenName(screenName);
                temp.setEmail(email);
                userService.updateUserInfo(temp);
                logService.addLog(LogActions.UP_INFO.getAction(),null,request.getRemoteAddr(),users.getUid());

                //
                User original = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
                original.setScreenName(screenName);
                original.setEmail(email);
                session.setAttribute(WebConst.LOGIN_SESSION_KEY,original);
            }
            return APIResponse.success();
    }



    /**
     * 修改密码
     *判断密码的内容 并且输入一个 密码长度在6到14位之间
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @ApiOperation("修改密码")
    @PostMapping("/password")
    @ResponseBody
    public APIResponse upPwd(
            @ApiParam(name = "oldPassword", value = "旧密码", required = true)
             @RequestParam(name="oldPassword",required = true)
                    String oldPassword,
            @ApiParam(name = "newPassword", value = "新密码", required = true)
             @RequestParam(name="newPassword",required = true)
                    String newPassword,
            HttpServletRequest request,
            HttpSession session
    ) {
        User users = this.user(request);
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            return APIResponse.fail("请输入完整的信息");
        }
        if (!users.getPassword().equals(oldPassword)) {
            return APIResponse.fail("旧密码错误");
        }
        if (newPassword.length() < 6 || newPassword.length() > 14) {
            return APIResponse.fail("新密码必须在6位和14位之间");
        }
        try {
            User tempUser = new User();
            tempUser.setUid(users.getUid());
            tempUser.setPassword(newPassword);
            userService.updateUserInfo(tempUser);
            logService.addLog(LogActions.UP_PWD.getAction(), null, request.getRemoteAddr(), users.getUid());

            User original = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            original.setPassword(newPassword);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY, original);
            return APIResponse.success();
        } catch (Exception e) {
            String msg = "修改密码失败";
            if (e instanceof BusinessException) {
                msg = e.getMessage();

            } else {
                LOGGER.error(e.getMessage());
            }
            return APIResponse.fail(msg);
        }

    }
}
