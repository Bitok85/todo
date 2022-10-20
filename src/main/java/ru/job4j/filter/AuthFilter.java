package ru.job4j.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class AuthFilter implements Filter {

    private static final Set<String> URI_END = new HashSet<>(Arrays.asList(
            "loginPage", "login", "regUser", "registration", "index"
    ));
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
            if (checkUriEnd(uri)) {
                filterChain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        filterChain.doFilter(req, res);

    }

    /**private boolean checkUriEnd(String uri) {
        boolean rsl = false;
        for (String uriEnd : URI_END) {
            if (uri.endsWith(uriEnd)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }*/

    private boolean checkUriEnd(String uri) {
        return URI_END.stream().anyMatch(uri::endsWith);
    }
}
