package com.smile.life.config;


/**
 * 认证授权
 *
 * @author: Smile
 * @date: 2019/4/18
 */

import com.smile.life.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class ApplicationConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许基于HttpServletRequest使用限制访问
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/mylife/**").hasRole("USER")
                .and().formLogin().loginPage("/login").loginProcessingUrl("/login").failureUrl("/user/login/error")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                //控制单个用户只能创建一个session，也就只能在服务器登录一次
                .and().sessionManagement().maximumSessions(1).expiredUrl("/login");
    }
}
