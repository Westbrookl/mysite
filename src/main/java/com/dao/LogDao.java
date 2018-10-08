package com.dao;

import com.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jhc on 2018/10/8
 */
@Mapper
public interface LogDao {
    /**
     * 添加日志
     * @param log
     * @return
     */
    int addLog(Log log);

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLogById(@Param("id") Integer id);

    /**
     * 获取日志
     * @return
     */
    List<Log> getLogs();
}
