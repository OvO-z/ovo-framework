package top.ooovo.framework.apilog.core.service;


import top.ooovo.framework.apilog.core.service.dto.ApiAccessLogCreateReqDTO;

import javax.validation.Valid;

/**
 *
 * API 访问日志 Framework Service 接口
 *
 * @author QAQ
 * @date 2021/11/22
 */

public interface ApiAccessLogFrameworkService {

    /**
     * 创建 API 访问日志
     *
     * @param createDTO 创建信息
     */
    void createApiAccessLogAsync(@Valid ApiAccessLogCreateReqDTO createDTO);
}
