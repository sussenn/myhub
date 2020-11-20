package com.itcodes.myhub.onlytest.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName ShipCodeEnum
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/10/16
 */
@Getter
@AllArgsConstructor
public enum ShipCodeEnum {

    SF("SF", "顺丰速运"),
    HTKY("HTKY", "百世快递"),
    ZTO("ZTO", "中通快递"),
    STO("STO", "申通快递"),
    YTO("YTO", "圆通速递"),
    YD("YD", "韵达速递"),
    YZPY("YZPY", "邮政快递包裹"),
    EMS("EMS", "EMS"),
    HHTT("HHTT", "天天快递"),
    JD("JD", "京东快递"),
    UC("UC", "优速快递"),
    DBL("DBL", "德邦快递"),
    ZJS("ZJS", "宅急送");

    private String code;
    private String desc;

    public static String getCode(String desc) {
        for (ShipCodeEnum value : ShipCodeEnum.values()) {
            if (value.desc.equals(desc)) {
                return value.code;
            }
        }
        return null;
    }

    public static String getDesc(String code) {
        for (ShipCodeEnum value : ShipCodeEnum.values()) {
            if (value.code.equals(code)) {
                return value.getDesc();
            }
        }
        return null;
    }
}
