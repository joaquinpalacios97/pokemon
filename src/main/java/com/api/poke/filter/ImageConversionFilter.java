package com.api.poke.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
@WebFilter(urlPatterns = "/pokemons/*")
public class ImageConversionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        byte[] imagen = obtenerImagenDesdeRequest(request);
        String imagenBase64 = convertirImagenABase64(imagen);
        request.setAttribute("imagenBase64", imagenBase64);
        chain.doFilter(request, response);
    }

    private byte[] obtenerImagenDesdeRequest(ServletRequest request) {
        return new byte[]{};
    }

    private String convertirImagenABase64(byte[] imagen) {
        return Base64.getEncoder().encodeToString(imagen);
    }

}