package com.saybiu.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName bbs_usermeta
 */
@TableName(value ="bbs_usermeta")
@Data
public class Usermeta implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "userMeta_id", type = IdType.AUTO)
    private Integer usermetaId;

    /**
     * 用户昵称
     */
    @TableField(value = "user_nickname")
    private String userNickname;

    /**
     * 用户性别
     */
    @TableField(value = "user_sex")
    private String userSex;

    /**
     * 用户头像url
     */
    @TableField(value = "user_profile_picture")
    private String userProfilePicture;

    /**
     * 粉丝数
     */
    @TableField(value = "user_supported_num")
    private Integer userSupportedNum;

    /**
     * 收藏帖子数
     */
    @TableField(value = "user_support_post_num")
    private Integer userSupportPostNum;

    /**
     * 主动发送帖子数
     */
    @TableField(value = "user_post_num")
    private Integer userPostNum;

    /**
     * 关注人数
     */
    @TableField(value = "user_support_num")
    private Integer userSupportNum;

    /**
     * 获赞数
     */
    @TableField(value = "user_like_num")
    private Integer userLikeNum;

    /**
     * 个性签名
     */
    @TableField(value = "user_introduce")
    private String userIntroduce;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}