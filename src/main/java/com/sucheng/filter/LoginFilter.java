package com.sucheng.filter;

import com.sucheng.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("loginfilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if(uri.contains("/page/admin/")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isFilter(uri)) {
            HttpSession session = request.getSession();
            Object user = session.getAttribute(Constants.STORE_IN_SESSION);
            Object admin = session.getAttribute(Constants.ADMIN_IN_SESSION);
            if (user != null || admin != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect("/");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("loginfilter destroy");
    }

    private boolean isFilter(String uri) {
        return uri.contains("/page/");
    }
}
