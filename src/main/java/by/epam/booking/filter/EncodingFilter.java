package by.epam.booking.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// FIXME: 18.11.2019 Разобраться как работают фильтры до конца!

@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param"),
                @WebInitParam(name = "locale", value = "ru_RU", description = "Locale Param")})
public class EncodingFilter implements Filter {
    private String code;
    private String locale;
    private static final String ENCODING  = "encoding";
    private static final String LOCALE  = "locale";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(ENCODING);
        locale = filterConfig.getInitParameter(LOCALE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String sessionLocale = (String) session.getAttribute(LOCALE);
        if (sessionLocale == null) {
            session.setAttribute(LOCALE, locale);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}