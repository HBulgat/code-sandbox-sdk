package cn.bulgat.codesandbox.sdk.model.dto;

import cn.bulgat.codesandbox.sdk.model.Input;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 对外提供给用户的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecuteCodeRequest implements Serializable {
    private InputList inputList;
    private String code;
    private String language;
}
