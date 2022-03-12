package com.saybiu.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saybiu.bbs.dao.mapper.SupportMapper;
import com.saybiu.bbs.dao.mapper.UserMapper;
import com.saybiu.bbs.entity.PageBean;
import com.saybiu.bbs.entity.Support;
import com.saybiu.bbs.entity.User;
import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import com.saybiu.bbs.service.SupportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class SupportServiceImpl extends ServiceImpl<SupportMapper, Support>
implements SupportService{
    @Resource
    private SupportMapper supportMapper;
    @Resource
    private UserMapper userMapper;
    /**
     *
     * 查询关注人
     * @param userId 用户id
     * @param currentPage 当前页数
     * @return
     */
    @Override
    public List<Support> querySupport(Integer userId,Integer currentPage) {
        User user = userMapper.selectById(userId);
        if (user==null){
            throw new ServiceException(CommonCode.SELECT_ERROR,"该用户不存在,无法查询关注人信息");
        }
        //分页查询关注人
//        Page<Support> supportPage = supportMapper.selectPage(new Page<Support>(pages, 5), new QueryWrapper<Support>().eq("user_id",userId));
//        List<Support> supports =  supportPage.getRecords();
        //设置每页显示条数
        int pageSize = 2;
        //获取每页起始行数
        int start = (currentPage-1)*pageSize;
        //获取商品list集合
        List<Support> supports = supportMapper.querySupports(start,pageSize,userId);
        return supports;
    }

    /**
     * 查询粉丝
     * @param userId
     * @return
     */
    @Override
    public List<Support> queryFans(Integer userId,Integer currentPage) {
        User user = userMapper.selectById(userId);
        if (user==null){
            throw new ServiceException(CommonCode.SELECT_ERROR,"该用户不存在,无法查询粉丝信息");
        }
//        List<Support> supports = supportMapper.queryFans(userId);
        //分页查询粉丝
//        Page<Support> supportPage = supportMapper.selectPage(new Page<Support>(currentPage, 5), new QueryWrapper<Support>().eq("user_id",userId));
//        List<Support> supports =  supportPage.getRecords();
        //设置每页显示条数
        int pageSize = 2;
        //获取每页起始行数
        int start = (currentPage-1)*pageSize;
        //获取商品list集合
        List<Support> supports = supportMapper.querySupports(start,pageSize,userId);
        return supports;
    }
}




