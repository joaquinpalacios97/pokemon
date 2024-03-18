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
        // Aquí obtienes la imagen de la solicitud y la conviertes a Base64
        // Supongamos que la imagen está en un parámetro llamado "image" en la solicitud
        byte[] imagen = obtenerImagenDesdeRequest(request);
        String imagenBase64 = convertirImagenABase64(imagen);

        // Se agrega la imagen convertida a la solicitud
        request.setAttribute("imagenBase64", imagenBase64);

        // Continuar con la cadena de filtros
        chain.doFilter(request, response);
    }

    private byte[] obtenerImagenDesdeRequest(ServletRequest request) {
        // Implementación para obtener la imagen de la solicitud
        // Esta es una implementación de ejemplo, debes adaptarla según tus necesidades
        return new byte[]{/* Obtener la imagen de la solicitud */};
    }

    private String convertirImagenABase64(byte[] imagen) {
        // Implementación para convertir la imagen a Base64
        return Base64.getEncoder().encodeToString(imagen);
    }

}