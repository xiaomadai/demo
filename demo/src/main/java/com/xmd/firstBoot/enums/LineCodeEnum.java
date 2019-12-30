package com.xmd.firstBoot.enums;

/**
 * @Date: Created in 10:05 2019/10/22
 */
public enum LineCodeEnum {
    HR_PUBLIC_ADMINISTRATION("人行","人行"),
    FINANCIAL("财务","财务"),
    MANAGEMENT("多营","多营"),
    STEWARD("管家","管家"),
    OTHERS("其他","所选单元其他业务人员"),
    NO("无",""),
    ;

    private String code;
    private String name;

    private LineCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(String code) {
        if(LineCodeEnum.OTHERS.getCode().equals(code) ||(!LineCodeEnum.HR_PUBLIC_ADMINISTRATION.getCode().equals(code) && (!LineCodeEnum.FINANCIAL.getCode().equals(code)
                && (!LineCodeEnum.MANAGEMENT.getCode().equals(code)) && (!LineCodeEnum.STEWARD.getCode().equals(code)) &&(!LineCodeEnum.NO.getCode().equals(code)) ))){
            return LineCodeEnum.OTHERS.getName();
        }
        for (LineCodeEnum lineCodeEnum : LineCodeEnum.class.getEnumConstants()) {
            if (lineCodeEnum.getCode().equals(code)) {
                return lineCodeEnum.getName();
            }

        }
        return null;
    }
}
