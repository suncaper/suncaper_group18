//参考：https://www.cnblogs.com/Dreamer-1/p/9034800.html


//package org.group18.back.Interceptor;
//
//import org.group18.back.Entity.User;
//import org.group18.back.Service.Impl.LoginRegisterServiceImpl;
//import org.group18.back.Service.LoginRegisterService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
//    /**
//     * 在请求被处理之前调用
//     * @param request
//     * @param response
//     * @param handler
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //根据cookie检查是否已经登陆
//        System.out.println("执行到拦截器");
//        LoginRegisterService loginRegisterService = new LoginRegisterServiceImpl();
//        User user = loginRegisterService.checkLoginStatus(request.getCookies());
//        if(user == null){
//            response.sendRedirect("/signin");
//        }
//        return true;
//    }
//
//    /**
//     * 在请求被处理后，视图渲染之前调用
//     * @param request
//     * @param response
//     * @param handler
//     * @param modelAndView
//     * @throws Exception
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    }
//
//    /**
//     * 在整个请求结束后调用
//     * @param request
//     * @param response
//     * @param handler
//     * @param ex
//     * @throws Exception
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
