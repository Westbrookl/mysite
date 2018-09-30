package com.dao;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    /**
        更改用户信息
     */
    int updateUserInfo(User user);
    /**
     * 通过ID找到用户
     *
     */
    User getUserInfoById(@Param("uid") Integer uid);
    /**
     *根据用户名密码得到用户信息
     */
    User getUSerInfoByCond(@Param("username")String username,@Param("password") String password);
}
