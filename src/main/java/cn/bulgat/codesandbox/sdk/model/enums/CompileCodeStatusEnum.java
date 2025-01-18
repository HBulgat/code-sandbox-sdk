package cn.bulgat.codesandbox.sdk.model.enums;

import cn.bulgat.codesandbox.sdk.constant.CmdExecuteStatusConstant;
import lombok.Getter;

import java.io.Serializable;

@Getter
public enum CompileCodeStatusEnum implements Serializable {
    COMPILE_NO_NEEDED(CmdExecuteStatusConstant.SUCCESS,4,"Compile No Needed"),
    SYSTEM_ERROR(CmdExecuteStatusConstant.FAILED,3,"System Error"),
    COMPILE_TIME_OUT(CmdExecuteStatusConstant.FAILED,2,"Compile Time Out"),
    COMPILE_ERROR(CmdExecuteStatusConstant.FAILED,1,"Compile Error"),
    COMPILE_SUCCESS(CmdExecuteStatusConstant.SUCCESS,0,"Compile Success");
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
