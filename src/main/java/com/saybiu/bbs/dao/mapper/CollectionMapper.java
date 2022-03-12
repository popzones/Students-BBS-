package com.saybiu.bbs.dao.mapper;

import com.saybiu.bbs.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.saybiu.bbs.entity.Collection
 */
public interface CollectionMapper extends BaseMapper<Collection> {

    List<Collection> queryCollections(Integer userId);
}




