package top.ooovo.framework.common.enums;

/**
 * Web 过滤器顺序的枚举类
 * @author QAQ
 * @date 2021/11/20
 */

public interface WebFilterOrderEnum {
    int CORS_FILTER = Integer.MIN_VALUE;

    int TRACE_FILTER = CORS_FILTER + 1;

    int REQUEST_BODY_CACHE_FILTER = Integer.MIN_VALUE + 500;

    /**
     * 需要保证在 RequestBodyCacheFilter 后面
     */
    int API_ACCESS_LOG_FILTER = -104;
}
