//package com.service.meta.Impl;
//
//import com.constant.ErrorConstant;
//import com.constant.WebConst;
//import com.dao.MetaDao;
//import com.dto.MetaDto;
//import com.dto.cond.MetaCond;
//import com.entity.Meta;
//import com.exception.BusinessException;
//import com.service.content.ContentService;
//import com.service.meta.MetaService;
//import org.apache.commons.lang3.StringUtils;///?????
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.management.relation.Relation;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author jhc on 2018/10/3
// * Meta 的具体服务实现类
// */
//@Service
//public class MetaServiceImpl implements MetaService {
//
//    @Autowired
//    private MetaDao metaDao;
//
//    @Autowired
//    private RelationShipDao relationShipDao;
//
//    @Autowired
//    private ContentService contentService;
//
//    @Override
//    @Transactional
//    public void addMeta(Meta meta) {
//        if(meta == null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        metaDao.addMeta(meta);
//    }
//
//    @Override
//    public void saveMeta(String type, String name, Integer mid) {
//        if(StringUtils.isNotBlank(type) && StringUtils.isNoneBlank(name)){
//            MetaCond metaCond = new MetaCond();
//            metaCond.setName(name);
//            metaCond.setType(type);
//            List<Meta> metas = metaDao.getMetasByCond(metaCond);
//            if( metas == null || metas.size()==0){
//                Meta meta = new Meta();
//                meta.setName(name);
//                if(mid != null){
//                    Meta meta1 = metaDao.getMetaById(mid);
//                    if(meta1 != null)
//                        meta.setMid(mid);
//                    metaDao.updateMeta(meta);
//                    //更新原有文章分类
//                    contentService.updateCategory(meta.getName(),name);
//                }else{
//                    meta.setType(type);
//                    metaDao.addMeta(meta);
//                }
//            }else{
//                throw BusinessException.withErrorCode(ErrorConstant.Meta.META_IS_EXIST);
//            }
//        }else{
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//    }
//
//    @Override
//    public void addMetas(Integer mid, String type, String name) {
//        if(mid == null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(type)){
//            String[] nameArr = StringUtils.split(name,",");
//            for(String name1:nameArr){
//                this.saveOrUpdate(mid,name,type);
//            }
//        }
//    }
//
//    @Override
//    public void saveOrUpdate(Integer mid, String name, String type) {
//        MetaCond metaCond = new MetaCond();
//        metaCond.setName(name);
//        metaCond.setType(type);
//        List<Meta>  metas = this.getMetas(metaCond);
//
//        int cid;
//        Meta meta;
//        if(metas.size() == 1){
//            Meta metas1 = metas.get(0);
//            cid = metas1.getMid();
//        }else if(metas.size()>1){
//            throw  BusinessException.withErrorCode(ErrorConstant.Meta.NOT_ONE_RESULT);
//        }else{
//            Meta metaTemp = new Meta();
//            metaTemp.setType(type);
//            metaTemp.setSlug(name);
//            metaTemp.setName(name);
//            this.addMeta(metaTemp);
//            cid = metaTemp.getMid();
//        }
//        if(cid != 0){
//            Long count = relationShipDao.getCountById(mid,cid);
//            if(count == 0){
//                RelationShip relationShip = new RelationShip();
//                relationShip.setCid(mid);
//                relationShip.setMid(cid);
//                relationShipDao.addRelationShip(relationShip);
//            }
//        }
//
//
//    }
//
//    @Override
//    public void deleteMetaById(Integer mid) {
//        if(mid == null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        Meta meta = metaDao.getMetaById(mid);
//        if(meta != null){
//
//        }
//
//    }
//
//    @Override
//    public void updateMeta(Meta meta) {
//        if(meta == null || meta.getMid()==null){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        metaDao.updateMeta(meta);
//    }
//
//    @Override
//    public Meta getMetaById(Integer mid) {
//        if(mid == null){
//            throw  BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        return metaDao.getMetaById(mid);
//    }
//
//    @Override
//    public List<Meta> getMetas(MetaCond metaCond) {
//        return metaDao.getMetasByCond(metaCond);
//    }
//
//
//    @Override
//    public List<MetaDto> getMetaList(String type, String orderby, int limit) {
//        if(StringUtils.isNotBlank(type)){
//            if(StringUtils.isNotBlank(orderby)){
//                orderby = "count desc,a.mid desc";
//            }
//            if(limit<1 || limit> WebConst.MAX_POSTS){
//                limit = 10;
//            }
//            Map<String,Object> paraMap = new HashMap<>();
//            paraMap.put("type",type);
//            paraMap.put("orderby",orderby);
//            paraMap.put("limit",limit);
//            return metaDao.selectFromSql(paraMap);
//        }
//        return null;
//    }
//
//    /**
//     * 没太懂这个
//     * @param name
//     * @param metas
//     * @return
//     */
//    private String reMeta(String name,String metas){
//        String[] ms = StringUtils.split(metas,",");
//        StringBuilder shuf = new StringBuilder();
//        for(String m:ms){
//            if(!name.equals(m)){
//                shuf.append(",").append(m);
//            }
//        }
//        if(shuf.length()>0){
//            return shuf.substring(1);
//        }
//        return "";
//    }
//}
