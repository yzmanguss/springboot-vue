package com.yunyun.financemanager.common.enums;

import java.util.Arrays;

/**
 * @author zhaoqin
 */
public enum RoleEnum {

    ROLE_NORMAL(0),
    ROLE_FINANCE(1)
    ;

    private final int value;

    public static RoleEnum of(int value) {
        return Arrays.stream(values())
                .filter(r -> r.value() == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("未找到角色value: " + value));
    }

    RoleEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
