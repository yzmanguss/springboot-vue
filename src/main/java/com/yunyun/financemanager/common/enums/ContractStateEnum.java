package com.yunyun.financemanager.common.enums;

/**
 * @author xlc
 */
public enum ContractStateEnum {

    DEVELOPMENT(0, "开发中"),
    MAINTENANCE(1, "维护中"),
    ABANDONED(2, "废弃"),
    END(3, "结束");

    private final Integer code;

    private final String name;

    ContractStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
