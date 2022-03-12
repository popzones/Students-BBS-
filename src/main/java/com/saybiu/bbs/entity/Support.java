package com.saybiu.bbs.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 
 * @TableName bbs_supports
 */
@TableName(value ="bbs_supports")
@Data
public class Support implements Serializable {
    /**
     * 关注表id
     */
    @TableId(value = "support_id", type = IdType.AUTO)
    private Integer supportId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 被关注用户id
     */
    @TableField(value = "supported_user_id")
    private Integer supportedUserId;

    /**
     * 关注时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 用户属性
     */
    @TableField(exist = false)
    private Usermeta usermeta;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}