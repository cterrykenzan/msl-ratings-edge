package io.swagger.api.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.kenzan.msl.common.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RatingsEdgeApiOriginFilter implements javax.servlet.Filter {

    @Inject
    @Named("clientPort")
    private static String CLIENT_PORT;

    @Inject
    private static RatingsEdgeSessionToken ratingsEdgeSessionToken;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", Utils.getHost(req.getRequestURL().toString(), CLIENT_PORT));
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        ratingsEdgeSessionToken.updateSessionToken(req);
        chain.doFilter(request, response);
    }

    public void destroy() {}

    public void init(FilterConfig filterConfig) throws ServletException {}
}
