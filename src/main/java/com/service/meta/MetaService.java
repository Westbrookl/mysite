package com.service.meta;

import com.dto.MetaDto;
import com.dto.cond.MetaCond;
import com.entity.Meta;

import java.util.List;

/**
 * @author jhc
 * @date 2018-10-03
 * 逻辑层

*/
public interface MetaService  {
    /**
     * 增加标签
     * @param meta
     */
    void addMeta(Meta meta);

    /**
     * 添加
     * @param type
     * @param name
     * @param mid
     */
    void saveMeta(String type,String name,Integer mid);

    /**
     * 批量添加
     * @param mid
     * @param type
     * @param name
     */
    void addMetas(Integer mid,String type,String name);

    /**
     * 添加或者更新
     * @param mid
     * @param name
     * @param type
     */
    void saveOrUpdate(Integer mid,String name,String type);

    /**
     * 删除内容
     * @param mid
     */
    void deleteMetaById(Integer mid);

    /**
     * 跟新标签
     * @param meta
     */
    void updateMeta(Meta meta);

    /**
     * 通过Id获取标签内容
     * @param mid
     * @return
     */
    Meta getMetaById(Integer mid);

    /**
     * 通过条件获取标签内容
     * @param metaCond
     * @return
     */
    List<Meta> getMetas(MetaCond metaCond);

    /**
     * 统计类型下面的内容
     * @param type
     * @return
     */
    Long getMetasCountByType(String type);

    /**
     * 这个不是很懂
     * @param type
     * @param orderby
     * @param limit
     * @return
     */
    List<MetaDto> getMetaList(String type,String orderby,int limit);
}
