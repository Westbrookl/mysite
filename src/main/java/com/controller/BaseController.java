package com.controller;

import com.entity.User;
import com.utils.TaleUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jhc on 2018/9/30
 */
public abstract class BaseController {
    public User user(HttpServletRequest request){
        return TaleUtils.getLoginUser(request);
    }
}
