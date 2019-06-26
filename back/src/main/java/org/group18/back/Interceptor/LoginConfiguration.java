//参考：https://www.cnblogs.com/Dreamer-1/p/9034800.html



//package org.group18.back.Interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class LoginConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册拦截器
//        LoginInterceptor loginInterceptor = new LoginInterceptor();
//        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
//
//        //拦截路径
//        loginRegistry.addPathPatterns("/testInceptor");
//
//        //排除路径
//        loginRegistry.excludePathPatterns("/index");
//        loginRegistry.excludePathPatterns("/signin");
//        loginRegistry.excludePathPatterns("/register");
//    }
//}
