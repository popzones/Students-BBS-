package com.saybiu.bbs.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 
 * @TableName bbs_users
 */
@TableName(value ="bbs_users",resultMap = "BaseResultMap")
@Data
@Validated
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    /**
     * 手机号
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 逻辑删除 true已删除 false未删除
     */
    @TableField(value = "user_muted")
    private Boolean userMuted;

    /**
     * 注册时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private String updateTime;

    /**
     * 注册用户ip
     */
    @TableField(value = "user_register_ip")
    private String userRegisterIp;

    /**
     * 最近登录id
     */
    @TableField(value = "user_latest_ip")
    private String userLatestIp;

    /**
     * 用户属性对象
     */
    @TableField(exist = false)
    private Usermeta usermeta;

}