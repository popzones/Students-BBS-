package com.saybiu.dao;

import com.saybiu.domain.Support;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: liming
 * @Date: 2021/4/17 14:35
 */
public interface SupportDao {
    int insertSupport(Support support);
    int selectSupportByEachUserId(@Param("userId")Integer userId,@Param("beSupportedUserId") Integer beSupportedUserId);
}
