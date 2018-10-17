package com.service.relation.Impl;

import com.dao.RelationshipDao;
import com.entity.Relationship;
import com.service.relation.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jhc on 2018/10/17
 */
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private RelationshipDao relationshipDao;
}
