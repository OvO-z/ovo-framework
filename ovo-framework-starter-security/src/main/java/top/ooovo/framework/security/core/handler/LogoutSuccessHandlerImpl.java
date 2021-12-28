package top.ooovo.framework.security.core.handler;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.util.servlet.ServletUtils;
import top.ooovo.framework.security.config.SecurityProperties;
import top.ooovo.framework.security.core.service.SecurityAuthFrameworkService;
import top.ooovo.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理器
 *
 * @author QAQ
 * @date 2021/11/22
 */

@AllArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final SecurityProperties securityProperties;

    private final SecurityAuthFrameworkService securityFrameworkService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 执行退出
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            securityFrameworkService.logout(token);
        }

        // 返回成功
        ServletUtils.writeJSON(response, CommonResult.success(null));
    }
}
