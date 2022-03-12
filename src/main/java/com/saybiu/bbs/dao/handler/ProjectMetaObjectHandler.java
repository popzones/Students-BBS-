package com.saybiu.bbs.dao.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.saybiu.bbs.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/5/5 1:45 上午 （日期和时间）
 */
@Component
@Slf4j
public class ProjectMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入");
        this.strictInsertFill(metaObject, "createTime", String.class, Utils.toDate(new Date()));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新");
        this.strictUpdateFill(metaObject, "updateTime", String.class, Utils.toDate(new Date()));
    }
}
