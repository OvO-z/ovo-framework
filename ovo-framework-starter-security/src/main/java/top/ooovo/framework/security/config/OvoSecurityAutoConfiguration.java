package top.ooovo.framework.security.config;

import top.ooovo.framework.security.core.aop.PreAuthenticatedAspect;
import top.ooovo.framework.security.core.filter.JwtAuthenticationTokenFilter;
import top.ooovo.framework.security.core.handler.AccessDeniedHandlerImpl;
import top.ooovo.framework.security.core.handler.AuthenticationEntryPointImpl;
import top.ooovo.framework.security.core.handler.LogoutSuccessHandlerImpl;
import top.ooovo.framework.security.core.service.SecurityAuthFrameworkService;
import top.ooovo.framework.web.core.handler.GlobalExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;

/**
 Spring Security 自动配置类，主要用于相关组件的配置
 *
 * 注意，不能和 {@link OvoWebSecurityConfigurerAdapter} 用一个，原因是会导致初始化报错。
 * 参见 https://stackoverflow.com/questions/53847050/spring-boot-delegatebuilder-cannot-be-null-on-autowiring-authenticationmanager 文档。
 * @author QAQ
 * @date 2021/11/22
 */

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class OvoSecurityAutoConfiguration {

    @Resource
    private SecurityProperties securityProperties;


    /**
     * 处理用户未登录拦截的切面的 Bean
     */
    @Bean
    public PreAuthenticatedAspect preAuthenticatedAspect() {
        return new PreAuthenticatedAspect();
    }

    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    /**
     * 退出处理类 Bean
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(SecurityAuthFrameworkService securityFrameworkService) {
        return new LogoutSuccessHandlerImpl(securityProperties, securityFrameworkService);
    }

    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     *
     * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding with Spring Security</a>
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Token 认证过滤器 Bean
     */
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter(SecurityAuthFrameworkService securityFrameworkService,
                                                                  GlobalExceptionHandler globalExceptionHandler) {
        return new JwtAuthenticationTokenFilter(securityProperties, securityFrameworkService, globalExceptionHandler);
    }


}
