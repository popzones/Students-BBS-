package com.saybiu.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saybiu.bbs.entity.Post;
import com.saybiu.bbs.service.PostService;
import com.saybiu.bbs.dao.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
implements PostService{

}




