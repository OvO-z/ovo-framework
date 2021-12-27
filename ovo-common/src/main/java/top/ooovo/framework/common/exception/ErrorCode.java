package top.ooovo.framework.common.exception;

import lombok.Data;

/**
 * 错误码对象
 *
 * 全局错误码，占用 [0, 999]
 * @author QAQ
 * @date 2021/11/18
 */

@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }
}
