package com.saybiu.bbs.dao.mapper;

import com.saybiu.bbs.entity.Usermeta;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.saybiu.bbs.entity.Usermeta
 */
public interface UsermetaMapper extends BaseMapper<Usermeta> {
    //更新用户关注数量(加一)
    int addSupportNum(Integer userId);
    //更新被关注用户粉丝数量(加一)
    int addSupportedNum(Integer userId);
    //更新用户关注数量(减一)
    int deleteSupportNum(Integer userId);
    //更新被关注用户粉丝数量(减一)
    int deleteSupportedNum(Integer userId);
}




