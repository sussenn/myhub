package com.itcodes.configread.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MyEnum {
    /**
     * 运营
     */
    ROLE_ADMIN(1, "运营"),
    /**
     * 教学
     */
    ROLE_DUDAO(2, "教学"),
    /**
     * 职能
     */
    ROLE_MANAGER(3, "职能");
    /**
     * 类型
     */
    private Integer type;
    /**
     * 描述
     */
    private String description;


}
