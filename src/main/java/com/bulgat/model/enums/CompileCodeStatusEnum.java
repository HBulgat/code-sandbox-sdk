package com.bulgat.model.enums;

import lombok.Getter;

import static com.bulgat.constant.CmdExecuteStatusConstant.FAILED;
import static com.bulgat.constant.CmdExecuteStatusConstant.SUCCESS;

@Getter
public enum CompileCodeStatusEnum {
    COMPILE_NO_NEEDED(SUCCESS,4,"Compile No Needed"),
    SYSTEM_ERROR(FAILED,3,"System Error"),
    COMPILE_TIME_OUT(FAILED,2,"Compile Time Out"),
    COMPILE_ERROR(FAILED,1,"Compile Error"),
    COMPILE_SUCCESS(SUCCESS,0,"Compile Success");
    /**
     * 1 成功，0 失败
     */
    private final int success;
    private final int code;
    private final String message;
    CompileCodeStatusEnum(int success,int code,String message){
        this.success=success;
        this.message=message;
        this.code=code;
    }

    /**
     * 根据 code 获取枚举
     *
     * @param code
     * @return
     */
    public static CompileCodeStatusEnum getEnumByCode(int code) {
        for (CompileCodeStatusEnum anEnum : CompileCodeStatusEnum.values()) {
            if (anEnum.code==code) {
                return anEnum;
            }
        }
        return null;
    }
}
