package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * customer
 */
@Data
public class Customer implements Serializable {

    @TableId
    private Long id;

    private String customerName;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    private static final long serialVersionUID = 1L;
}