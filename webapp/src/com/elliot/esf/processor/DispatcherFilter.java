package com.elliot.esf.processor;

import com.elliot.esf.context.ParamsSetting;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: huangxa
 * Date: 13-7-4
 * Time: 下午1:37
 * To change this template use File | Settings | File Templates.
 */
public class DispatcherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri = request.getServletPath();
        if(!uri.startsWith("/install")&& !ParamsSetting.isInstall){
            response.sendRedirect(request.getContextPath()+"/install");
            return;
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
