package com.service.meta.Impl;

import com.constant.ErrorConstant;
import com.constant.Types;
import com.constant.WebConst;
import com.dao.MetaDao;
import com.dao.RelationshipDao;
import com.dto.MetaDto;
import com.dto.cond.MetaCond;
import com.entity.Content;
import com.entity.Meta;
import com.entity.Relationship;
import com.exception.BusinessException;
import com.service.content.ContentService;
import com.service.meta.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Meta中的Mid 和 type是一一对应的对应于文章的标签分类
 * 而meta中的name对应于文章的tags
 */
@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaDao metaDao;

    @Autowired
    private RelationshipDao relationshipDao;

    @Autowired
    private ContentService contentService;


    @Override
    public void addMeta(Meta meta) {
        if(meta == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        metaDao.addMeta(meta);
    }

    /**
     * 保存项目的历程
        首先先判断字符串 如果不为空的话作为一个查询条件去查找数据库有没有符合的选项
        如果有就抛出异常
        如果没有的话
        查看是否传入了mid，如果主键存在则是更新标签
        如果主键不存在则是保存并且创建标签
     * @param name
     * @param type
     * @param mid
     */
    @Override
    public void saveMeta(String name, String type, Integer mid) {
        if(StringUtils.isNotBlank(name)&&StringUtils.isNotBlank(type)){
            MetaCond metaCond =  new MetaCond();
            metaCond.setName(name);
            metaCond.setType(type);
            List<Meta> metas = metaDao.getMetasByCond(metaCond);
            if(metas == null || metas.size()==0){
                Meta meta = new Meta();
                meta.setName(name);
                if(mid !=null){
                    Meta meta1 = metaDao.getMetaById(mid);
                    if(meta1 != null)
                        meta.setMid(mid);
                        metaDao.updateMeta(meta);
                        //更新原有文章的分类
                        contentService.updateCategory(meta1.getName(),name);

                }else{
                    meta.setType(type);
                    metaDao.addMeta(meta);
                }
            }else{
                throw BusinessException.withErrorCode(ErrorConstant.Meta.META_IS_EXIST);
            }
        }
    }

    /**
     * meta下面每个类别下面可以有很多名字
     *
     * @param cid
     * @param names
     * @param type
     */
    @Override
    public void addMetas(Integer cid, String names, String type) {
        if(cid == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        if(StringUtils.isNotBlank(names) && StringUtils.isNotBlank(type)){
            String[] nameArr = names.split(",");
            for(String name:nameArr){
                this.saveOrUpdate(cid,name,type);
            }
        }
    }

    /**
     * 保存的是创建一个新的标签
     * 更新的是一个文章对象的标签下面的名字
     * @param cid
     * @param name
     * @param type
     */
    @Override
    public void saveOrUpdate(Integer cid, String name, String type) {
        MetaCond metaCond = new MetaCond();
        metaCond.setType(type);
        metaCond.setName(name);
        List<Meta> metas = this.getMetas(metaCond);

        int mid;
        Meta meta;
        if(metas.size() == 1){
            Meta temp = metas.get(0);
            mid = temp.getMid();
        }else if(metas.size()>1){
            throw BusinessException.withErrorCode(ErrorConstant.Meta.META_IS_EXIST);
        }else{
            meta  = new Meta();
            meta.setSlug(name);
            meta.setName(name);
            meta.setType(type);
            this.addMeta(meta);
            mid = meta.getMid();
        }
        if(mid != 0){
            Long count = relationshipDao.getCountById(cid,mid);
            if(count == 0){
                Relationship relationship = new Relationship();
                relationship.setCid(cid);
                relationship.setMid(mid);
                relationshipDao.addRelationship(relationship);
            }
        }
    }

    @Override
    public void deleteMetaById(Integer mid) {
        if(mid == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        Meta meta = metaDao.getMetaById(mid);
        if(meta != null){
            String type = meta.getType();
            String name = meta.getName();
            metaDao.deleteMetaById(mid);
            /**
               删除标签的时候也需要删除
                文章中的对应的内容
             */
            List<Relationship> relationships = relationshipDao.getRelationshipByMid(mid);
            if(relationships!=null && relationships.size()>0){
                for(Relationship re:relationships) {
                    Content article = contentService.getArticleById(re.getCid());
                    if (article != null) {
                        Content temp = new Content();
                        temp.setCid(re.getCid());
                        if (type.equals(Types.CATEGORY.getType())) {
                            temp.setCategories(reMeta(name, article.getCategories()));
                        }
                        if (type.equals(Types.TAG.getType())) {
                            temp.setTags(reMeta(name, article.getTags()));
                        }
                        //将删除的资源去除
                        contentService.updateArticleById(temp);
                    }
                }
                relationshipDao.deleteRelationshipByMid(mid);
            }
        }
    }

    @Override
    public void updateMeta(Meta meta) {
        if(meta == null ||meta.getMid() == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        metaDao.updateMeta(meta);
    }

    @Override
    public Meta getMetaById(Integer mid) {
        if(mid == null){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return metaDao.getMetaById(mid);
    }

    @Override
    public List<Meta> getMetas(MetaCond metaCond) {
        return metaDao.getMetasByCond(metaCond);
    }

    @Override
    public List<MetaDto> getMetaList(String type, String orderby, int limit) {
       if(StringUtils.isNotBlank(type)){
           if(StringUtils.isBlank(orderby)){
               orderby = "count desc,a.mid desc";
           }
           if(limit<1 ||limit>WebConst.MAX_POSTS){
               limit=10;
           }
           Map<String,Object> paraMap = new HashMap<>();
           paraMap.put("type",type);
           paraMap.put("order",orderby);
           paraMap.put("limit",limit);
           return metaDao.selectFromSql(paraMap);
       }
       return null;
    }

    private String reMeta(String name,String metas){
        String[] ms = metas.split(",");
        StringBuilder sbuf =  new StringBuilder();
        for(String m:ms){
            if(!name.equals(m)){
                sbuf.append(",").append(m);
            }
        }
        if(sbuf.length()>0){
            return sbuf.substring(1);
        }
        return "";
    }
}