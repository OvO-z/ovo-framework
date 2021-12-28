package top.ooovo.framework.apilog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ooovo.framework.common.enums.WebFilterOrderEnum;
import top.ooovo.framework.apilog.core.filter.ApiAccessLogFilter;
import top.ooovo.framework.apilog.core.service.ApiAccessLogFrameworkService;
import top.ooovo.framework.web.config.OvoWebAutoConfiguration;
import top.ooovo.framework.web.config.WebProperties;

import javax.servlet.Filter;

/**
 * API 日志 自动配置类
 * @author QAQ
 * @date 2021/11/20
 */

@Configuration
@AutoConfigureAfter(OvoWebAutoConfiguration.class)
public class ApiLogAutoConfiguration {

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

}
