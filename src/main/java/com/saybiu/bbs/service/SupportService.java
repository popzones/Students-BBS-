package com.saybiu.bbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.saybiu.bbs.entity.Support;

import java.util.List;

/**
 *
 */
public interface SupportService extends IService<Support> {

    List<Support> querySupport(Integer userId,Integer currentPage);

    List<Support> queryFans(Integer userId,Integer currentPage);
}
