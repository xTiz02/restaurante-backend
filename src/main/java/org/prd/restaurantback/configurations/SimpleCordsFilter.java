package org.prd.restaurantback.configurations;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCordsFilter implements Filter {

    private static final String clientAppUrl = "http://localhost:4200/*";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response= (HttpServletResponse) res;
        HttpServletRequest request= (HttpServletRequest) req;
        Map<String, String> map = new HashMap<>();
        String origin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", origin); //origin es la url de la aplicacion que hace la peticion
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH"); //permite que el navegador acceda a los metodos
        response.setHeader("Access-Control-Allow-Headers", "*"); //permite que el navegador acceda a los headers
        response.setHeader("Access-Control-Max-Age", "3600"); //3600 es poruque es el tiempo que se almacena en el navegador la configuracion de los cors
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(req, res);
        }
    }//se encarga de filtrar las peticiones que llegan al servidor y añadir los headers necesarios para que el navegador no bloquee las peticiones

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
