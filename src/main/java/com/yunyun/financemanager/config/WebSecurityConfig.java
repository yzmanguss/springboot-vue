package com.yunyun.financemanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.response.ResponseCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhaoqin
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .successHandler(new AuthenticationSuccessHandlerImpl())
                .failureHandler(new AuthenticationFailureHandlerImpl())
                .and()
                .rememberMe()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandlerImpl())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPointImpl());
    }

    /**
     * 处理登录成功返回
     */
    public static class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException {
            Object principal = authentication.getPrincipal();
            String result = OBJECT_MAPPER.writeValueAsString(ApiResponse.ok(principal));
            writeStringWithJson(response, result);
        }
    }

    /**
     * 处理登录失败返回
     */
    public static class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException {
            String result = OBJECT_MAPPER.writeValueAsString(ApiResponse.failure(ResponseCode.BAD_CREDENTIAL));
            writeStringWithJson(response, result);
        }
    }

    /**
     * 处理登出成功返回
     */
    public static class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException {
            String result = OBJECT_MAPPER.writeValueAsString(ApiResponse.ok());
            writeStringWithJson(response, result);
        }
    }

    /**
     * 处理未登录返回
     */
    public static class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException) throws IOException {
            String result = OBJECT_MAPPER.writeValueAsString(ApiResponse.failure(ResponseCode.UNAUTHORIZED));
            writeStringWithJson(response, result);
        }
    }

    @SuppressWarnings("deprecation")
    private static void writeStringWithJson(HttpServletResponse response, String jsonStr)  throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = response.getWriter();
        writer.write(jsonStr);
        writer.flush();
    }

}
