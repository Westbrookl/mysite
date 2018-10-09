package com.service.user.impl;

import com.constant.ErrorConstant;
import com.dao.UserDao;
import com.entity.User;
import com.exception.BusinessException;
import com.service.user.UserService;
import com.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jhc on 2018/9/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;//这里会报错，但是并不会影响


    @Transactional
    @Override
    public int updateUserInfo(User user) {
        if (null == user.getUid())
            throw BusinessException.withErrorCode("用户编号不可能为空");
        return userDao.updateUserInfo(user);
    }

    @Override
    public User getUserInfoById(Integer uId) {
        return userDao.getUserInfoById(uId);
    }

    @Override
    public User login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);

        String pwd = TaleUtils.MD5encode(username + password);
        User user = userDao.getUserInfoByCond(username, pwd);
        if (null == user){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }

        return user;
    }


}
