package com.dao;
/**
 * @author jhc
 * @date 2018-10-03
*/
import com.dto.MetaDto;
import com.dto.cond.MetaCond;
import com.entity.Meta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper//这里的Mapper是为了方便与xml的内容进行映射
        //@Param()主要是用于实体类的对象里面的属性进行操作的
public interface MetaDao {
    /**
     * 增加标签
     * @param meta
     * @return
     */
    int addMeta(Meta meta);

    /**
     * 通过Id删除标签
     * @param id
     * @return
     */
    int deleteMetaById(@Param("mid") Integer mid);

    /**
     * 更新标签
     * @param meta
     * @return
     */
    int updateMeta(Meta meta);

    /**
     * 通过Id获取标签
     * @param mid
     * @return
     */
    Meta getMetaById(@Param("mid") Integer mid);

    /**
     * 通过条件获取标签
     * @param metaCond
     * @return
     */
    List<Meta> getMetasByCond(MetaCond metaCond);

    /**
     * 统计标签下面的内容
     * @param type
     * @return
     */
    Long getMetasCountByType(@Param("type") String type);

    /**
     * 根据sql查询
     * @param paraMap
     * @return
     */
    List<MetaDto> selectFromSql(Map<String,Object> paraMap);
}
