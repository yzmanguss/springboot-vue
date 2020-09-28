package com.yunyun.financemanager.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * account
 */
@Data
public class Account implements Serializable {

    @TableId
    private Long id;

    private Long insertBy;

    private Long updateBy;

    private String accountName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色-0一般人员，1财务人员
     */
    private Integer type;

    /**
     * 密码
     */
    private String password;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}