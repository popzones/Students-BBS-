package com.saybiu.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName bbs_collections
 */
@TableName(value ="bbs_collections")
@Data
public class Collection implements Serializable {
    /**
     * 收藏表id
     */
    @TableId(value = "collection_id", type = IdType.AUTO)
    private Integer collectionId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 帖子id
     */
    @TableField(value = "post_id")
    private Integer postId;

    /**
     * 收藏时间
     */
    @TableField(value = "create_time")
    private String createTime;
    /**
     * post属性
     */
    @TableField(exist = false)
    private Post post;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}