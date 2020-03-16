package com.itontheway.manage.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.crazycake.shiro.serializer.ObjectSerializer;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: 公众号 itontheway
 * @description: shiro 配置类
 * @date 2020/3/13 20:05
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/14 15:04
     * @Desc shiro + redis
     * @Param []
     * @Return org.crazycake.shiro.RedisManager
     **/
    @Bean
    public RedisManager redisManager() {
        //如果不设置这个，RedisManager 会抛异常 ArrayIndexOutOfBoundsException，因为找不到 host和port
        JedisPool jedisPool = new JedisPool(host,port);
        RedisManager redisManager = new RedisManager();
        redisManager.setJedisPool(jedisPool);
        redisManager.setTimeout(30000);
        return redisManager;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/14 15:04
     * @Desc shiro + redis
     * @Param []
     * @Return RedisCacheManager
     **/
    @Bean(name = "redisCacheManager")
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setKeySerializer(new ObjectSerializer());
        redisCacheManager.setValueSerializer(new ObjectSerializer());
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setExpire(1800);
        return redisCacheManager;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/14 15:04
     * @Desc shiro + redis
     * @Param []
     * @Return RedisSessionDAO
     **/
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setExpire(1800);
        return redisSessionDAO;
    }


    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/13 20:08
     * @Desc Session Manager：会话管理  即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；
     *  会话可以是普通JavaSE环境的，也可以是如Web环境的；
     * @Param []
     * @Return org.apache.shiro.session.mgt.SessionManager
     **/
    @Bean("sessionManager")
    public DefaultWebSessionManager  sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/13 20:11
     * @Desc 安全管理器
     * @Param [userRealm, sessionManager]
     * @Return java.lang.SecurityManager
     **/
    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(shiroRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/13 20:11
     * @Desc ShiroFilter是整个Shiro的入口点，用于拦截需要安全控制的请求进行处理
     * @Param [securityManager]
     * @Return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     **/
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //0.定义 ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //1.设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //2.LinkedHashMap是有序的，进行顺序拦截器配置
        Map<String, String> filterChainMap  = new LinkedHashMap<>();
        //3.放开 Swagger2 ，如果swagger2报 NumberFormatException这个异常 ，则降低swagger2的版本
        filterChainMap.put("/", "anon");
        filterChainMap.put("/user/**", "anon");
        filterChainMap.put("/role/**", "anon");
        filterChainMap.put("/mail/**", "anon");
        filterChainMap.put("/dict/**", "anon");
        filterChainMap.put("/swagger-ui.html", "anon");
        filterChainMap.put("/swagger-resources/**", "anon");
        filterChainMap.put("/v2/api-docs/**", "anon");
        filterChainMap.put("/webjars/springfox-swagger-ui/**", "anon");
        //4.配置login，不然 ShiroRealm 里面的方法 doGetAuthenticationInfo 会调用两次
        filterChainMap.put("/login", "anon");
        //5.配置logout过滤器
        filterChainMap.put("/logout", "anon");
        //6.所有url必须通过认证才可以访问
        filterChainMap.put("/**","authc");
        //7.设置默认登录的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //8.设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //9.设置未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        //10.设置shiroFilterFactoryBean的FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }


    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/13 20:12
     * @Desc 扫描上下文，寻找所有的Advistor(通知器） 将这些Advisor应用到所有符合切入点的Bean中
     *       开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     *       配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @Param []
     * @Return org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     **/
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/13 20:13
     * @Desc 匹配所有加了 Shiro 认证注解的方法    开启aop注解支持
     * @Param [securityManager]
     * @Return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     **/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
