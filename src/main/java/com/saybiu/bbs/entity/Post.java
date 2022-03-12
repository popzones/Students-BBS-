package com.saybiu.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName bbs_posts
 */
@TableName(value ="bbs_posts")
@Data
public class Post implements Serializable {
    /**
     * 帖子id
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    /**
     * 发帖人id,对应bean中实体类
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 发帖时间
     */
    @TableField(value = "create_time")
    private String createTime;

    /**
     * 帖子更新时间
     */
    @TableField(value = "update_time")
    private String updateTime;

    /**
     * 是否被封禁
     */
    @TableField(value = "post_muted")
    private Boolean postMuted;
    /**
     * postmeta属性
     */
    @TableField(exist = false)
    private Postmeta postmeta;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}