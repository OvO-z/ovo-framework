package top.ooovo.framework.security.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import top.ooovo.framework.security.core.annotations.PreAuthenticated;
import top.ooovo.framework.security.core.util.SecurityFrameworkUtils;

import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;
import static top.ooovo.framework.common.exception.enums.GlobalErrorCodeConstants.*;
/**
 * @author QAQ
 * @date 2021/11/22
 */

@Aspect
@Slf4j
public class PreAuthenticatedAspect {

    @Around("@annotation(preAuthenticated)")
    public Object around(ProceedingJoinPoint joinPoint, PreAuthenticated preAuthenticated) throws Throwable{
        if (SecurityFrameworkUtils.getLoginUser() == null) {
            throw exception(UNAUTHORIZED);
        }
        return joinPoint.proceed();
    }
}
