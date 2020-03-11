package by.epam.booking.filter;

import by.epam.booking.filter.helper.AllowedPages;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns = {"/jsp/view/*"}, initParams = {@WebInitParam(name = "INDEX_PATH", value = "/jsp/error/errorStepPage.jsp")})

public class PageRedirectSecurityFilter implements Filter {

    private String indexPath;
    private FilterConfig filterConfig;
    private ArrayList<String> allowedPagesForGuest;
    private ArrayList<String> allowedPagesForUser;
    private ArrayList<String> allowedPagesForAdmin;
    private boolean flag;

    public void init(FilterConfig fConfig) {

        filterConfig = fConfig;
        indexPath = fConfig.getInitParameter("INDEX_PATH");
        allowedPagesForGuest = new ArrayList<>();
        allowedPagesForUser = new ArrayList<>();
        allowedPagesForAdmin = new ArrayList<>();
        flag = true;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpServletResponse resp = (HttpServletResponse) response;

        AllowedPages.FillAllowLists(allowedPagesForGuest, allowedPagesForUser, allowedPagesForAdmin);

        String[] list = req.getRequestURI().split("/");
        String userRole = (String) req.getSession().getAttribute("userRoleType");

        String page = null;
        if (list[list.length - 1].indexOf(".jsp") > 0) {
            page = list[list.length - 1];
        }

        if(userRole == null){
            userRole = "";
        }
        if (!allowedPagesForGuest.contains(page) && userRole.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + indexPath);
        }else if(!allowedPagesForUser.contains(page) && userRole.equals("USER")){
            resp.sendRedirect(req.getContextPath() + indexPath);
        }else if(!allowedPagesForAdmin.contains(page) && userRole.equals("ADMIN")){
            resp.sendRedirect(req.getContextPath() + indexPath);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        filterConfig = null;
    }
}
