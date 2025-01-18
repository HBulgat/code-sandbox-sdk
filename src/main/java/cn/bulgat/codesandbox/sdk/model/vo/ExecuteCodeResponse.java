package cn.bulgat.codesandbox.sdk.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 返回跑一个输入的信息
 * - 编译信息（有的语言没有）
 * - 输出（列表）
 * - 占用内存（列表）
 * - 执行时间（列表）
 * - 容器运行代码信息：
 *   - 执行出错
 *   - 正常执行
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecuteCodeResponse implements Serializable {
    /**
     * 编译信息
     */
    private CompileMessage compileMessage;
    /**
     * 运行信息
     */
    private List<ExecuteMessage> executeMessageList;
}
