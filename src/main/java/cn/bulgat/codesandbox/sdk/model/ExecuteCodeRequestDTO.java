package cn.bulgat.codesandbox.sdk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 给http请求的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecuteCodeRequestDTO implements Serializable {
    private List<Input> inputList;
    private String code;
    private String language;
}
