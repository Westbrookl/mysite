package com.service.Log;

import com.entity.Log;
import com.github.pagehelper.PageInfo;


public interface LogService {
    /**
     * 添加日志
     * @param action
     * @param data
     * @param ip
     * @param authorId
     */
    void addLog(String action,String data,String ip,Integer authorId);

    /**
     * 删除日志
     * @param id
     */
    void deleteLogById(Integer id);

    /**
     * 获取日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Log> getLogs(int pageNum,int pageSize);
}
