package com.service.Log.Impl;

import com.constant.ErrorConstant;
import com.dao.LogDao;
import com.entity.Log;
import com.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.Log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jhc on 2018/10/8
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public void addLog(String action, String data, String ip, Integer authorId) {
        Log log = new Log();
        log.setAction(action);
        log.setData(data);
        log.setIp(ip);
        log.setAuthorId(authorId);
        logDao.addLog(log);
    }

    @Override
    public void deleteLogById(Integer id) {
        if(id == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        logDao.deleteLogById(id);
    }

    @Override
    public PageInfo<Log> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Log> logs = logDao.getLogs();
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        return pageInfo;
    }
}
