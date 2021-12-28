package top.ooovo.framework.apilog.core.service;


import top.ooovo.framework.apilog.core.service.dto.ApiErrorLogCreateReqDTO;

import javax.validation.Valid;

/**
 *
 * API 错误日志 Framework Service 接口
 *
 * @author QAQ
 * @date 2021/11/22
 */

public interface ApiErrorLogFrameworkService {

    /**
     * 创建 API 错误日志
     *
     * @param createDTO 创建信息
     */
    void createApiErrorLogAsync(@Valid ApiErrorLogCreateReqDTO createDTO);
}
