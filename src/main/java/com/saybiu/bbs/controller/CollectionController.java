package com.saybiu.bbs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saybiu.bbs.dao.mapper.CollectionMapper;
import com.saybiu.bbs.entity.Collection;
import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import com.saybiu.bbs.service.CollectionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @Author wangwei
 * @Date 2021/5/12 15:09
 * @Version 1.0
 */
@RestController
@Validated
public class CollectionController {
    @Resource
    private CollectionService collectionService;
    @GetMapping(value = "collections")
    public List<Collection> queryCollections(@Min(1) Integer userId){
        return collectionService.queryCollections(userId);
    }

}
