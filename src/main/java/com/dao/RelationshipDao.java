package com.dao;

import com.entity.Relationship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jhc on 2018/10/17
 */
@Mapper
public interface RelationshipDao {
    /**
     * 增加关系
     * @param relationship
     * @return
     */
    int addRelationship(Relationship relationship);

    /**
     * 删除关系
     * @param cid
     * @param mid
     * @return
     */
    int deleteRelationshipById(@Param("cid")Integer cid,@Param("mid")Integer mid);

    /**通过文章主键
     * 删除关系
     * @param mid
     * @return
     */
    int deleteRelationshipByMid(@Param("mid")Integer mid);
    int deleteRelationshipByCid(@Param("cid")Integer cid);
    /**
     * 更新关系
     * @param relationship
     * @return
     */
    int updateRelationship(Relationship relationship);

    /**
     * 根据文章主键获得关系
     * @param cid
     * @return
     */
    List<Relationship> getRelationshipByCid(@Param("cid")Integer cid);

    /**
     * 根据meta编号获取关系
     * @param mid
     * @return
     */
    List<Relationship> getRelationshipByMid(@Param("mid")Integer mid);

    /**
     * 获取数量
     * @param cid
     * @param mid
     * @return
     */
    Long getCountById(@Param("cid")Integer cid,@Param("mid")Integer mid);
}
