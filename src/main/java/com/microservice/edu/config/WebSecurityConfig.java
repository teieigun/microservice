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
            .antMatchers("/watch").hasAnyAuthority("1","2","3")
            .antMatchers("/watch/**").authenticated()//所有/video/**的请求必须认证通过
            .antMatchers("/account").hasAnyAuthority("1","2","3")
            .antMatchers("/account/**").authenticated()//所有/video/**的请求必须认证通过
            .antMatchers("/vip").hasAnyAuthority("2","3")
            .antMatchers("/vip/**").authenticated()//所有/video/**的请求必须认证通过
            .antMatchers("/admin").hasAnyAuthority("3")
            .antMatchers("/admin/**").authenticated()//所有/admin/**的请求必须认证通过
            .antMatchers("/account").hasAnyAuthority("1","2","3")
            .antMatchers("/account/**").authenticated()//所有/r/**的请求必须认证通过
            .antMatchers("/", "/index","/video/css","/video/img","/video/font","/video/js").permitAll()//除了/r/**，其它的请求可以访问
            .and()
            .formLogin().loginPage("/login")
            .loginProcessingUrl("/dologin")
            .defaultSuccessUrl("/index")
            .failureForwardUrl("/login")
            .usernameParameter("username").passwordParameter("password")
            .permitAll();

            http.logout()
            .logoutSuccessUrl("/") // ログアウト成功時の遷移先URL
            .permitAll();          // すべてのユーザに対して、ログアウトページへのアクセスを許す

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
