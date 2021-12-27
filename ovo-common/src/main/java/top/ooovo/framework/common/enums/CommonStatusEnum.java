package top.ooovo.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态枚举类
 * @author QAQ
 * @date 2021/11/18
 */

@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    // 开启
    ENABLE(0, "开启"),
    // 关闭
    DISABLE(1, "关闭");


    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

}
