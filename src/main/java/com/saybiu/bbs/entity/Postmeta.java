package com.saybiu.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName bbs_postmeta
 */
@TableName(value ="bbs_postmeta")
@Data
public class Postmeta implements Serializable {
    /**
     * 帖子属性id
     */
    @TableId(value = "postmeta_id", type = IdType.AUTO)
    private Integer postmetaId;

    /**
     * 帖子内容
     */
    @TableField(value = "post_content")
    private String postContent;

    /**
     * 帖子id
     */
    @TableField(value = "post_id")
    private Integer postId;

    /**
     * 帖子标题
     */
    @TableField(value = "post_title")
    private String postTitle;

    /**
     * 帖子类型
     */
    @TableField(value = "post_type_name")
    private String postTypeName;

    /**
     * 帖子阅读量
     */
    @TableField(value = "post_read_num")
    private Integer postReadNum;

    /**
     * 帖子点赞量
     */
    @TableField(value = "post_liked_num")
    private Integer postLikedNum;

    /**
     * 帖子收藏量
     */
    @TableField(value = "post_collected_num")
    private Integer postCollectedNum;

    /**
     * 帖子评论量
     */
    @TableField(value = "post_commented_num")
    private Integer postCommentedNum;

    /**
     * 最新评论时间
     */
    @TableField(value = "update_time")
    private String updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}