package top.ooovo.framework.web.core.handler;


import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.web.core.util.WebFrameworkUtils;

/**
 * 全局响应结果（ResponseBody）处理器
 *
 * 不同于在网上看到的很多文章，会选择自动将 Controller 返回结果包上 {@link CommonResult}，
 * 在 onemall 中，是 Controller 在返回时，主动自己包上 {@link CommonResult}。
 * 原因是，GlobalResponseBodyHandler 本质上是 AOP，它不应该改变 Controller 返回的数据结构
 *
 * 目前，GlobalResponseBodyHandler 的主要作用是，记录 Controller 的返回结果，
 * @author QAQ
 * @date 2021/11/20
 */

@ControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter returnType, Class aClass) {
        if (returnType.getMethod() == null) {
            return false;
        }
        // 只拦截返回结果为 CommonResult 类型
        return returnType.getMethod().getReturnType() == CommonResult.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest request, ServerHttpResponse serverHttpResponse) {
        // 记录 Controller 结果
        WebFrameworkUtils.setCommonResult(((ServletServerHttpRequest) request).getServletRequest(), (CommonResult<?>) body);
        return body;
    }
}
