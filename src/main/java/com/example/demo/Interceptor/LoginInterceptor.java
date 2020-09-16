package com.example.demo.Interceptor;

import com.example.demo.Interceptor.annotation.LoginRequire;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            //尝试获取方法注解
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            LoginRequire loginRequire = method.getAnnotation(LoginRequire.class);
            //如果方法注解不存在，尝试查找类注解
            if(loginRequire == null){
                loginRequire = method.getDeclaringClass().getAnnotation(LoginRequire.class);
            }
            //如果有访问权限限制
            if(loginRequire != null && !loginRequire.value().equals("")){
                HttpSession session = request.getSession();
                //尝试获取session中的授权信息
                if(session.getAttribute("auth")!=null){
                    int required = Integer.parseInt(loginRequire.value()),
                            provided = Integer.parseInt((String) session.getAttribute("auth"));
                    if(required==provided)
                        return true;
                }else{
                    //如果权限越界，则拒绝请求
//                    response.setStatus(403);
//                    response.setContentType("application/json;charset=UTF-8");
//                    response.getWriter().write("{\"errCode\":-1,\"msg\":\"auth refuse\"}");

                    // TODO: 添加前端提示登录
                    response.sendRedirect("/login");
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
