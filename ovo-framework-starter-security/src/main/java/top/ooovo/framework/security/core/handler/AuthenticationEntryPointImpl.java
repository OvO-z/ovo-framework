package top.ooovo.framework.security.core.handler;

import lombok.extern.slf4j.Slf4j;
import top.ooovo.framework.common.exception.enums.GlobalErrorCodeConstants;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.util.servlet.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static top.ooovo.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

/**
 *
 * 访问一个需要认证的 URL 资源，但是此时自己尚未认证（登录）的情况下，返回 {@link GlobalErrorCodeConstants#UNAUTHORIZED} 错误码，从而使前端重定向到登录页
 *
 * @author QAQ
 * @date 2021/11/22
 */

@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.debug("[commence][访问 URL({}) 时，没有登录]", request.getRequestURI(), e);
        // 返回 401
        ServletUtils.writeJSON(response, CommonResult.error(UNAUTHORIZED));
    }
}
