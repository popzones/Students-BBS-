package com.saybiu.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saybiu.bbs.entity.Collection;
import com.saybiu.bbs.service.CollectionService;
import com.saybiu.bbs.dao.mapper.CollectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
implements CollectionService{
    @Resource
    private CollectionMapper collectionMapper;
    /**
     * 查询用户收藏帖子信息
     * @param userId
     * @return
     */
    @Override
    public List<Collection> queryCollections(Integer userId) {
        return collectionMapper.queryCollections(userId);
    }
}




