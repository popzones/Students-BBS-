package com.saybiu.bbs.service;

import com.saybiu.bbs.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface CollectionService extends IService<Collection> {

    List<Collection> queryCollections(Integer userId);
}
