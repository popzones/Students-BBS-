package com.saybiu.bbs.dao.mapper;

import com.saybiu.bbs.entity.Support;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.saybiu.bbs.entity.Support
 */
public interface SupportMapper extends BaseMapper<Support> {

    List<Support> querySupports(int start, int pageSize, Integer userId);

    List<Support> queryFans(Integer userId);
}




