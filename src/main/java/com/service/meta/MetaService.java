package com.service.meta;

import com.dao.MetaDao;
import com.dto.MetaDto;
import com.dto.cond.MetaCond;
import com.entity.Meta;

import java.util.List;

/**
 * @author jhc
 * @date 2018-10-17



*/
public interface MetaService{
    /**
     * 服务增加项目
     * @param meta
     */
    void addMeta(Meta meta);

    /**
     * 保存项目
     * @param name
     * @param type
     * @param mid
     */
    void saveMeta(String name,String type,Integer mid);

    /**
     * 保存多个项目，批量增加
     * @param cid
     * @param names
     * @param type
     */
    void addMetas(Integer cid,String names,String type);

    /**
     * 增加或者更新
     * @param cid
     * @param name
     * @param type
     */
    void saveOrUpdate(Integer cid,String name,String type);

    /**
     * 删除项目
     * @param mid
     */
    void deleteMetaById(Integer mid);

    /**
     * 更新项目
     * @param meta
     */
    void updateMeta(Meta meta);

    /**
     * 通过id获得项目
     * @param mid
     * @return
     */
    Meta getMetaById(Integer mid);

    /**
     * 获取所有项目
     * @param metaCond
     * @return
     */
    List<Meta> getMetas(MetaCond metaCond);

    /**
     * 根据类型查询项目列表，带项目下面的文章数
     * @param type
     * @param orderby
     * @param limit
     * @return
     */
    List<MetaDto> getMetaList(String type,String orderby,int limit);
}