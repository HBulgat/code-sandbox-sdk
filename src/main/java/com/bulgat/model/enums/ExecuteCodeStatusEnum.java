package com.bulgat.model.enums;

import lombok.Getter;

import static com.bulgat.constant.CmdExecuteStatusConstant.FAILED;
import static com.bulgat.constant.CmdExecuteStatusConstant.SUCCESS;


@Getter
public enum ExecuteCodeStatusEnum {
    EXECUTE_TIME_OUT(FAILED,4,"Execute Time Out"),
    SYSTEM_ERROR(FAILED,3,"System Error"),
    EXECUTE_ERROR(FAILED,1,"Execute Error"),
    EXECUTE_OUTPUT_EXCEEDED(FAILED,2,"Execute Output Exceeded"),
    EXECUTE_SUCCESS(SUCCESS,0,"Execute Success");
    private final int success;
    private final int code;
    private final String message;
    ExecuteCodeStatusEnum(int success,int code,String message){
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
    public static ExecuteCodeStatusEnum getEnumByCode(int code) {
        for (ExecuteCodeStatusEnum anEnum : ExecuteCodeStatusEnum.values()) {
            if (anEnum.code==code) {
                return anEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}