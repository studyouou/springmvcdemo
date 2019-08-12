package org.ougen.application.interception;


import org.springframework.stereotype.Component;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: OuGen
 * Discription:
 * Date: 15:35 2019/7/19
 */
@Component
public class MyInterception implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies){
//            if ("phone".equals(cookie.getName())){
//                return true;
//            }
//        }
//        request.getRequestDispatcher("/WEB-INF/login1").forward(request,response);
        System.out.println(Thread.currentThread().getId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("请求controller结束后，就到这里处理，如果带有@ResponseBody的话，不会等待处理完再返回" +
                "那怎么办呢？");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("请求结束后到这里"+Thread.currentThread().getId());
    }
}
