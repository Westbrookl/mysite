package com.dao;
/**
 * @author jhc
 * @date 2018-10-17



*/

import com.dto.MetaDto;
import com.dto.cond.MetaCond;
import com.entity.Meta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MetaDao{
    /**
     * 增加项目
     * @param meta
     * @return
     */
    int addMeta(Meta meta);

    /**
     * 根据Id删除项目
     * @param mid
     * @return
     */
    int deleteMetaById(@Param("mid")Integer mid);

    /**
     * 更新项目
     * @param meta
     * @return
     */
    int updateMeta(Meta meta);

    /**
     * 通过Id获取项目
     * @param mid
     * @return
     */
    Meta getMetaById(@Param("mid")Integer mid);

    /**
     * 通过条件获取项目
     * @param metaCond
     * @return
     */
    List<Meta> getMetasByCond(MetaCond metaCond);

    /**
     * 统计type的项目数量
     * @param type
     * @return
     */
    Long getMetasByType(@Param("type")String type);

    /**
     * 根据sql语句获取项目
     * @param paraMap
     * @return
     */
    List<MetaDto> selectFromSql(Map<String,Object> paraMap);
}