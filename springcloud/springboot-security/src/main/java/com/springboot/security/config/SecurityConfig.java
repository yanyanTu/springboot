package com.springboot.security.config;

import com.springboot.security.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // 开启web保护
@EnableGlobalMethodSecurity(prePostEnabled=true)// 开启方法上的保护
// prePostEnabled=true:开启方法级别的安全校验，prePostEnabled:Pre和Post是否可用，
// 即：＠PreAuthorize(在进入方法前验证权限)和@PostAuthorize(在执行方法后进行验证权限)是否可用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService ;

    /**
     * 配置请求的安全验证
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("123123").roles("USER"); // 验证时指定用户信息
//        authenticationManagerBuilder.userDetailsService(userDetailsService());// 设置验证实现类从内存中验证用户登录信息
        // 配置认证服务类和密码加密实现类
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());// 设置验证实现类从数据库中验证用户登录信息
    }

    /**
     * 配置用户信息和密码的安全验证策略
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();
        // 登录时权限设置处理
//        http.authorizeRequests()
//                .antMatchers("/css/**", "/index").permitAll() // 以css/**开头和/index资源不需验证，外界直接访问
//                .antMatchers("/user/**").hasRole("USER") // 以/user/**开头的资源需验证，且角色需为USER
//                .antMatchers("/blogs/**").hasRole("USER") // 以/blogs/**开头的资源需验证，且角色需为USER
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login-error")
//                .and()
//                .exceptionHandling().accessDeniedPage("/401");// 异常重定向到/401界面
//        http.logout().logoutSuccessUrl("/");
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("123123").roles("admin").build());
        manager.createUser(User.withUsername("test").password("123123").roles("USER").build());
        return manager;
    }

    /**
     * 配置验证管理的bean
     * @return
     * @throws Exception
     */
    @Override
    @Bean // 自定义认证bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
