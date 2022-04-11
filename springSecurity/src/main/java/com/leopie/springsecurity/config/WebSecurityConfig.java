package com.leopie.springsecurity.config;

import com.leopie.springsecurity.service.LeoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 实现方式1:设置passwordEncoder，同时将LeoUserDetailsService通过@Service交给spring容器管理
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 实现方式2：在实现方式1的基础上，去掉LeoUserDetailsService的@Service
     * 通过以下userDetailsServiceBean方法new LeoUserDetailsService对象并通过@Bean交给spring容器管理
     * @return
     * @throws Exception
     */
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return new LeoUserDetailsService();
//    }

    /**
     * 实现方式3：在实现方式1的基础上，去掉LeoUserDetailsService的@Service
     * 通过以下userDetailsService方法new LeoUserDetailsService对象并通过@Bean交给spring容器管理
     * @return
     */
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new LeoUserDetailsService();
//    }

    /**
     * 实现方式4：在实现方式1的基础上，去掉LeoUserDetailsService，直接使用下面configure方法
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("Tony")
//                .password(passwordEncoder().encode("Tony"))
//                .authorities("admin");
//    }
}
