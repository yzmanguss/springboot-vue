package com.yunyun.financemanager.common.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotBlank
    @Pattern(regexp = "^1\\d{10}", message = "请输入正确的手机号")
    private String phone;

    /**
     * 角色-0一般人员，1财务人员
     */
    private Integer type;

    /**
     * 密码
     */
    @NotNull
    @Length(min = 6, max = 20)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private LocalDateTime insertTime;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}