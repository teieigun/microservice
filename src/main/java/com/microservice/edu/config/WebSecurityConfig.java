package com.microservice.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @author Administrator
 * @version 1.0
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http)  {
    	try {
            http
            .authorizeRequests()
            .antMatchers("/video").hasAuthority("5")
            .antMatchers("/video/video").hasAuthority("5")
            .antMatchers("/video/vip").hasAuthority("5")
            .antMatchers("/video/admin").hasAuthority("5")
            .antMatchers("/video/**").authenticated()//所有/r/**的请求必须认证通过
            .antMatchers("/", "/index").permitAll()//除了/r/**，其它的请求可以访问
            .and()
            .formLogin().loginPage("/")
            .loginProcessingUrl("/login")
            .failureHandler(new SimpleUrlAuthenticationFailureHandler())       // 認証失敗時に呼ばれるハンドラクラス
            .defaultSuccessUrl("/video")
            .usernameParameter("username").passwordParameter("password")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/regist?logout")
            .permitAll();
    	}catch(Exception e) {
    		System.out.println(e.toString());
    	}


    }


    @Configuration
    protected static class AuthenticationConfiguration
    extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        UserDetailsService userDetailsService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 認証するユーザーを設定する
            auth.userDetailsService(userDetailsService)
            // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
            //.passwordEncoder(new BCryptPasswordEncoder());
            // ·密码不加密
            .passwordEncoder(NoOpPasswordEncoder.getInstance());

        }
    }

}
