package com.sucheng.filter;

import com.sucheng.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by yao on 2018/1/9.
 */
public class AdminFilter implements Filter{

    private Logger logger= LoggerFactory.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init........");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if(uri.contains("/page/admin/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isFilter(uri)) {
            HttpSession session = request.getSession();
            Object admin = session.getAttribute(Constants.ADMIN_IN_SESSION);
            if (admin != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect("/page/admin/login");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("destroy........");
    }

    private boolean isFilter(String uri) {
        return uri.contains("/page/admin/");
    }
}
