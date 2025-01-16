package cn.bulgat.codesandbox.sdk.model.enums;

import cn.bulgat.codesandbox.sdk.constant.CmdExecuteStatusConstant;
import lombok.Getter;


@Getter
public enum ExecuteCodeStatusEnum {
    EXECUTE_TIME_OUT(CmdExecuteStatusConstant.FAILED,4,"Execute Time Out"),
    SYSTEM_ERROR(CmdExecuteStatusConstant.FAILED,3,"System Error"),
    EXECUTE_ERROR(CmdExecuteStatusConstant.FAILED,1,"Execute Error"),
    EXECUTE_OUTPUT_EXCEEDED(CmdExecuteStatusConstant.FAILED,2,"Execute Output Exceeded"),
    EXECUTE_SUCCESS(CmdExecuteStatusConstant.SUCCESS,0,"Execute Success");
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