package com.yunyun.financemanager.common.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author zhaoqin
 */
public enum RoleEnum {

    ROLE_NORMAL(0),
    ROLE_FINANCE(1)
    ;

    private final int value;

    public static RoleEnum of(int value) {
        RoleEnum[] values = values();
        Optional<RoleEnum> roleEnumOptional = Arrays.stream(values)
                .filter(r -> r.value() == value)
                .findAny();
        return roleEnumOptional
                .orElseThrow(() -> new IllegalArgumentException("未找到角色value: " + value));
    }

    RoleEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
